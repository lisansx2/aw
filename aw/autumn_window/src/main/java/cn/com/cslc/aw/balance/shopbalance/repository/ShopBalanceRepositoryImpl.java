package cn.com.cslc.aw.balance.shopbalance.repository;

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

import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceCondition;
import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceResult;


public class ShopBalanceRepositoryImpl implements ShopBalanceRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopBalanceResult> queryShopBalanceResultByCondition(
			QueryShopBalanceCondition queryShopBalanceCondition, Pageable pageable) {
		String accountStateCode = queryShopBalanceCondition.getAccountStateCode();
		String shopCode  = queryShopBalanceCondition.getShopCode();
		
		String sqlString = "select S2.*,s1.orgName from (";
		sqlString += "SELECT t1.CODE agentCode,t3.CODE orgCode,t3.NAME orgName ";
		sqlString += " FROM SYS_AGENT t1,  sys_org_agent t2, sys_org t3 ";
		sqlString += " WHERE t1.ID   = t2.AGENT_ID AND t2.ORG_ID = t3.ID) s1, ";

		sqlString += "(SELECT s.shop_no shopCode, bai.account_no accountCode, ba.agent_name agentName, ba.agent_no agentCode,";
		sqlString += "f_getavailableamount(rcbd.balance_amount,";
		sqlString += "bai.credit_amount,";
		sqlString += "bai.attached_balance_amount,";
		sqlString += "bai.temp_credit_begin_time,";
		sqlString += "bai.temp_credit_available_length,";
		sqlString += "bai.temp_credit_amount,";
		sqlString += "SYSDATE) computerAvalibleBanlance, rcbd.balance_amount computerAccountBalance,";
		sqlString += "decode(bai.account_status_code, 0, '已冻结', 10, '已启用') accountStateName";
		sqlString += " FROM bc_account_balance rcbd, b_account_info bai, b_shop s, b_agent ba";
		sqlString += " WHERE bai.province_center_id = ba.province_center_id";
		sqlString += " AND rcbd.province_center_id = bai.province_center_id";
		sqlString += " AND rcbd.account_id = bai.account_id";
		sqlString += " AND bai.account_holder_id = s.shop_id";
		sqlString += " AND s.agent_id = ba.agent_id";
		sqlString +=" AND bai.account_type_code = 2";
		sqlString +=" AND ba.agent_type_code = 10";
		
		if (!StringUtils.isEmpty(accountStateCode)) {
			sqlString = sqlString + " and bai.account_status_code = :accountStateCode ";
		}
		
		if (!StringUtils.isEmpty(shopCode)) {
			sqlString = sqlString + " and s.shop_no  like :shopCode";
		}
		
		List<String> agentCodeList = queryShopBalanceCondition.getAgentCodeList();

		if(!agentCodeList.isEmpty()){
			sqlString += "   and ba.agent_no in (:agentCodeList)) s2 ";
		}else{
			sqlString += "   and ba.agent_no is null) s2 ";
		}
		
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
		.addScalar("accountCode", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		.addScalar("computerAvalibleBanlance", BigDecimalType.INSTANCE)
		.addScalar("computerAccountBalance", BigDecimalType.INSTANCE)
		.addScalar("accountStateName", StringType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopBalanceResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if (!StringUtils.isEmpty(accountStateCode)) {
			query.setParameter("accountStateCode", accountStateCode);
			countQuery.setParameter("accountStateCode", accountStateCode);
		}
		if (!StringUtils.isEmpty(shopCode)) {
			query.setParameter("shopCode", "%"+shopCode+"%");
			countQuery.setParameter("shopCode", "%"+shopCode+"%");
		}

		if(!agentCodeList.isEmpty()){
			query.setParameter("agentCodeList", agentCodeList);
			countQuery.setParameter("agentCodeList", agentCodeList);
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
