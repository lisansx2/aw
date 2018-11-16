package cn.com.cslc.aw.balance.agentbalance.repository;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

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
import com.google.common.collect.Lists;

import cn.com.cslc.aw.balance.agentbalance.dto.QueryAgentBalanceResult;


public class AgentBalanceRepositoryImpl implements AgentBalanceRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryAgentBalanceResult> queryAgentBalanceResultByAgentCodes(
			List<String> agentCodeList, Pageable pageable) {
		
		String sqlString = "SELECT ba.agent_name agentName,ba.agent_no agentCode,bai.account_no agentAccountCode,";
		sqlString += "f_getavailableamount(rcbd.balance_amount,";
		sqlString += "bai.credit_amount,";
		sqlString += "bai.attached_balance_amount,";
		sqlString += "bai.temp_credit_begin_time,";
		sqlString += "bai.temp_credit_available_length,";
		sqlString += "bai.temp_credit_amount,";
		sqlString += "SYSDATE) computerAvalibleBanlance, rcbd.balance_amount computerAccountBalance,";
		sqlString += "decode(bai.account_status_code, 0, '已冻结', 10, '已启用') accountState";
		sqlString += "  FROM bc_account_balance rcbd, b_account_info bai, b_agent ba";
		sqlString += " WHERE bai.province_center_id = ba.province_center_id";
		sqlString += "   AND rcbd.province_center_id = bai.province_center_id";
		sqlString += "   AND bai.account_holder_id = ba.agent_id";
		sqlString += "  AND rcbd.account_id = bai.account_id";
		sqlString += "   AND bai.account_type_code = 5 ";
				
		if(!agentCodeList.isEmpty()){
			sqlString += "   and Ba.Agent_No in (:agentNo)";
		}else{
			sqlString += "   and Ba.Agent_No is null";
		}

		
		String orderColumns = " order by ";
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
			sqlString = sqlString + orderColumns; 
		}
		
		Query query = em.createNativeQuery(sqlString);
		query.unwrap(SQLQuery.class)
		.addScalar("agentName", StringType.INSTANCE)
		.addScalar("agentCode", StringType.INSTANCE)
		.addScalar("agentAccountCode", StringType.INSTANCE)
		.addScalar("computerAvalibleBanlance", BigDecimalType.INSTANCE)
		.addScalar("computerAccountBalance", BigDecimalType.INSTANCE)
		.addScalar("accountState", StringType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryAgentBalanceResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if(!agentCodeList.isEmpty()){
			query.setParameter("agentNo", agentCodeList);
			countQuery.setParameter("agentNo", agentCodeList);
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
}
