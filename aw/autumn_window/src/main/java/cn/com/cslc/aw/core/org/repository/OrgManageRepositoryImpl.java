package cn.com.cslc.aw.core.org.repository;

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
import org.hibernate.type.IntegerType;
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

import cn.com.cslc.aw.core.org.dto.OrgAuthResult;
import cn.com.cslc.aw.core.org.dto.QueryOrgCondition;


public class OrgManageRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<OrgAuthResult> queryByCondition(QueryOrgCondition queryOrgCondition,
			Pageable pageable) {
		
		String sqlString= " select a.id,a.code,a.name,a.parentAgentName,a.hasChildren from( select c.id,c.code,c.name,b.name as parentAgentName,c.has_children as hasChildren  from sys_org c,sys_org b where c.parent_code=b.code and c.org_type_id=2 and c.code <>001 and (c.del_flag is null or c.del_flag=0) ";
		sqlString+= " and c.code like :orgCode";
		sqlString+= " and c.name like :orgName";
		sqlString+=") a";

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
		.addScalar("id", IntegerType.INSTANCE)
		.addScalar("code", StringType.INSTANCE)
		.addScalar("name", StringType.INSTANCE)
		.addScalar("parentAgentName", StringType.INSTANCE)
		.addScalar("hasChildren", IntegerType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(OrgAuthResult.class));

		query.setParameter("orgCode", "%"+queryOrgCondition.getOrgCode().trim()+"%");
		query.setParameter("orgName", "%"+queryOrgCondition.getOrgName().trim()+"%");
		
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		countQuery.setParameter("orgCode", "%"+queryOrgCondition.getOrgCode().trim()+"%");
		countQuery.setParameter("orgName", "%"+queryOrgCondition.getOrgName().trim()+"%");
		
		 return PageableExecutionUtils.getPage(query.getResultList(), pageable, new TotalSupplier() {
				@Override
				public long get() {
					return executeCountQuery(countQuery);
				}
			});
	}
	
	@SuppressWarnings("unchecked")
	public String getOrgCodeNext(String code) {
		
		String sqlString1= " select max(c.code) from sys_org c where c.code like :code and c.parent_code = :parenCode ";
		String sqlString2= " select count(c.id) from sys_org c where c.code like :code and c.parent_code = :parenCode ";
		Query query1 = em.createNativeQuery(sqlString1);
		Query query2 = em.createNativeQuery(sqlString2);
		query1.setParameter("code", code.trim()+"%");
		query1.setParameter("parenCode", code.trim());
		query2.setParameter("code", code.trim()+"%");
		query2.setParameter("parenCode", code.trim());
		List<String> totals1 = query1.getResultList();
		List<BigDecimal> totals2 = query2.getResultList();
		String codeNext = "";
		String orgCode = totals1.get(0);
		Long count = totals2.get(0).longValue();
		if(count==0){
			codeNext = "1" + code + "001";
		}else if (count > 0){
			Long codeNum = Long.valueOf("1" + orgCode);
			codeNext = String.valueOf(++codeNum);
		}
		return codeNext;
	}
	
	@SuppressWarnings("unchecked")
	private static Long executeCountQuery(Query query) {
		List<BigDecimal> totals = query.getResultList();
		return totals.get(0).longValue();
	}
	
	@SuppressWarnings("unchecked")
	public Integer getOrgSumByParentId(Long parentId) {
		
		String sqlString= " select count(*) from sys_org c where c.parent_id = :parentId and (c.del_flag is null or c.del_flag = 0)";
		Query query = em.createNativeQuery(sqlString);
		query.setParameter("parentId", parentId);
		List<BigDecimal> totals = query.getResultList();
		Integer count = totals.get(0).intValue();
		return count;
	}

}
