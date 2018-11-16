package cn.com.cslc.aw.agent.repository;

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
import cn.com.cslc.aw.game.dto.C_GameDefDto;
import cn.com.cslc.aw.settings.agentsetting.dto.QueryAgentSettingResult;

public class AgentRepositoryImpl implements AgentRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<B_AgentDto> getAllAgentInfoFromB_Agent() {
		String sql = "select t.AGENT_NO as \"agentNo\", t.AGENT_NAME as \"agentName\", t.STATUS_CODE as \"statusCode\", t.DEL_FLAG as \"delFlag\"";
		sql += " from B_AGENT t ";
		sql += " where t.AGENT_TYPE_CODE = 10 and t.DEL_FLAG = 0 ";
		List<B_AgentDto> agentDtos = em.createNativeQuery(sql).unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(B_AgentDto.class )).list();
		
		return agentDtos;
	}

	@Override
	public Page<QueryAgentSettingResult> queryAgentListByProvinceCodes(Set<String> provinceNoSet,
			Pageable pageable) {
		
		String sqlString = "SELECT t1.PROVINCE_NAME provinceName,";
		sqlString += "  t1.PROVINCE_NO provinceNo, ";
		sqlString += "  t2.agent_list agentSettingList ";
		sqlString += " FROM sys_province t1 ";
		sqlString += " LEFT OUTER JOIN ";
		sqlString += "  (SELECT t.province_id,";
		sqlString += "    listagg(t.NAME,',') within GROUP(";
		sqlString += "  ORDER BY t.NAME ASC) AS agent_list ";
		sqlString += "  FROM sys_agent t";
		sqlString += "  GROUP BY t.province_id";
		sqlString += "  ) t2 ";
		sqlString += " ON t1.ID             = t2.PROVINCE_ID";
				
		if(!provinceNoSet.isEmpty()){
			sqlString += " WHERE t1.PROVINCE_NO in  (:provinceNoSet) ";
		}else{
			sqlString += " WHERE t1.PROVINCE_NO  is null ";
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
		.addScalar("agentSettingList", StringType.INSTANCE)

		.setResultTransformer(new FluentHibernateResultTransformer(QueryAgentSettingResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if(!provinceNoSet.isEmpty()){
			query.setParameter("provinceNoSet", provinceNoSet);
			countQuery.setParameter("provinceNoSet", provinceNoSet);
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
	public B_AgentDto getAllAgentInfoFromB_AgentByNo(String agentNo) {
		String sql = "select t.AGENT_NO as \"agentNo\", t.AGENT_NAME as \"agentName\", t.STATUS_CODE as \"statusCode\", t.DEL_FLAG as \"delFlag\"";
		sql += " from B_AGENT t ";
		sql += " where t.AGENT_TYPE_CODE = 10 and t.DEL_FLAG = 0";
		sql += " and t.AGENT_NO = :agentNo ";
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(B_AgentDto.class ));
		query.setParameter("agentNo", agentNo);
		return (B_AgentDto) query.getSingleResult();
	}

	@Override
	public List<B_AgentDto> getAllAgentInfoByProvinceNo(String provinceNo) {
		String sql = "select t.AGENT_NO as \"agentNo\", t.AGENT_NAME as \"agentName\", t.STATUS_CODE as \"statusCode\", t.DEL_FLAG as \"delFlag\"";
		sql += " from B_AGENT t ";
		sql += " where t.AGENT_TYPE_CODE = 10 and t.DEL_FLAG = 0 ";
		sql += " and t.province_center_id=:provinceNo";
		
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(B_AgentDto.class ));
		query.setParameter("provinceNo", provinceNo);
		return  query.getResultList();
		
	}
	
	@Override
	public List<SysAgent> findUnusedAgentByProvinceNo(String provinceNo,Long orgId) {
		String sql = "select c.id,c.name from sys_agent c where c.id not in (select b.agent_id from sys_org_agent b where b.org_id <> :orgId) and c.province_no = :provinceNo";
		Query query = em.createNativeQuery(sql);

		query.setParameter("orgId", orgId);
		query.setParameter("provinceNo", provinceNo);
		List list = query.getResultList();
		List<SysAgent> sysAgentList =  new ArrayList<SysAgent>();
		if(list != null){
			for(Object o : list){
				Object[] obj = (Object[]) o;
				SysAgent sysAgent = new SysAgent();
				sysAgent.setId(Long.valueOf(obj[0].toString()));
				sysAgent.setName(obj[1].toString());
				sysAgentList.add(sysAgent);
			}
			
		}
		return sysAgentList;
		
	}
}
