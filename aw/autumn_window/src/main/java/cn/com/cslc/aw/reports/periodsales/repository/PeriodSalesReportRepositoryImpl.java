package cn.com.cslc.aw.reports.periodsales.repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;

import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.exception.BaseSystemException;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesCondition;
import cn.com.cslc.aw.reports.periodsales.dto.QueryPeriodSalesResult;

public class PeriodSalesReportRepositoryImpl implements PeriodSalesReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AgentService agentService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryPeriodSalesResult> queryPeriodSalesByCondition(QueryPeriodSalesCondition queryCondition,
			Pageable pageable) {
		
		Set<String> agentCodeSet = queryCondition.getAgentCodeSet();
		String sqlString ="select S2.*, s1.orgName from ";
//		sqlString += " from (SELECT t1.CODE agentCode, t3.CODE orgCode, t3.NAME orgName";
//		sqlString += " FROM SYS_AGENT t1, sys_org_agent t2, sys_org t3";
//		sqlString += " WHERE t1.ID = t2.AGENT_ID";
//		sqlString += " AND t2.ORG_ID = t3.ID) s1,";
		sqlString += " (select R1.Shop_No as shopNo, ";
		sqlString += " r1.agent_no as agent_no,";
		sqlString += " sum(nvl(R2.Saled_Ticket_Num_Sum,0)) as saledTicketNumSum, ";
		sqlString += " sum(nvl(R2.Saled_Ticket_Amount_Sum,0)) as saledTicketAmountSum, ";
		sqlString += " sum(nvl(R2.cancel_ticket_num_sum, 0)) as cancelTicketNumSum, ";
		sqlString += " sum(nvl(R2.cancel_ticket_amount_sum, 0)) as cancelTicketAmountSum, ";
		sqlString += " sum(nvl(R2.refund_ticket_num_sum, 0)) as refundTicketNumSum,";
		sqlString += " sum(nvl(R2.refund_ticket_amount_sum, 0)) as refundTicketAmountSum,";
		sqlString += " sum(nvl(R2.Paid_Ticket_Num_Sum,0)) as paidTicketNumSum, ";
		sqlString += " sum(nvl(R2.Paid_Ticket_Amount_Sum,0))  as paidTicketAmountSum ";
		sqlString += " from ";
		sqlString = sqlString + " (select distinct t.shop_no,t.agent_no from Sys_Period_Sales_Report_Temp t where t.Report_Date between to_date(:startDateStr,'yyyy/MM/dd HH24:mi:ss')  and to_date(:endDateStr,'yyyy/MM/dd HH24:mi:ss')";
		sqlString +=  " and t.Shop_No like :shopNo";
		
		if(!agentCodeSet.isEmpty()){
			sqlString += "   and t.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += "   and t.Agent_No is null";
		}
		
		sqlString += ") r1 ";
		sqlString +=" left join (";
		sqlString += " select T6.Shop_No as shop_no,t7.agent_no as agent_no,sum(nvl(T1.Sale_Lottery_Cnt,0)) as saled_ticket_num_sum, sum(nvl(T1.Sale_Amount,0)) as saled_ticket_amount_sum , sum(nvl(T1.Cancel_Lottery_Cnt, 0))+sum(nvl(T1.Prov_Cancel_Lottery_Cnt, 0))+ sum(nvl(T1.Branch_Cancel_Lottery_Cnt, 0)) as cancel_ticket_num_sum,  sum(nvl(T1.Cancel_Amount, 0))+sum(nvl(T1.Prov_Cancel_Amount, 0))+ sum(nvl(T1.Branch_Cancel_Amount, 0)) as cancel_ticket_amount_sum,   CASE t2.tech_system_id WHEN 40 THEN sum(nvl(t1.STS_REFUND_LOTTERY_CNT, 0) + nvl(t1.STO_REFUND_LOTTERY_CNT, 0))  ELSE 0 END as refund_ticket_num_sum, CASE t2.tech_system_id WHEN 40 THEN sum(nvl(t1.STS_REFUND_AMOUNT, 0) + nvl(t1.STO_REFUND_AMOUNT, 0)) ELSE 0 END as refund_ticket_amount_sum, sum(nvl(t1.STS_PAID_LOTTERY_CNT,0)+nvl(t1.STO_PAID_LOTTERY_CNT,0)) as paid_ticket_num_sum , sum(nvl(t1.STS_PAID_AMOUNT,0)+nvl(t1.STO_PAID_AMOUNT,0)) as paid_ticket_amount_sum ";
		sqlString += " from R_Base t1, C_Game_Def t2,B_Terminal t3 ,B_Shop t6 ,B_Agent t7 ";
		
		sqlString += " where T1.Province_Center_Id = T2.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T3.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T6.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T7.Province_Center_Id ";

		sqlString  += " and T1.Game_Id = T2.Game_Id ";
		sqlString  += " and T1.Terminal_Id = T3.Terminal_Id ";
		sqlString  += " and T3.Shop_Id = t6.shop_id ";
		sqlString  += " and T6.Agent_Id = T7.Agent_Id ";
		//sqlString  += " and T3.Channel_Type_Code = 9 ";
		if(!StringUtils.isEmpty(queryCondition.getGameNo())){
			sqlString += " and T2.Game_No = :gameNo";
		}
		
		String startDateStr = queryCondition.getStartDate() + " 00:00:00";
		String endDateStr = queryCondition.getEndDate() + " 23:59:59";
		sqlString += " and (T1.Report_Date between to_date(:startDateStr,'yyyy/MM/dd HH24:mi:ss')  and to_date(:endDateStr,'yyyy/MM/dd HH24:mi:ss') ) ";

		if(!agentCodeSet.isEmpty()){
			sqlString += "   and t7.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += "   and t7.Agent_No is null";
		}
		
		sqlString += " and t6.shop_no like :shopNo";

		sqlString += " group by t6.Shop_No,t2.tech_system_id,t7.agent_no ";
		
		sqlString += " 	) r2 on R1.Shop_No = R2.shop_no ";
		
		sqlString += " 	 group by r1.shop_no,r1.agent_no)s2 ";
		
		sqlString += " left join (SELECT t1.CODE agentCode, t3.CODE orgCode, t3.NAME orgName";
		sqlString += " FROM SYS_AGENT t1, sys_org_agent t2, sys_org t3";
		sqlString += " WHERE t1.ID = t2.AGENT_ID";
		sqlString += " AND t2.ORG_ID = t3.ID) s1";
		sqlString += "   on s1.agentCode = s2.agent_no";
		
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
		.addScalar("saledTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("saledTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("cancelTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("cancelTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("refundTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("refundTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("paidTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("paidTicketAmountSum", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryPeriodSalesResult.class));

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		if(!StringUtils.isEmpty(queryCondition.getGameNo())){
			query.setParameter("gameNo", queryCondition.getGameNo());
			countQuery.setParameter("gameNo", queryCondition.getGameNo());
		}
		
		query.setParameter("startDateStr", startDateStr);
		countQuery.setParameter("startDateStr", startDateStr);
		query.setParameter("endDateStr", endDateStr);
		countQuery.setParameter("endDateStr", endDateStr);
		if(StringUtils.isNotEmpty(queryCondition.getShopNo())){
			query.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
			countQuery.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
		}else{
			query.setParameter("shopNo", "%%");
			countQuery.setParameter("shopNo", "%%");
		}
		if(!agentCodeSet.isEmpty()){
			query.setParameter("agentCodeSet", agentCodeSet);
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
	@Override
	public Page<QueryPeriodSalesResult> queryPeriodSalesByConditionNatural(QueryPeriodSalesCondition queryCondition,
			Pageable pageable) {
		
		Set<String> agentCodeSet = queryCondition.getAgentCodeSet();
		String sqlString ="select S2.*, s1.orgName from ";
//		sqlString += " from (SELECT t1.CODE agentCode, t3.CODE orgCode, t3.NAME orgName";
//		sqlString += " FROM SYS_AGENT t1, sys_org_agent t2, sys_org t3";
//		sqlString += " WHERE t1.ID = t2.AGENT_ID";
//		sqlString += " AND t2.ORG_ID = t3.ID) s1,";
		sqlString += " (select R1.Shop_No as shopNo, ";
		sqlString += " r1.agent_no as agent_no,";
		sqlString += " sum(nvl(R2.Saled_Ticket_Num_Sum,0)) as saledTicketNumSum, ";
		sqlString += " sum(nvl(R2.Saled_Ticket_Amount_Sum,0)) as saledTicketAmountSum, ";
		sqlString += " sum(nvl(R2.cancel_ticket_num_sum, 0)) as cancelTicketNumSum, ";
		sqlString += " sum(nvl(R2.cancel_ticket_amount_sum, 0)) as cancelTicketAmountSum, ";
		sqlString += " sum(nvl(R2.refund_ticket_num_sum, 0)) as refundTicketNumSum,";
		sqlString += " sum(nvl(R2.refund_ticket_amount_sum, 0)) as refundTicketAmountSum,";
		sqlString += " sum(nvl(R2.Paid_Ticket_Num_Sum,0)) as paidTicketNumSum, ";
		sqlString += " sum(nvl(R2.Paid_Ticket_Amount_Sum,0))  as paidTicketAmountSum ";
		sqlString += " from ";
		sqlString = sqlString + " (select distinct t.shop_no,t.agent_no from Sys_Period_Sales_Report_Temp t where t.Report_Date between to_date(:startDateStr,'yyyy/MM/dd HH24:mi:ss')  and to_date(:endDateStr,'yyyy/MM/dd HH24:mi:ss')";
		sqlString +=  " and t.Shop_No like :shopNo";
		
		if(!agentCodeSet.isEmpty()){
			sqlString += "   and t.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += "   and t.Agent_No is null";
		}
		
		sqlString += ") r1 ";
		sqlString +=" left join (";
		sqlString += " select /*+ full(T1) */  T6.Shop_No as shop_no,t7.agent_no as agent_no,sum(nvl(T1.Sale_Lottery_Cnt,0)) as saled_ticket_num_sum, sum(nvl(T1.Sale_Amount,0)) as saled_ticket_amount_sum , sum(nvl(T1.Cancel_Lottery_Cnt, 0))+sum(nvl(T1.Prov_Cancel_Lottery_Cnt, 0))+ sum(nvl(T1.Branch_Cancel_Lottery_Cnt, 0)) as cancel_ticket_num_sum,  sum(nvl(T1.Cancel_Amount, 0))+sum(nvl(T1.Prov_Cancel_Amount, 0))+ sum(nvl(T1.Branch_Cancel_Amount, 0)) as cancel_ticket_amount_sum,   CASE t2.tech_system_id WHEN 40 THEN sum(nvl(t1.REFUND_LOTTERY_CNT, 0))  ELSE 0 END as refund_ticket_num_sum, CASE t2.tech_system_id WHEN 40 THEN sum(nvl(t1.REFUND_AMOUNT, 0)) ELSE 0 END as refund_ticket_amount_sum, sum(nvl(t1.PAID_LOTTERY_CNT,0)) as paid_ticket_num_sum , sum(nvl(t1.PAID_AMOUNT,0)) as paid_ticket_amount_sum ";
		sqlString += " from rc_account_base t1, C_Game_Def t2,B_Terminal t3 ,B_Shop t6 ,B_Agent t7 ";
		
		sqlString += " where T1.Province_Center_Id = T2.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T3.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T6.Province_Center_Id ";
		sqlString +=" and T1.Province_Center_Id = T7.Province_Center_Id ";

		sqlString  += " and T1.Game_Id = T2.Game_Id ";
		sqlString  += " and T1.Terminal_Id = T3.Terminal_Id ";
		sqlString  += " and T3.Shop_Id = t6.shop_id ";
		sqlString  += " and T6.Agent_Id = T7.Agent_Id ";
		//sqlString  += " and T3.Channel_Type_Code = 9 ";
		if(!StringUtils.isEmpty(queryCondition.getGameNo())){
			sqlString += " and T2.Game_No = :gameNo";
		}
		
		String startDateStr = queryCondition.getStartDate() + " 00:00:00";
		String endDateStr = queryCondition.getEndDate() + " 23:59:59";
		sqlString += " and (T1.Report_Date between to_date(:startDateStr,'yyyy/MM/dd HH24:mi:ss')  and to_date(:endDateStr,'yyyy/MM/dd HH24:mi:ss') ) ";

		if(!agentCodeSet.isEmpty()){
			sqlString += "   and t7.Agent_No in (:agentCodeSet)";
		}else{
			sqlString += "   and t7.Agent_No is null";
		}
		
		sqlString += " and t6.shop_no like :shopNo";

		sqlString += " group by t6.Shop_No,t2.tech_system_id,t7.agent_no ";
		
		sqlString += " 	) r2 on R1.Shop_No = R2.shop_no ";
		
		sqlString += " 	 group by r1.shop_no,r1.agent_no)s2 ";
		
		sqlString += " left join (SELECT t1.CODE agentCode, t3.CODE orgCode, t3.NAME orgName";
		sqlString += " FROM SYS_AGENT t1, sys_org_agent t2, sys_org t3";
		sqlString += " WHERE t1.ID = t2.AGENT_ID";
		sqlString += " AND t2.ORG_ID = t3.ID) s1";
		sqlString += "   on s1.agentCode = s2.agent_no";
		
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
		.addScalar("saledTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("saledTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("cancelTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("cancelTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("refundTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("refundTicketAmountSum", BigDecimalType.INSTANCE)
		.addScalar("paidTicketNumSum", BigDecimalType.INSTANCE)
		.addScalar("paidTicketAmountSum", BigDecimalType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryPeriodSalesResult.class));

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);
		if(!StringUtils.isEmpty(queryCondition.getGameNo())){
			query.setParameter("gameNo", queryCondition.getGameNo());
			countQuery.setParameter("gameNo", queryCondition.getGameNo());
		}
		
		query.setParameter("startDateStr", startDateStr);
		countQuery.setParameter("startDateStr", startDateStr);
		query.setParameter("endDateStr", endDateStr);
		countQuery.setParameter("endDateStr", endDateStr);
		if(StringUtils.isNotEmpty(queryCondition.getShopNo())){
			query.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
			countQuery.setParameter("shopNo", "%" + queryCondition.getShopNo() + "%");
		}else{
			query.setParameter("shopNo", "%%");
			countQuery.setParameter("shopNo", "%%");
		}
		if(!agentCodeSet.isEmpty()){
			query.setParameter("agentCodeSet", agentCodeSet);
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
	
	@Override
	public Date findMaxReportDate() {
		String sql = "select max(t.report_date) from Sys_Period_Sales_Report_Temp t ";
		List maxReportDateList =  em.createNativeQuery(sql).getResultList();
		if(maxReportDateList == null || maxReportDateList.isEmpty()){
			return null;
		}else{
			return (Date) maxReportDateList.get(0);
		}
	}

	@Override
	public void batchInsertReportBaseData(String startDateStr, String endDateStr) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = DateUtils.parseDate(startDateStr, new String[]{"yyyy-MM-dd"});
			endDate = DateUtils.parseDate(endDateStr, new String[]{"yyyy-MM-dd"});
		} catch (ParseException e) {
			throw new BaseSystemException(e);
		}

		while (startDate.before(endDate) || DateUtils.isSameDay(startDate, endDate)){
			String sql = this.generateBatchInsertReportBaseDataSql(startDate);
			em.createNativeQuery(sql).executeUpdate();
			startDate = DateUtils.addDays(startDate, 1);
		}
	}
	
	private String generateBatchInsertReportBaseDataSql(Date reportDate){
		String sql = "Insert Into Sys_Period_Sales_Report_Temp(Id,Report_Date,Terminal_Id,Terminal_No,Shop_Id,Shop_No,Agent_Id,Agent_No,City_Id,City_No,Province_Id,Province_No) ";
		sql +=  "select aw_sequence.Nextval";
		String startDateStr = DateFormatUtils.format(reportDate, "yyyy-MM-dd");
		sql = sql + ",to_date('" + startDateStr + "','yyyy-MM-dd') ";
		sql += ", T1.Terminal_Id,T1.Terminal_No,T2.Shop_Id,T2.Shop_No,T3.Agent_Id,T3.Agent_No,T4.City_Center_Id,T4.City_Center_No,T5.Province_Center_Id,T5.Province_Center_No";
		sql += " from B_Terminal t1, B_Shop t2, B_Agent t3 ,B_City_Center t4,B_Province_Center t5  ";
		sql += " where T1.Province_Center_Id = T2.Province_Center_Id ";
		sql += " and T1.Province_Center_Id = T3.Province_Center_Id ";
		sql += " and T1.Province_Center_Id = T4.Province_Center_Id ";
		sql += " and T1.Province_Center_Id = T5.Province_Center_Id ";
		sql += " and T2.City_Center_Id = T4.City_Center_Id ";
		sql += " and T1.Shop_Id = T2.Shop_Id ";
		sql += " and T2.Agent_Id = T3.Agent_Id ";
		//sql += " and T1.Channel_Type_Code = 9 ";
		
		List<SysAgent> agentList = agentService.findAllAgent();
		if(!agentList.isEmpty()){
			sql += "   and t3.Agent_No in (";
			for(int i = 0 ; i < agentList.size(); i++){
				sql += agentList.get(i).getCode();
				if(i < agentList.size()-1){
					sql += ",";
				}
			}
			sql +=")";
		}

		return sql;
	}

	@Override
	public Date findInitReportDate() {
		String sql = "select min(t.report_date) from r_base t";
		List<Date> initReportDateList =  em.createNativeQuery(sql).getResultList();
		if(initReportDateList == null || initReportDateList.isEmpty()){
			return null;
		}else{
			return initReportDateList.get(0);
		}
	}
}
