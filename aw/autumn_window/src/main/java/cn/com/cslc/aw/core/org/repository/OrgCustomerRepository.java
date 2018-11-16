package cn.com.cslc.aw.core.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.customer.domain.SysOrgCustomer;

public interface OrgCustomerRepository extends BaseRepository<SysOrgCustomer> {
	
	@Query("select s from SysOrgCustomer s join fetch s.sysCustomer where s.sysOrg.id in (:orgIdList)")
	List<SysOrgCustomer> findOrgCustomer(@Param("orgIdList") List<Long> orgIdList);

	@Modifying
	@Query("delete from SysOrgCustomer  where sysOrg.id=:orgId")
	void deleteByOrgId(@Param("orgId") Long orgId);

}
