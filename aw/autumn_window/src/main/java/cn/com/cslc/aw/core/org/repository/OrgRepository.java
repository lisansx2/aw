package cn.com.cslc.aw.core.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.domain.SysOrg;
import cn.com.cslc.aw.core.common.domain.SysProvince;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface OrgRepository extends BaseRepository<SysOrg> {
	
	//@Query("select s from SysOrg s where s.isShow=1 order by s.parentId,s.weight asc")
	@Query("select s from SysOrg s where s.isShow=1 order by s.parent.id,s.weight asc")
	List<SysOrg> findAllOrderedOrg();

//	@Query("select o from SysOrg o where o.id=:orgId")
//	SysOrg findTreeById(Long orgId);
	
	SysOrg findByCode(String code);
	
	@Query("select s from SysProvince s join s.sysOrgs t where t.id in (:orgIdList)")
	List<SysProvince> findOrgProvinceByIds(@Param("orgIdList") List<Long> orgIdList);

	@Query("select s from SysOrg s join fetch s.sysOrgType s1 where s1.code=20 and s.parent.id = 1")
	List<SysOrg> findAllSuperMarketOrgs();
	
}
