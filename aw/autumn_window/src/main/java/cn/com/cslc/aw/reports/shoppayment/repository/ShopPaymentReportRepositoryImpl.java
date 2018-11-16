package cn.com.cslc.aw.reports.shoppayment.repository;

import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentCondition;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentResult;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ShopPaymentReportRepositoryImpl implements cn.com.cslc.aw.reports.shoppayment.repository.ShopPaymentReportRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryShopPaymentResult> queryByCondition(QueryShopPaymentCondition queryCondition,
														 Pageable pageable) {

		String dealTypeCode = queryCondition.getDealTypeCode();
		String bankNo = queryCondition.getBankNo();
		String bDate = queryCondition.getbDate() + " 00:00:00";
		String eDate = queryCondition.geteDate() + " 23:59:59";
		String shopNo = queryCondition.getShopNo();

		String sqlString = " select sysorg.name orgName, ";
		sqlString +="        t.account_no accountNo, ";
		sqlString +="        t.bank_name bankName, ";
		sqlString +="        agent_no agentNo, ";
		sqlString +="        nvl (shop_no, '--') shopNo, ";
		sqlString +="        record_no, ";
		sqlString +="        to_char(log_time,'yyyy-MM-dd HH24:mi:ss') logTime, ";
		sqlString +="        to_char(deal_time,'yyyy-MM-dd HH24:mi:ss') dealTime, ";
		sqlString +="       case ";
		sqlString +="         when agent_shop_status = 2 and bank_name = '兑奖额度归集' and deal_type_code = 20 then -1*deal_amount " ;
		sqlString +="         when agent_shop_status = 2 and bank_name = '代理账户转账' and deal_type_code = 10 then -1*deal_amount " ;
		sqlString +="       else deal_amount end dealAmount, ";
		sqlString +="        old_record_no serviceCharge, ";
		sqlString +="       case " ;
		sqlString +="         when agent_shop_status = 2 and bank_name = '兑奖额度归集' and deal_type_code = 20 then '缴款' " ;
		sqlString +="         when agent_shop_status = 2 and bank_name = '代理账户转账' and deal_type_code = 10 then '额度划拨' " ;
		sqlString +="       else deal_type_name end dealTypeName ";
		sqlString +="   from his_agent_shop_payment_detail t, ";
		sqlString +="    (select t1.code ageng_no, t3.code , t3.name from sys_agent t1, sys_org_agent t2, sys_org t3 where t1.id = t2.agent_id and t2.org_id = t3.id) sysorg ";
		sqlString +="  where t.agent_no = sysorg.ageng_no ";

		//交易类型
		if(StringUtils.isNotEmpty(queryCondition.getDealTypeCode())){
			sqlString +="    and t.deal_type_code in (:dealTypeCode) ";
		}

		//银行名称
		if(StringUtils.isNotEmpty(queryCondition.getBankNo())){
			sqlString +="    and t.bank_no in (:bankNo) ";
		}

		//门店编号
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			sqlString +="    and t.agent_no in (:agentCodeSet)";
		}else{
			sqlString +="    and t.agent_no is null";
		}
		sqlString +="    and ( (t.agent_shop_status = 1 and t.bank_name not in ('兑奖额度归集', '代理账户转账')) ";
		sqlString +="         or (t.agent_shop_status = 2 and t.bank_name not in ('佣金转额度')) ";
		sqlString +="       ) ";
		sqlString +="    and nvl(t.shop_no, '--') like :shopNo ";

		//日期范围
		sqlString +="    and t.deal_time between ";
		sqlString +="        to_date(:bDate, 'yyyy/MM/dd HH24:mi:ss') and ";
		sqlString +="        to_date(:eDate, 'yyyy/MM/dd HH24:mi:ss') ";
		sqlString +="  order by agent_no, shop_no, deal_time desc ";

		Query query = em.createNativeQuery(sqlString);
		query.unwrap(SQLQuery.class)
		.addScalar("accountNo", StringType.INSTANCE)
		.addScalar("bankName", StringType.INSTANCE)
		 .addScalar("agentNo", StringType.INSTANCE)
		.addScalar("shopNo", StringType.INSTANCE)
		.addScalar("dealTypeName", StringType.INSTANCE)
		.addScalar("dealAmount", BigDecimalType.INSTANCE)
		.addScalar("serviceCharge", BigDecimalType.INSTANCE)
		.addScalar("dealTime", StringType.INSTANCE)
		.addScalar("logTime", StringType.INSTANCE)
		.addScalar("orgName", StringType.INSTANCE)
		.setResultTransformer(new FluentHibernateResultTransformer(QueryShopPaymentResult.class));

        //交易类型
		if(StringUtils.isNotEmpty(queryCondition.getDealTypeCode())){
			query.setParameter("dealTypeCode", dealTypeCode);
		}

		//银行名称
		if(StringUtils.isNotEmpty(queryCondition.getBankNo())){
			query.setParameter("bankNo", bankNo);
		}

		//门店编号
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			query.setParameter("agentCodeSet", queryCondition.getAgentCodeSet());
		}
		query.setParameter("shopNo", "%" + shopNo + "%");

		//日期范围
		query.setParameter("bDate", bDate);
		query.setParameter("eDate", eDate);

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		String countSqlString = "select count(*) from (";
		countSqlString += sqlString;
		countSqlString += ")";
		
		Query countQuery = em.createNativeQuery(countSqlString);

		//交易类型
		if(StringUtils.isNotEmpty(queryCondition.getDealTypeCode())){
			countQuery.setParameter("dealTypeCode", dealTypeCode);
		}

		//银行名称
		if(StringUtils.isNotEmpty(queryCondition.getBankNo())){
			countQuery.setParameter("bankNo", bankNo);
		}

		//门店编号
		if(!queryCondition.getAgentCodeSet().isEmpty()){
			countQuery.setParameter("agentCodeSet", queryCondition.getAgentCodeSet());
		}
		countQuery.setParameter("shopNo", "%" + shopNo + "%");

		//日期范围
		countQuery.setParameter("bDate", bDate);
		countQuery.setParameter("eDate", eDate);

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
