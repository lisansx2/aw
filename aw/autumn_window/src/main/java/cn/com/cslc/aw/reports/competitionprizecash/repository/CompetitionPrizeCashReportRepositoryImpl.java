package cn.com.cslc.aw.reports.competitionprizecash.repository;

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

import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashCondition;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashResult;

public class CompetitionPrizeCashReportRepositoryImpl implements CompetitionPrizeCashReportRepositoryCustom{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<QueryCompetitionPrizeCashResult> queryByCondition(QueryCompetitionPrizeCashCondition queryCondition,
			Pageable pageable) {
		String sqlString =" select s2.*, s1.orgName ";
		sqlString += "   from (select R1.Shop_No as shopNo, ";
		sqlString += "                r1.agent_no as agentCode, ";
		sqlString += "                nvl(R2.paidTicketNumSum, 0) as paidTicketNumSum, ";
		sqlString += "                nvl(R2.paidTicketAmountSum, 0) as paidTicketAmountSum, ";
		sqlString += "                nvl(R2.paidAmountRoundSum, 0) as paidAmountRoundSum, ";
		sqlString += "                nvl(R2.paidAmountAbandonSum, 0) as paidAmountAbandonSum, ";
		sqlString += "                nvl(R2.paidAmountCarrySum, 0) as paidAmountCarrySum ";
		sqlString += "           from (select distinct t.shop_no, t.agent_no ";
		sqlString += "                   from Sys_Period_Sales_Report_Temp t ";
		sqlString += "                  where t.Report_Date BETWEEN to_date(:startDate, 'yyyy/MM/dd') AND to_date(:endDate, 'yyyy/MM/dd') ";
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			sqlString += " and t.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += " and t.Agent_No is null ";
		}
		sqlString += "                    and t.shop_no like :shopNo) r1 ";
		sqlString += "           left join (SELECT bs.shop_no shopNo, ";
		sqlString += "                            sum(tr.paid_lottery_cnt) paidTicketNumSum, ";
		sqlString += "                            sum(tr.paid_amount) paidTicketAmountSum, ";
		sqlString += "                            sum(tr.PAID_AMOUNT_ROUND) paidAmountRoundSum, ";
		sqlString += "                            sum(tr.PAID_AMOUNT_ABANDON) paidAmountAbandonSum, ";
		sqlString += "                            sum(tr.PAID_AMOUNT_CARRY) paidAmountCarrySum ";
		sqlString += "                       FROM RC_SHOP_JC_DAILY tr, b_shop bs, b_agent ba ";
		sqlString += "                      where bs.shop_id = tr.shop_id ";
		sqlString += "                        and ba.agent_id = bs.agent_id ";
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			sqlString += " and ba.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += " and ba.Agent_No is null ";
		}
		sqlString += "                        and tr.Report_Date BETWEEN to_date(:startDate, 'yyyy/MM/dd') AND to_date(:endDate, 'yyyy/MM/dd') ";
		sqlString += "                        and bs.shop_no like :shopNo ";
		sqlString += "                      group by bs.Shop_No) r2 ";
		sqlString += "             on R1.Shop_No = R2.shopNo) s2 ";
		sqlString += "   left join (SELECT t1.CODE agentCode, t3.CODE orgCode, t3.NAME orgName ";
		sqlString += "                FROM SYS_AGENT t1, sys_org_agent t2, sys_org t3 ";
		sqlString += "               WHERE t1.ID = t2.AGENT_ID ";
		sqlString += "                 AND t2.ORG_ID = t3.ID) s1 ";
		sqlString += "     on s1.agentCode = s2.agentCode ";
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
		.addScalar("paidAmountRoundSum", BigDecimalType.INSTANCE)
		.addScalar("paidAmountAbandonSum", BigDecimalType.INSTANCE)
		.addScalar("paidAmountCarrySum", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryCompetitionPrizeCashResult.class));
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			query.setParameter("agentCodeSet", queryCondition.getAgentCodeSet());
			countQuery.setParameter("agentCodeSet", queryCondition.getAgentCodeSet());
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
