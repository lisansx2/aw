package cn.com.cslc.aw.game.repository;

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
import cn.com.cslc.aw.game.dto.Instant_GmGameAward;
import cn.com.cslc.aw.settings.gamesetting.dto.QueryGameSettingResult;



public class GameRepositoryImpl implements GameRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Page<QueryGameSettingResult> queryGameListByProvinceCodes(Set<String> provinceCodesSet, Pageable pageable) {
		// TODO Auto-generated method stub
		String sqlString = "SELECT t1.province_name provinceName,";
		sqlString += " t1.province_no provinceNo, ";
		sqlString += " NVL(t2.game_list,' ') gameSettingList ";
		sqlString += " FROM sys_province t1 ";
		sqlString += " LEFT OUTER JOIN ";
		sqlString += "  ( select sg.province_id,";
		sqlString += "  listagg(sg.game_name,',') within GROUP(";
		sqlString += "  ORDER BY sg.game_name ASC) AS game_list";
		sqlString += "  FROM sys_game sg";
		sqlString += "  GROUP BY sg.province_id";
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
		.addScalar("gameSettingList", StringType.INSTANCE)

		.setResultTransformer(new FluentHibernateResultTransformer(QueryGameSettingResult.class));
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
	public List<C_GameDefDto> getGameDtoByProvinceNoAndGameNo(String provinceNo,String gameNo) {
		String sql = " select cgd.province_center_id as  \"provinceCenterId\", cgd.game_no as \"gameNo\", cgd.game_name as \"gameName\",  cgd.game_short_name as \"gameShortName\",cgd.game_status_code as \"gameStatusCode\"";
		sql += " from c_game_def cgd ";
		sql += " where cgd.game_status_code = 40";
		sql += " and cgd.province_center_id=:provinceNo";
		
		if(gameNo!=null &&gameNo.length()>0){
			sql += " and cgd.game_no=:gameNo";
		}
		//List<C_GameDefDto> gameDtos = em.createNativeQuery(sql).unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(C_GameDefDto.class )).list();
		
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(C_GameDefDto.class ));
		query.setParameter("provinceNo", provinceNo);
		if(gameNo!=null &&gameNo.length()>0){
			query.setParameter("gameNo", gameNo);
		}
		return  query.getResultList();
		

	}

	@Override
	public List<Instant_GmGameAward> getInstantGameDtoByProvinceNoAndGameNo(String provinceNo, String gameNo) {
		String sql = " select iggap.province_org_id as \"provinceCenterId\", iggap.game_code as \"gameNo\", iggap.game_name as \"gameName\",iggap.province_org_code as \"provinceOrgCode\"";
		sql += " from instant_gm_game_award_province iggap ";
		sql += " where iggap.active_flag='Y' ";
		sql += " and iggap.deleted_flag='0'";
		sql += " and iggap.province_org_code=:provinceNo";
		
		if(gameNo!=null &&gameNo.length()>0){
			sql += " and iggap.game_code=:gameNo";
		}
		
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(Instant_GmGameAward.class ));
		query.setParameter("provinceNo", provinceNo);
		if(gameNo!=null &&gameNo.length()>0){
			query.setParameter("gameNo", gameNo);
		}
		return  query.getResultList();
	}



}
