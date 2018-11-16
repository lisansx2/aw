package cn.com.cslc.aw.saleperiod.repository;

import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.saleperiod.dto.C_SalePeriodDto;
import cn.com.cslc.aw.settings.gamesetting.dto.QueryGameSettingResult;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public class CSalePeriodRepositoryImpl implements CSalePeriodRepositoryCustom {

	@PersistenceContext
	private EntityManager em;



	@SuppressWarnings("unchecked")
	@Override
	public List<C_SalePeriodDto> querySalePeriodFromC_Sale_Period(Set<String> userProvinceCodeSet) {

		String sqlString = "select t.sale_period_id            salePeriodId, ";
		sqlString += "       t.province_center_id        provinceCenterId, ";
		sqlString += "       to_char(t.begin_time,'yyyy-MM-dd')                beginTime, ";
		sqlString += "       to_char(t.end_time,'yyyy-MM-dd')                  endTime, ";
		sqlString += "       t.sale_period_no            salePeriodNo, ";
		sqlString += "       t.create_date               createDate, ";
		sqlString += "       t.create_user_id            createUserId, ";
		sqlString += "       t.settled_flag              settledFlag, ";
		sqlString += "       t.settled_time              settledTime, ";
		sqlString += "       t.commission_mode_type_code commissionModeTypeCode, ";
		sqlString += "       t.tiger_flag                tigerFlag, ";
		sqlString += "       t.del_flag                  delFlag, ";
		sqlString += "       t.del_time                  delTime ";
		sqlString += "  from c_sale_period t, b_province_center bp ";
		sqlString += " where bp.province_center_no in (:userProvinceCodeSet) ";
		sqlString += "   and t.province_center_id = bp.province_center_id ";
		sqlString += "   and t.settled_flag = 1 ";
		sqlString += " order by t.sale_period_no desc ";

		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("salePeriodNo", StringType.INSTANCE)
				.addScalar("beginTime", StringType.INSTANCE)
				.addScalar("endTime", StringType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(C_SalePeriodDto.class));

		query.setParameter("userProvinceCodeSet", userProvinceCodeSet);

		return  query.getResultList();

	}

}
