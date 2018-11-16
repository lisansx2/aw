package cn.com.cslc.aw.reports.shopinstantpromotion.repository;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
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

import cn.com.cslc.aw.game.dto.C_GameDefDto;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.InstantPromotion;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionCondition;
import cn.com.cslc.aw.reports.shopinstantpromotion.dto.QueryShopInstantPromotionResult;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeResult;



public class ShopInstantPromotionReportRepositoryImpl implements ShopInstantPromotionReportRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopInstantPromotionResult> queryByCondition(QueryShopInstantPromotionCondition queryCondition,
			Pageable pageable) {
		String sqlString = "		SELECT ibs.shop_no shopNo,";
		sqlString += "		  so.name orgName,";
		sqlString += "		  ispa.promotion_name promotionName,";
		sqlString += "		  sum(ispa.bonus)  sumBonus";
		sqlString += "		FROM";
		sqlString += "		  sys_org so ";
		sqlString += "		    join sys_org_customer soc on so.id=soc.org_id";
		sqlString += "		    join sys_customer sc on sc.id=soc.customer_id";
		sqlString += "		    join instant_md_channel_customer imcc on sc.code=imcc.customer_code";
		sqlString += "		    join instant_b_shop ibs on ibs.channel_customer_id=imcc.row_id";
		sqlString += "		    and imcc.ACTIVE_FLAG='Y'  ";
		sqlString += "		  join instant_sp_promotion_award ispa on ispa.opt_org_type_code=8";
		sqlString += "		  and ispa.deleted_flag='0' ";
		sqlString +="         and ispa.opt_org_id=ibs.shop_id";
		sqlString +="          where ";
		sqlString +="     imcc.ACTIVE_FLAG='Y'";
		sqlString +="     and imcc.deleted_flag='0'";
		sqlString +="     and ibs.DELETED_FLAG='0'";
		sqlString +="     and ibs.shop_no like :shopNo";
		sqlString +="     and  (ispa.award_date BETWEEN to_date(:startDate,'yyyy/MM/dd') AND to_date(:endDate,'yyyy/MM/dd')) ";
		if(!queryCondition.getCustomerCodeSet().isEmpty()){
			sqlString += "		  and imcc.customer_code in (:customerCodeSet) ";
		}else{
			sqlString += "		 and imcc.customer_code is null ";
		}
		if(StringUtils.isNotEmpty(queryCondition.getPromotionNo())){
			sqlString += "		  and  ispa.promotion_code=:promotionCode ";
		}
		sqlString +="     group by ibs.shop_no,so.name,ispa.promotion_code,ispa.promotion_name";
		/*
		select  
ibs.shop_no shopNo,
so.name orgName,
ispa.promotion_name promotionName,
sum(ispa.bonus)
from 
sys_org so 
join sys_org_customer soc on so.id=soc.org_id
join sys_customer sc on sc.id=soc.customer_id
join instant_md_channel_customer imcc on sc.code=imcc.customer_code
join instant_b_shop ibs on ibs.channel_customer_id=imcc.row_id
and imcc.ACTIVE_FLAG='Y' 
join instant_sp_promotion_award ispa 
on ispa.opt_org_type_code=8
and ispa.deleted_flag='0'
and ispa.opt_org_id=ibs.shop_id
where imcc.ACTIVE_FLAG='Y' 
and imcc.deleted_flag='0'
and ibs.del_flag='0'
and ibs.shop_no like :shopNo
and imcc.customer_code in (:customerCodeSet)
and  (ispa.award_date BETWEEN to_date(:startDate,'yyyy/MM/dd') AND to_date(:endDate,'yyyy/MM/dd')) 
and  ispa.promotion_code=:promotionCode
group by ibs.shop_no,so.name,ispa.promotion_code,ispa.promotion_name	
		*/
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
		.addScalar("shopNo", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		.addScalar("promotionName", StringType.INSTANCE)
		.addScalar("sumBonus", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopInstantPromotionResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if(!queryCondition.getCustomerCodeSet().isEmpty()){
			query.setParameter("customerCodeSet", queryCondition.getCustomerCodeSet());
			countQuery.setParameter("customerCodeSet", queryCondition.getCustomerCodeSet());
		}
		
		if(StringUtils.isNotEmpty(queryCondition.getPromotionNo())){
			query.setParameter("promotionCode", queryCondition.getPromotionNo());
			countQuery.setParameter("promotionCode", queryCondition.getPromotionNo());
		}

		
		query.setParameter("startDate", queryCondition.getStartDate());
		countQuery.setParameter("startDate", queryCondition.getStartDate());
		
		query.setParameter("endDate", queryCondition.getEndDate());
		countQuery.setParameter("endDate", queryCondition.getEndDate());
		
		if(StringUtils.isNotEmpty(queryCondition.getShopNo())){
			query.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
			countQuery.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
		}else{
			query.setParameter("shopNo", "%%");
			countQuery.setParameter("shopNo", "%%");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<InstantPromotion> findPromotionByCustomerCodes(Set<String> userCustomerCodeSet) {
		
		String sql = " select ispa.promotion_code as  \"promotionNo\", ispa.promotion_name as \"promotionName\"";
		sql += " from sys_customer sc ";
		sql += " join instant_md_channel_customer imcc on sc.code=imcc.customer_code";
		sql += " join instant_b_shop ibs on ibs.channel_customer_id=imcc.row_id";
		sql +=" and imcc.ACTIVE_FLAG='Y'";
		sql +=" join instant_sp_promotion_award ispa";
		sql +=" on ispa.opt_org_type_code=8";
		sql +=" and ispa.deleted_flag='0'";
		sql +=" and ispa.opt_org_id=ibs.shop_id";
		sql +=" where imcc.ACTIVE_FLAG='Y'";
		sql +=" and imcc.deleted_flag='0'";
		sql +=" and ibs.deleted_flag='0'";
		if(!userCustomerCodeSet.isEmpty()){
			sql +=" and sc.code in (:customerCodeSet)";
		}
		else{
			sql +=" and sc.code  is null ";
		}
		
		sql+=" group by ispa.promotion_code,ispa.promotion_name";		

		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(InstantPromotion.class ));
		if(!userCustomerCodeSet.isEmpty()){
		query.setParameter("customerCodeSet", userCustomerCodeSet);
		}
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public InstantPromotion findPromotionByPromotionCode(String promotionCode) {
		
		String sql = " select  ip.promotion_code as  \"promotionNo\", ip.promotion_name as \"promotionName\"";
		sql += " from instant_sp_promotion ip ";
		sql +=" where ip.promotion_code=:promotionCode";
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(InstantPromotion.class ));
		query.setParameter("promotionCode", promotionCode);
		return  (InstantPromotion)query.getResultList().get(0);
	}


}
