package cn.com.cslc.aw.province.repository;

import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class BProvinceCenterRepositoryImpl implements BProvinceCenterRepositoryCustom {

	@PersistenceContext
	private EntityManager em;



	@SuppressWarnings("unchecked")
	@Override
	public List<B_ProvinceCenterDto> queryBProvinceCenter(Set<String> provinceIdSet){

		String sqlString = " select p2.province_center_id provinceCenterId from sys_province p1, b_province_center p2 where p1.province_no = p2.province_center_no and p1.province_no in (:provinceIdSet) order by p1.province_no ";


		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("provinceCenterId", BigDecimalType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(B_ProvinceCenterDto.class));

		query.setParameter("provinceIdSet", provinceIdSet);

		return  query.getResultList();

	}

}
