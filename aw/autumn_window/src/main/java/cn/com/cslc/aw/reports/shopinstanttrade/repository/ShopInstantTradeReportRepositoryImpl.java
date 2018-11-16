package cn.com.cslc.aw.reports.shopinstanttrade.repository;

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

import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeResult;

public class ShopInstantTradeReportRepositoryImpl implements ShopInstantTradeReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopInstantTradeResult> queryByCondition(QueryShopInstantTradeCondition queryCondition,
			Pageable pageable) {

		String sqlString = "		SELECT a.shopNo,";
		sqlString += "		  b.orgName,";
		sqlString += "		  a.paidTicketNumSum,";
		sqlString += "		  a.paidTicketAmountSum,";
		sqlString += "		  a.activeNumSum,";
		sqlString += "		  a.activeAmountSum";
		sqlString += "		FROM";
		sqlString += "		  (SELECT t.SHOP_CODE shopNo,";
		sqlString += "		     im.customer_code customerCode,";
		sqlString += "		    SUM(t.ACTIVATE_PR) activeAmountSum,";
		sqlString += "		    SUM(t.ACTIVATE_PKG_AMT) activeNumSum,";
		sqlString += "		    SUM(t.CLAIM_PR) paidTicketAmountSum,";
		sqlString += "		    SUM(t.CLAIM_TICKET_AMT) paidTicketNumSum ";
		sqlString += "		  FROM INSTANT_RP_BIZ_D_S_2P t, instant_md_channel_customer im ";
		sqlString += "		  WHERE ";
		sqlString += "		   im.row_id=t.channel_customer_id  ";
		if(!queryCondition.getCustomerCodeSet().isEmpty()){
			sqlString += "		AND   im.customer_code in(:customerCodeSet) ";
		}else{
			sqlString += "		AND   im.customer_code is null ";
		}

		if(!queryCondition.getGameNoSet().isEmpty()){
			sqlString += "		  AND t.GAME_CODE in(:gameCodeSet) ";
		}else{
			sqlString += "		  AND t.GAME_CODE is null ";
		}
		

		sqlString += "		  AND (t.BIZ_DATE BETWEEN to_date(:startDate,'yyyy/MM/dd') AND to_date(:endDate,'yyyy/MM/dd')) ";
		sqlString += "		  AND t.SHOP_CODE LIKE :shopNo ";
		sqlString += "		  GROUP BY t.SHOP_CODE,";
		sqlString += "		   im.customer_code";
		sqlString += "		  ) a,";
		sqlString += "		  (SELECT t.CODE orgCode,";
		sqlString += "		    t.NAME orgName,";
		sqlString += "		    t2.CODE customerCode";
		sqlString += "		  FROM sys_org t,";
		sqlString += "		    SYS_ORG_CUSTOMER t1,";
		sqlString += "		    SYS_CUSTOMER t2";
		sqlString += "		  WHERE t.id         =t1.ORG_ID ";
		sqlString += "		  AND t1.CUSTOMER_ID = t2.ID ";
		if(!queryCondition.getCustomerCodeSet().isEmpty()){
			sqlString += "		  AND t2.CODE in (:customerCodeSet) ";
		}else{
			sqlString += "		  AND t2.CODE is null ";
		}

		sqlString += "		  ) b";
		sqlString += "		WHERE a.customerCode = b.customerCode";

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
		.addScalar("paidTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("paidTicketAmountSum", BigDecimalType.INSTANCE)
		//.addScalar("promotionPaidTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("activeNumSum", BigDecimalType.INSTANCE)
		.addScalar("activeAmountSum", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopInstantTradeResult.class));
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
		
		if(!queryCondition.getGameNoSet().isEmpty()){
			query.setParameter("gameCodeSet", queryCondition.getGameNoSet());
			countQuery.setParameter("gameCodeSet", queryCondition.getGameNoSet());
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

}
