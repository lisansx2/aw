package cn.com.cslc.aw.bank.repository;

import cn.com.cslc.aw.bank.dto.B_BankDto;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class BBankRepositoryImpl implements BBankRepositoryCustom {

	@PersistenceContext
	private EntityManager em;



	@SuppressWarnings("unchecked")
	@Override
	public List<B_BankDto> queryBBank(Set<String> provinceIdSet){

		String sqlString = " select t.bank_no bankNo, t.bank_name bankName from b_bank t, b_province_center p where t.province_center_id = p.province_center_id and p.province_center_no in (:provinceIdSet) order by t.bank_no ";


		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("bankNo", StringType.INSTANCE)
				.addScalar("bankName", StringType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(B_BankDto.class));

		query.setParameter("provinceIdSet", provinceIdSet);

		return  query.getResultList();

	}

	@Override
	public List<B_BankDto> queryBBank(Set<String> provinceIdSet, String bankNo) {

		String sqlString = " select t.bank_no bankNo, t.bank_name bankName from b_bank t, b_province_center p where t.province_center_id = p.province_center_id and p.province_center_no in (:provinceIdSet) and t.bank_no = :bankNo ";


		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("bankNo", StringType.INSTANCE)
				.addScalar("bankName", StringType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(B_BankDto.class));

		query.setParameter("provinceIdSet", provinceIdSet);
		query.setParameter("bankNo", bankNo);

		return  query.getResultList();
	}
}
