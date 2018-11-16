package cn.com.cslc.aw.domain.repository;

import cn.com.cslc.aw.domain.dto.B_DomainDto;
import com.github.fluent.hibernate.transformer.FluentHibernateResultTransformer;
import org.hibernate.SQLQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class BDomainRepositoryImpl implements BDomainRepositoryCustom {

	@PersistenceContext
	private EntityManager em;



	@SuppressWarnings("unchecked")
	@Override
	public List<B_DomainDto> queryBDomain(String domTypeCode){

		String sqlString = " select t.dom_key domKey, t.dom_value domValue from  b_domain t where t.dom_type_code = :domTypeCode order by t.dom_key ";


		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("domKey", BigDecimalType.INSTANCE)
				.addScalar("domValue", StringType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(B_DomainDto.class));

		query.setParameter("domTypeCode", domTypeCode);

		return  query.getResultList();

	}

	@Override
	public B_DomainDto queryBDomain(String domTypeCode, BigDecimal domKey) {
		String sqlString = " select t.dom_key domKey, t.dom_value domValue from  b_domain t where t.dom_type_code = :domTypeCode and t.dom_key = :domKey ";


		Query query = em.createNativeQuery(sqlString);

		query.unwrap(SQLQuery.class)
				.addScalar("domKey", BigDecimalType.INSTANCE)
				.addScalar("domValue", StringType.INSTANCE)
				.setResultTransformer(new FluentHibernateResultTransformer(B_DomainDto.class));

		query.setParameter("domTypeCode", domTypeCode);
		query.setParameter("domKey", domKey);

		return (B_DomainDto) query.getSingleResult();
	}
}
