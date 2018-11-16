package cn.com.cslc.aw.reports.shophistorybalance.repository;

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

import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceCondition;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceResult;

public class ShopHistoryBalanceReportRepositoryImpl implements ShopHistoryBalanceReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopHistoryBalanceResult> queryByCondition(QueryShopHistoryBalanceCondition queryCondition,
			Pageable pageable) {
		
		String startDateStr = queryCondition.getStartDate() + " 00:00:00";
		String endDateStr = queryCondition.getEndDate() + " 23:59:59";
		
		Set<String> provinceIdSet = queryCondition.getProvinceIdSet();
		
		String sqlString = "select S2.*,s1.orgName from (";
		sqlString += "SELECT t1.CODE agentCode,t3.CODE orgCode,t3.NAME orgName ";
		sqlString += " FROM SYS_AGENT t1,  sys_org_agent t2, sys_org t3 ";
		sqlString += " WHERE t1.ID   = t2.AGENT_ID AND t2.ORG_ID = t3.ID) s1, ";
		
		sqlString += "(SELECT  ";
		sqlString +="  aa.agent_no as agentCode,";
		sqlString +="  aa.shop_no as shopCode,";
		sqlString +="  trunc(aa.report_date) as reportDate,";
		sqlString +="  SUM(NVL(aa.prior_amount, 0)) as balanceAmountBeforeSettled,";
		sqlString +="  SUM(NVL(aa.post_amount, 0)) as balanceAmountAfterSettled,";
		sqlString +="  SUM(aa.deal_amount) as paidAmount,";
		sqlString +="  SUM(aa.transfer_amount) as agentAllocatedAmount,";
		sqlString +="  SUM(aa.sale_amount) as netSalesAmount,";
		sqlString +="  SUM(aa.paid_amount) as cashPrizeAmount,";
		sqlString +="  SUM(aa.paid_quota_amount) as cashPrizeTransferBalanceAmount, ";
		sqlString +="  SUM(aa.wxpay_amount) as wxpayAmount, ";
		sqlString +="  SUM(aa.wxpay_fee) as wxpayFee, ";
		sqlString +="  SUM(aa.alipay_amount) as alipayAmount, ";
		sqlString +="  SUM(aa.alipay_fee) as alipayFee, ";
		sqlString +="  SUM(aa.refund_amount) as refundAmount, ";
		sqlString +="  SUM(aa.paid_autocollect_amount) as paidAutoCollectAmount ";
		sqlString +="FROM";
		sqlString +="  (SELECT a.agent_no,";
		sqlString +="    s.shop_no,";
		sqlString +="    rc.report_date,";
		
		sqlString = sqlString +"    TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss'))    AS min_report_date,";
		sqlString = sqlString +"    TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))  AS max_report_date,";
		
		sqlString +="    NVL(rc.prior_amount, 0) AS prior_amount,";
		sqlString +="    rc.post_amount AS post_amount,";
		sqlString +="    NVL(rc.dealtype_10_amount, 0) AS deal_amount,";
		sqlString +="    NVL(rc.sale_amount, 0) AS sale_amount,";
		sqlString +="    NVL(rc.paid_amount, 0) AS paid_amount,";
		sqlString +="    abs(nvl(rc.dealtype_70_amount, 0))*(-1) AS paid_quota_amount,";
		sqlString +="    nvl(rc.dealtype_10shop_amount, 0) AS transfer_amount, ";
		sqlString +="    nvl(rc.wxpay_amount, 0)+nvl(rc.wxpay_fee, 0) AS wxpay_amount, ";
		sqlString +="    nvl(rc.wxpay_fee, 0) AS wxpay_fee, ";
		sqlString +="    nvl(rc.alipay_amount, 0)+nvl(rc.alipay_fee, 0) AS alipay_amount, ";
		sqlString +="    nvl(rc.alipay_fee, 0) AS alipay_fee, ";
		sqlString +="    nvl(rc.refund_amount, 0) AS refund_amount, ";
		sqlString +="    nvl(rc.paid_autocollect_amount, 0) AS paid_autocollect_amount ";
		sqlString +="  FROM rc_balance_daily_finance rc,";
		sqlString +="    b_account_info b,";
		sqlString +="    b_shop s,";
		sqlString +="    b_agent a";
		sqlString +="  WHERE rc.account_id       = b.account_id";
		sqlString +="  AND rc.province_center_id = b.province_center_id";
		sqlString +="  AND rc.province_center_id = s.province_center_id";
		sqlString +="  AND rc.province_center_id = a.province_center_id";
		sqlString +="  AND b.account_holder_id   = s.shop_id";
		sqlString +="  AND s.agent_id            = a.agent_id";
		sqlString +="  AND b.account_type_code   = 2";
		sqlString +="  AND a.agent_type_code     = 10";
		
		//作为参数传入
		sqlString = sqlString + "  AND rc.province_center_id  in(:provinceIdSet) ";
		sqlString = sqlString + "  AND rc.report_date BETWEEN TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss')) AND TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))";
		
		String shopNo = queryCondition.getShopCode();
		if(shopNo != null && !"".equals(shopNo)){
			sqlString = sqlString + "  AND s.shop_no like :shopNo";
		}
		
		Set<String> agentCodeSet = queryCondition.getAgentCodeSet();
		if(!agentCodeSet.isEmpty()){
			sqlString = sqlString + "  and a.agent_no in (:agentCodeSet)";
		}else{
			sqlString = sqlString + "  and a.agent_no is null ";
		}

		sqlString +="  ) aa ";
		sqlString +="GROUP BY aa.shop_no,aa.agent_no,aa.report_date ) s2";
		sqlString += " where s1.agentCode = s2.agentCode "; 
		
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
		.addScalar("shopCode", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		.addScalar("reportDate", StringType.INSTANCE)

		.addScalar("balanceAmountBeforeSettled", BigDecimalType.INSTANCE)
		.addScalar("balanceAmountAfterSettled", BigDecimalType.INSTANCE)
		.addScalar("paidAmount", BigDecimalType.INSTANCE)
		.addScalar("agentAllocatedAmount", BigDecimalType.INSTANCE)
		.addScalar("netSalesAmount", BigDecimalType.INSTANCE)
		.addScalar("cashPrizeAmount", BigDecimalType.INSTANCE)
		.addScalar("cashPrizeTransferBalanceAmount", BigDecimalType.INSTANCE)
		.addScalar("wxpayAmount", BigDecimalType.INSTANCE)
		.addScalar("wxpayFee", BigDecimalType.INSTANCE)
		.addScalar("alipayAmount", BigDecimalType.INSTANCE)
		.addScalar("alipayFee", BigDecimalType.INSTANCE)
		.addScalar("refundAmount", BigDecimalType.INSTANCE)
		.addScalar("paidAutoCollectAmount", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopHistoryBalanceResult.class));

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		query.setParameter("startDateStr", startDateStr);
		countQuery.setParameter("startDateStr", startDateStr);
		query.setParameter("endDateStr", endDateStr);
		countQuery.setParameter("endDateStr", endDateStr);
		query.setParameter("provinceIdSet", provinceIdSet);
		countQuery.setParameter("provinceIdSet", provinceIdSet);
		
		if(StringUtils.isNotEmpty(shopNo)){
			query.setParameter("shopNo", "%" + shopNo + "%");
			countQuery.setParameter("shopNo", "%" + shopNo + "%");
		}

		if(!agentCodeSet.isEmpty()){
			query.setParameter("agentCodeSet", agentCodeSet);
			countQuery.setParameter("agentCodeSet", agentCodeSet);
		}
		
		 return PageableExecutionUtils.getPage(query.getResultList(), pageable, new TotalSupplier() {
				@Override
				public long get() {
					return executeCountQuery(countQuery);
				}
			});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopHistoryBalanceResult> queryByConditionNatural(QueryShopHistoryBalanceCondition queryCondition,
			Pageable pageable) {
		
		String startDateStr = queryCondition.getStartDate() + " 00:00:00";
		String endDateStr = queryCondition.getEndDate() + " 23:59:59";
		
		Set<String> provinceIdSet = queryCondition.getProvinceIdSet();
		
		String sqlString = "select S2.*,s1.orgName from (";
		sqlString += "SELECT t1.CODE agentCode,t3.CODE orgCode,t3.NAME orgName ";
		sqlString += " FROM SYS_AGENT t1,  sys_org_agent t2, sys_org t3 ";
		sqlString += " WHERE t1.ID   = t2.AGENT_ID AND t2.ORG_ID = t3.ID) s1, ";
		
		sqlString += "(SELECT  ";
		sqlString +="  aa.agent_no as agentCode,";
		sqlString +="  aa.shop_no as shopCode,";
		sqlString +="  trunc(aa.report_date) as reportDate,";
		sqlString +="  SUM(NVL(aa.prior_amount, 0)) as balanceAmountBeforeSettled,";
		sqlString +="  SUM(NVL(aa.post_amount, 0)) as balanceAmountAfterSettled,";
		sqlString +="  SUM(aa.deal_amount) as paidAmount,";
		sqlString +="  SUM(aa.transfer_amount) as agentAllocatedAmount,";
		sqlString +="  SUM(aa.sale_amount) as netSalesAmount,";
		sqlString +="  SUM(aa.paid_amount) as cashPrizeAmount,";
		sqlString +="  SUM(aa.paid_quota_amount) as cashPrizeTransferBalanceAmount, ";
		sqlString +="  SUM(aa.wxpay_amount) as wxpayAmount, ";
		sqlString +="  SUM(aa.wxpay_fee) as wxpayFee, ";
		sqlString +="  SUM(aa.alipay_amount) as alipayAmount, ";
		sqlString +="  SUM(aa.alipay_fee) as alipayFee, ";
		sqlString +="  SUM(aa.refund_amount) as refundAmount, ";
		sqlString +="  SUM(aa.paid_autocollect_amount) as paidAutoCollectAmount ";
		sqlString +="FROM";
		sqlString +="  (SELECT a.agent_no,";
		sqlString +="    s.shop_no,";
		sqlString +="    rc.report_date,";
		
		sqlString = sqlString +"    TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss'))    AS min_report_date,";
		sqlString = sqlString +"    TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))  AS max_report_date,";
		
		sqlString +="    NVL(rc.prior_amount, 0) AS prior_amount,";
		sqlString +="    rc.post_amount AS post_amount,";
		sqlString +="    NVL(rc.dealtype_10_amount, 0) AS deal_amount,";
		sqlString +="    NVL(rc.sale_amount, 0) AS sale_amount,";
		sqlString +="    NVL(rc.paid_amount, 0) AS paid_amount,";
		sqlString +="    abs(nvl(rc.dealtype_70_amount, 0))*(-1) AS paid_quota_amount,";
		sqlString +="    nvl(rc.dealtype_10shop_amount, 0) AS transfer_amount, ";
		sqlString +="    nvl(rc.wxpay_amount, 0)+nvl(rc.wxpay_fee, 0) AS wxpay_amount, ";
		sqlString +="    nvl(rc.wxpay_fee, 0) AS wxpay_fee, ";
		sqlString +="    nvl(rc.alipay_amount, 0)+nvl(rc.alipay_fee, 0) AS alipay_amount, ";
		sqlString +="    nvl(rc.alipay_fee, 0) AS alipay_fee, ";
		sqlString +="    nvl(rc.refund_amount, 0) AS refund_amount, ";
		sqlString +="    nvl(rc.paid_autocollect_amount, 0) AS paid_autocollect_amount ";
		sqlString +="  FROM RC_BALANCE_DAILY_NATURAL rc,";
		sqlString +="    b_account_info b,";
		sqlString +="    b_shop s,";
		sqlString +="    b_agent a";
		sqlString +="  WHERE rc.account_id       = b.account_id";
		sqlString +="  AND rc.province_center_id = b.province_center_id";
		sqlString +="  AND rc.province_center_id = s.province_center_id";
		sqlString +="  AND rc.province_center_id = a.province_center_id";
		sqlString +="  AND b.account_holder_id   = s.shop_id";
		sqlString +="  AND s.agent_id            = a.agent_id";
		sqlString +="  AND b.account_type_code   = 2";
		sqlString +="  AND a.agent_type_code     = 10";
		
		//作为参数传入
		sqlString = sqlString + "  AND rc.province_center_id  in(:provinceIdSet) ";
		sqlString = sqlString + "  AND rc.report_date BETWEEN TRUNC(to_date(:startDateStr, 'yyyy-mm-dd hh24:mi:ss')) AND TRUNC(to_date(:endDateStr, 'yyyy-mm-dd hh24:mi:ss'))";
		
		String shopNo = queryCondition.getShopCode();
		if(shopNo != null && !"".equals(shopNo)){
			sqlString = sqlString + "  AND s.shop_no like :shopNo";
		}
		
		Set<String> agentCodeSet = queryCondition.getAgentCodeSet();
		if(!agentCodeSet.isEmpty()){
			sqlString = sqlString + "  and a.agent_no in (:agentCodeSet)";
		}else{
			sqlString = sqlString + "  and a.agent_no is null ";
		}

		sqlString +="  ) aa ";
		sqlString +="GROUP BY aa.shop_no,aa.agent_no,aa.report_date ) s2";
		sqlString += " where s1.agentCode = s2.agentCode "; 
		
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
		.addScalar("shopCode", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		.addScalar("reportDate", StringType.INSTANCE)

		.addScalar("balanceAmountBeforeSettled", BigDecimalType.INSTANCE)
		.addScalar("balanceAmountAfterSettled", BigDecimalType.INSTANCE)
		.addScalar("paidAmount", BigDecimalType.INSTANCE)
		.addScalar("agentAllocatedAmount", BigDecimalType.INSTANCE)
		.addScalar("netSalesAmount", BigDecimalType.INSTANCE)
		.addScalar("cashPrizeAmount", BigDecimalType.INSTANCE)
		.addScalar("cashPrizeTransferBalanceAmount", BigDecimalType.INSTANCE)
		.addScalar("wxpayAmount", BigDecimalType.INSTANCE)
		.addScalar("wxpayFee", BigDecimalType.INSTANCE)
		.addScalar("alipayAmount", BigDecimalType.INSTANCE)
		.addScalar("alipayFee", BigDecimalType.INSTANCE)
		.addScalar("refundAmount", BigDecimalType.INSTANCE)
		.addScalar("paidAutoCollectAmount", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopHistoryBalanceResult.class));

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		query.setParameter("startDateStr", startDateStr);
		countQuery.setParameter("startDateStr", startDateStr);
		query.setParameter("endDateStr", endDateStr);
		countQuery.setParameter("endDateStr", endDateStr);
		query.setParameter("provinceIdSet", provinceIdSet);
		countQuery.setParameter("provinceIdSet", provinceIdSet);
		
		if(StringUtils.isNotEmpty(shopNo)){
			query.setParameter("shopNo", "%" + shopNo + "%");
			countQuery.setParameter("shopNo", "%" + shopNo + "%");
		}

		if(!agentCodeSet.isEmpty()){
			query.setParameter("agentCodeSet", agentCodeSet);
			countQuery.setParameter("agentCodeSet", agentCodeSet);
		}
		
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

	@Override
	public List<Object[]> queryProvinceIdByAgentNo(List<String> agentNoList) {
		String sqlString = "SELECT DISTINCT t.PROVINCE_CENTER_ID "
				+ "FROM b_agent t "
				+ "WHERE  t.AGENT_NO in(";
		for(int i = 0; i < agentNoList.size(); i ++){
			String agentNo = agentNoList.get(i);
			sqlString = sqlString + "'" + agentNo + "'";
			if(i < agentNoList.size() -1){
				sqlString +=",";
			}
		}
		sqlString +=")";
		return  em.createNativeQuery(sqlString).getResultList();
	}
}
