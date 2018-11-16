package cn.com.cslc.aw.core.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.agent.domain.SysOrgAgent;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface OrgAgentRepository extends BaseRepository<SysOrgAgent> {
	
	@Query("select s from SysOrgAgent s join fetch s.sysAgent where s.sysOrg.id in (:orgIdList)")
	List<SysOrgAgent> findOrgAgent(@Param("orgIdList") List<Long> orgIdList);

	@Modifying
	@Query("delete from SysOrgAgent  where sysOrg.id=:orgId")
	void deleteByOrgId(@Param("orgId") Long orgId);

}
