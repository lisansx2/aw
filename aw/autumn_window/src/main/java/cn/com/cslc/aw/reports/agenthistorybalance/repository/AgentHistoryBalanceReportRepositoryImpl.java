package cn.com.cslc.aw.reports.agenthistorybalance.repository;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;

import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import com.google.common.collect.Sets;

import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceCondition;
import cn.com.cslc.aw.reports.agenthistorybalance.dto.QueryAgentHistoryBalanceResult;

public class AgentHistoryBalanceReportRepositoryImpl implements AgentHistoryBalanceReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryAgentHistoryBalanceResult> queryByCondition(QueryAgentHistoryBalanceCondition queryCondition,
			Pageable pageable) {
		
		String startDateStr = queryCondition.getStartDate() + " 00:00:00";
		String endDateStr = queryCondition.getEndDate() + " 23:59:59";
		
		String provinceId = queryCondition.getProvinceId();
		Set<String> provinceIdSet = Sets.newHashSet();
		if(StringUtils.isEmpty(provinceId)){
			provinceIdSet = queryCondition.getProvinceIdSet();
		}else{
			provinceIdSet.add(provinceId);
		}

		String agentNo = queryCondition.getAgentCode();

		Set<String> agentNoSet = Sets.newHashSet();
		if(StringUtils.isEmpty(agentNo)){
			agentNoSet = queryCondition.getAgentCodeSet();
		}else{
			agentNoSet.add(agentNo);
		}

		
		String sqlString= "SELECT  ";
		sqlString +="  a.agent_name as agentName,";
		sqlString +="  a.agent_no as agentCode,";
		sqlString +="  a.account_no as agentAccountCode,";
		sqlString +="  SUM(";
		sqlString +="  CASE";
		sqlString +="    WHEN a.report_date = min_report_date";
		sqlString +="    THEN NVL(a.prior_amount, 0)";
		sqlString +="    ELSE 0";
		sqlString +="  END) as balanceAmountBeforeSettled,";
		sqlString +="  SUM(";
		sqlString +="  CASE";
		sqlString +="    WHEN a.report_date = max_report_date";
		sqlString +="    THEN NVL(a.post_amount, 0)";
		sqlString +="    ELSE 0";
		sqlString +="  END) as balanceAmountAfterSettled,";
		sqlString +="  SUM(a.dealtype_10_amount + a.dealtype_20_amount - a.dealtype_20agent_amount - a.paid_autocollect_amount) as paidAmount,";
//		sqlString +="  SUM(a.dealtype_10_amount ) as paidAmount,";
		sqlString +="  SUM(a.ctb_amount) as ctbAmount,";
		sqlString +="  SUM(a.paid_autocollect_amount) as paidAutocollectAmount,";
		sqlString +="  SUM(a.transfer_amount) as agentAllocatedAmount ";
		sqlString +="FROM";
		sqlString +="  (SELECT ba.agent_name,";
		sqlString +="    ba.agent_no,";
		sqlString +="    bai.account_no,";
		sqlString +="    rcbd.report_date,";
		
		sqlString = sqlString +"    TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss'))    AS min_report_date,";
		sqlString = sqlString +"    TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))  AS max_report_date,";
		
		sqlString +="    rcbd.prior_amount,";
		sqlString +="    rcbd.post_amount,";
		sqlString +="    NVL(rcbd.dealtype_10_amount, 0) AS dealtype_10_amount,";
		sqlString +="    NVL(rcbd.dealtype_20_amount, 0) AS dealtype_20_amount,";
		sqlString +="    NVL(rcbd.dealtype_20agent_amount, 0) AS dealtype_20agent_amount,";
		sqlString +="    NVL(rcbd.ctb_amount, 0) AS ctb_amount,";
		sqlString +="    NVL(rcbd.paid_autocollect_amount, 0) AS paid_autocollect_amount,";
		sqlString +="    NVL(tab_deal.transfer_amount, 0) AS transfer_amount";
		sqlString +="  FROM rc_balance_daily_finance rcbd,";
		sqlString +="    b_account_info bai,";
		sqlString +="    b_agent ba,";
		sqlString +="    (SELECT t.account_id,";
		sqlString +="      t.report_date      AS log_time,";
		sqlString +="      SUM(t.deal_amount) AS transfer_amount";
		sqlString +="    FROM tc_bank_deal_agent t";
		//作为参数传入
		sqlString = sqlString +"      WHERE t.province_center_id in (:provinceIdSet) ";
		sqlString =sqlString + "    AND t.report_date          >= TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss'))";
		sqlString =sqlString + "    AND t.report_date           < TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss')) + 1";
		sqlString +="    GROUP BY t.account_id,";
		sqlString +="      t.report_date";
		sqlString +="    ) tab_deal";
		
		sqlString +="  WHERE bai.province_center_id = ba.province_center_id";
		sqlString +="  AND bai.account_holder_id    = ba.agent_id";
		sqlString +="  AND bai.account_type_code    = 5";
		
		//作为参数传入
		sqlString = sqlString + "  AND rcbd.province_center_id  in(:provinceIdSet) ";
		
		sqlString +="  AND rcbd.account_id          = bai.account_id";
		sqlString +="  AND rcbd.report_date         = tab_deal.log_time(+)";
		sqlString +="  AND rcbd.account_id          = tab_deal.account_id(+)";
		sqlString +="  AND rcbd.province_center_id  = bai.province_center_id ";
		
		//作为参数传入
		sqlString = sqlString + "  and ba.agent_no in(:agentNoSet) ";
		sqlString = sqlString + "  AND rcbd.report_date BETWEEN TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss')) AND TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))";
		
		sqlString +="  ) a ";
		sqlString +="GROUP BY a.agent_name,a.agent_no,a.account_no";
		
		String orderColumns = "";
		Sort sort = pageable.getSort();
		Iterator<Order> orderIter = sort.iterator();
		while(orderIter.hasNext()){
			Order order = orderIter.next();
			orderColumns += order.getProperty();
			orderColumns += " ";
			orderColumns += order.getDirection() == Direction.ASC ? " asc":" desc";
			if(orderIter.hasNext()){
				orderColumns += ",";
			}
		}
		if(StringUtils.isNotEmpty(orderColumns)){
			sqlString = sqlString + " order by " + orderColumns; 
		}
		
		Query query = em.createNativeQuery(sqlString);
		query.unwrap(SQLQuery.class)
		.addScalar("agentCode", StringType.INSTANCE)
		.addScalar("agentName", StringType.INSTANCE)
		.addScalar("agentAccountCode", StringType.INSTANCE)
		
		.addScalar("balanceAmountBeforeSettled", BigDecimalType.INSTANCE)
		.addScalar("balanceAmountAfterSettled", BigDecimalType.INSTANCE)
		.addScalar("paidAmount", BigDecimalType.INSTANCE)
		.addScalar("agentAllocatedAmount", BigDecimalType.INSTANCE)
		.addScalar("ctbAmount", BigDecimalType.INSTANCE)
		.addScalar("paidAutocollectAmount", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryAgentHistoryBalanceResult.class));

		query.setParameter("startDateStr", startDateStr);
		query.setParameter("endDateStr", endDateStr);
		query.setParameter("agentNoSet", agentNoSet);
		query.setParameter("provinceIdSet", provinceIdSet);
		
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		countQuery.setParameter("startDateStr", startDateStr);
		countQuery.setParameter("endDateStr", endDateStr);
		countQuery.setParameter("agentNoSet", agentNoSet);
		countQuery.setParameter("provinceIdSet", provinceIdSet);
		
		 return PageableExecutionUtils.getPage(query.getResultList(), pageable, new TotalSupplier() {
				@Override
				public long get() {
					return executeCountQuery(countQuery);
				}
			});
	}
	
	@SuppressWarnings("unchecked")
	private static Long executeCountQuery(Query query) {
		List<BigDecimal> totals = query.getResultList();
		return totals.get(0).longValue();
	}

/*	@Override
	public List<Object[]> queryProvinceIdAccountIdByAgentNo(List<String> agentNoList) {
		String sqlString = "SELECT DISTINCT t1.PROVINCE_CENTER_ID,t1.ACCOUNT_ID "
				+ "FROM B_ACCOUNT_INFO t1, b_agent t2 "
				+ "WHERE t1.ACCOUNT_HOLDER_ID = t2.AGENT_ID and t2.AGENT_NO in(";
		for(int i = 0; i < agentNoList.size(); i ++){
			String agentNo = agentNoList.get(i);
			sqlString = sqlString + "'" + agentNo + "'";
			if(i < agentNoList.size() -1){
				sqlString +=",";
			}
		}
		sqlString +=")";
		return  em.createNativeQuery(sqlString).getResultList();
	}*/
}
