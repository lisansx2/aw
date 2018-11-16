package cn.com.cslc.aw.customer.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;

import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.dto.B_AgentDto;
import cn.com.cslc.aw.customer.domain.SysCustomer;
import cn.com.cslc.aw.customer.dto.MD_ChannelCustomerDto;
import cn.com.cslc.aw.game.dto.C_GameDefDto;
import cn.com.cslc.aw.settings.customersetting.dto.QueryCustomerSettingResult;


public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MD_ChannelCustomerDto> getCustomerDtoByProvinceNo(String provinceNo) {
		// TODO Auto-generated method stub
		String sql = " select mcc.customer_code as  \"customerCode\",mcc.customer_name as \"customerName\",mcc.org_type_code as \"orgTypeCode\" ,mcc.org_code as \"orgCode\",mcc.org_name as \"orgName\"";
		sql += " from INSTANT_MD_CHANNEL_CUSTOMER mcc ";
		sql += " where mcc.ORG_TYPE_CODE=2";
		sql += " and mcc.org_code = :provinceNo";
		sql += " and mcc.DELETED_FLAG='0'";
		sql += " and mcc.ACTIVE_FLAG='Y'";
		sql += " union all";
		sql += " select mcc.customer_code as  \"customerCode\",mcc.customer_name as \"customerName\",mcc.org_type_code as \"orgTypeCode\" ,mcc.org_code as \"orgCode\",mcc.org_name as \"orgName\"";
		sql += " from";
		sql += " INSTANT_MD_CHANNEL_CUSTOMER mcc";
		sql += " where  mcc.ORG_TYPE_CODE=3";
		sql += " and mcc.org_code in (";
		sql += " select sc.city_no";
		sql += " from sys_province sp,sys_city sc";
		sql += " where sp.id=sc.province_id";
		sql+=" and sp.province_no=:provinceNo";
		sql+=" )";
		sql+=" and mcc.DELETED_FLAG='0'";
		sql+=" and mcc.ACTIVE_FLAG='Y'";
		
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(MD_ChannelCustomerDto.class ));
		query.setParameter("provinceNo", provinceNo);
		
		return  (List<MD_ChannelCustomerDto>)query.getResultList();
		
	}

	
	@Override
	public Page<QueryCustomerSettingResult> queryCustomerListByProvinceCodes(Set<String> provinceCodesSet,
			Pageable pageable) {
	
		// TODO Auto-generated method stub
		String sqlString = "SELECT t1.province_name provinceName,";
		sqlString += " t1.province_no provinceNo, ";
		sqlString += " NVL(t2.customer_list,' ') customerSettingList ";
		sqlString += " FROM sys_province t1 ";
		sqlString += " LEFT OUTER JOIN ";
		sqlString += "  ( select sc.province_id,";
		sqlString += "   listagg(sc.name,',') within GROUP(";
		sqlString += "  ORDER BY sc.name ASC) AS customer_list";
		sqlString += "  FROM sys_customer sc";
		sqlString += "  GROUP BY sc.province_id";
		sqlString += "  ) t2 ";
		sqlString += " ON t1.id = t2.province_id";
		
		if(!provinceCodesSet.isEmpty()){
			sqlString += " WHERE t1.province_no in  (:provinceCodesSet) ";
		}else{
			sqlString += " WHERE t1.province_no  is null ";
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
		.addScalar("provinceName", StringType.INSTANCE)
		.addScalar("provinceNo", StringType.INSTANCE)
		.addScalar("customerSettingList", StringType.INSTANCE)

		.setResultTransformer(new FluentHibernateResultTransformer(QueryCustomerSettingResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from ( ";
		countSqlString += sqlString;
		countSqlString += " )";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if(!provinceCodesSet.isEmpty()){
			query.setParameter("provinceCodesSet", provinceCodesSet);
			countQuery.setParameter("provinceCodesSet", provinceCodesSet);
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
	public List<MD_ChannelCustomerDto> getAllCustomerFromMD_ChannelCustomerByNo(String customerNo) {
		// TODO Auto-generated method stub

		String sql = "select mcc.customer_code as \"customerCode\",mcc.customer_name as \"customerName\", mcc.org_type_code as \"orgTypeCode\",mcc.org_code as \"orgCode\", mcc.org_name as \"orgName\"";
		sql += " from instant_md_channel_customer mcc";
		sql += " where mcc.customer_code=:customerCode ";
		sql+=" and mcc.deleted_flag='0'";
		sql+=" and mcc.active_flag='Y'";
		
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(MD_ChannelCustomerDto.class ));
		query.setParameter("customerCode", customerNo);
		
		return  query.getResultList();
		
	}


	@Override
	public List<SysCustomer> findUnusedCustomByProvinceNo(String provinceNo,Long orgId) {
		String sql = "select c.id,c.name,c.province_no from sys_customer c where c.id not in (select b.customer_id from sys_org_customer b  where b.org_id <> :orgId) and c.province_no = :provinceNo";
		Query query = em.createNativeQuery(sql);

		query.setParameter("orgId", orgId);
		query.setParameter("provinceNo", provinceNo);
		List list = query.getResultList();
		List<SysCustomer> sysCustomerList =  new ArrayList<SysCustomer>();
		if(list != null){
			for(Object o : list){
				Object[] obj = (Object[]) o;
				SysCustomer sysCustomer = new SysCustomer();
				sysCustomer.setId(Long.valueOf(obj[0].toString()));
				sysCustomer.setName(obj[1].toString());
				sysCustomerList.add(sysCustomer);
			}
			
		}
		return sysCustomerList;
	}

}
