package cn.com.cslc.aw.reports.shopcommission.repository;

import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionResult;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class ShopCommissionReportRepositoryImpl implements ShopCommissionReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopCommissionResult> queryByCondition(QueryShopCommissionCondition queryCondition,
															Pageable pageable) {

		Set<String> techSystemIdSet = queryCondition.getTechSystemIdSet();
		String beginSalePeriodNO = queryCondition.getBeginSalePeriodNO();
		String endSalePeriodNO = queryCondition.getEndSalePeriodNO();
		String shopNo = queryCondition.getShopNo();
		Set<String> provinceIdSet = queryCondition.getProvinceIdSet();
		Set<String> agentCodeSet = queryCondition.getAgentCodeSet();

		String sqlString = " select /*+ full(rsd) parallel(rsd 2) */";
		sqlString +="  sysorg.name orgName,";
		sqlString +="  ag.agent_no,";
		sqlString +="  ag.agent_name,";
		sqlString +="  shop.shop_no shopNo,";
		sqlString +="  shop.shop_name,";
		sqlString +="  term.terminal_no terminalNo,";
		sqlString +="  /*佣金销量*/";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 120, rsd.settled_amount, 100, rsd.settled_amount, 0)), 0) as commissionSaleAmount,";
		sqlString +="  /*兑奖金额*/";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 30, rsd.settled_amount, 0)), 0) as paidAmount,";
		sqlString +="  /*额度佣金*/";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 81, rsd.settled_amount, 91, rsd.settled_amount, 0)), 0) as quotaCommissionAmount,";
		sqlString +="  /*应付佣金*/";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 80, rsd.settled_amount, 0)), 0) +";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 90, rsd.settled_amount, 0)), 0) -";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 82, rsd.settled_amount, 0)), 0) -";
		sqlString +="  nvl(sum(decode(rsd.settled_item_type_code, 83, rsd.settled_amount, 0)), 0) as commissionAmount,";
		sqlString +="  /*现金佣金*/";
		sqlString +="  round(nvl(sum(decode(rsd.settled_item_type_code, 80, rsd.settled_amount, 0)), 0) +";
		sqlString +="        nvl(sum(decode(rsd.settled_item_type_code, 90, rsd.settled_amount, 0)), 0) -";
		sqlString +="        nvl(sum(decode(rsd.settled_item_type_code, 82, rsd.settled_amount, 0)), 0) -";
		sqlString +="        nvl(sum(decode(rsd.settled_item_type_code, 83, rsd.settled_amount, 0)), 0) -";
		sqlString +="        nvl(sum(decode(rsd.settled_item_type_code, 81, rsd.settled_amount, 91, rsd.settled_amount, 0)), 0),";
		sqlString +="        2) as cashCommissionAmount";
		sqlString +="   from r_sale_period_settled_data rsd, b_shop shop, b_terminal term, b_agent ag, b_province_center p, ";
		sqlString +="   (select t1.code ageng_no, t3.code , t3.name from sys_agent t1, sys_org_agent t2, sys_org t3 where t1.id = t2.agent_id and t2.org_id = t3.id) sysorg";
		sqlString +="  where rsd.province_center_id = p.province_center_id";
		sqlString +="    and p.province_center_no in (:provinceIdSet)";
		sqlString +="    and rsd.sale_period_no between :beginSalePeriodNO and :endSalePeriodNO";
		sqlString +="    and rsd.tech_system_id in (:techSystemIdSet)";
		sqlString +="    and shop.province_center_id = p.province_center_id";
		sqlString +="    and rsd.shop_id = shop.shop_id";
		sqlString +="    and term.province_center_id = p.province_center_id";
		sqlString +="    and rsd.terminal_id = term.terminal_id";
		sqlString +="    and ag.agent_id = shop.agent_id";
		if(!agentCodeSet.isEmpty()){
			sqlString +="    and ag.agent_no in (:agentCodeSet)";
		}else{
			sqlString +="    and ag.agent_no is null";
		}

		sqlString +="    and ag.agent_no = sysorg.ageng_no";

		if(StringUtils.isNotEmpty(queryCondition.getShopNo())){
			sqlString +="    and shop.shop_no like :shopNo";
		}
		sqlString +="  group by sysorg.name, ag.agent_no, ag.agent_name, rsd.province_center_id, rsd.shop_id, rsd.account_id, shop.shop_no, shop.shop_name,term.terminal_no";

		sqlString +="  order by ag.agent_no, shop_no, terminal_no";
//		String orderColumns = "";
//		Sort sort = pageable.getSort();
//		Iterator<Sort.Order> orderIter = sort.iterator();
//		while(orderIter.hasNext()){
//			Sort.Order order = orderIter.next();
//			orderColumns += order.getProperty();
//			orderColumns += " ";
//			orderColumns += order.getDirection() == Sort.Direction.ASC ? " asc":" desc";
//			if(orderIter.hasNext()){
//				orderColumns += ",";
//			}
//		}
//		if(StringUtils.isNotEmpty(orderColumns)){
//			sqlString = sqlString + " order by " + orderColumns;
//		}


		Query query = em.createNativeQuery(sqlString);
		query.unwrap(SQLQuery.class)
		.addScalar("shopNo", StringType.INSTANCE)
		.addScalar("terminalNo", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		
		.addScalar("commissionSaleAmount", BigDecimalType.INSTANCE)
		.addScalar("paidAmount", BigDecimalType.INSTANCE)
		.addScalar("quotaCommissionAmount", BigDecimalType.INSTANCE)
		.addScalar("commissionAmount", BigDecimalType.INSTANCE)
		.addScalar("cashCommissionAmount", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopCommissionResult.class));

		query.setParameter("techSystemIdSet", techSystemIdSet);
		query.setParameter("beginSalePeriodNO", beginSalePeriodNO);
		query.setParameter("endSalePeriodNO", endSalePeriodNO);
		query.setParameter("provinceIdSet", provinceIdSet);

		if(StringUtils.isNotEmpty(shopNo)){
			query.setParameter("shopNo", "%" + shopNo + "%");
		}
		if(!agentCodeSet.isEmpty()){
			query.setParameter("agentCodeSet", agentCodeSet);
		}

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);

		countQuery.setParameter("techSystemIdSet", techSystemIdSet);
		countQuery.setParameter("beginSalePeriodNO", beginSalePeriodNO);
		countQuery.setParameter("endSalePeriodNO", endSalePeriodNO);
		countQuery.setParameter("provinceIdSet", provinceIdSet);

		if(StringUtils.isNotEmpty(shopNo)){
			query.setParameter("shopNo", "%" + shopNo + "%");
		}
		if(!agentCodeSet.isEmpty()){
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

}
