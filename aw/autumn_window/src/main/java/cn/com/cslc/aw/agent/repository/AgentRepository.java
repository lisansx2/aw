package cn.com.cslc.aw.agent.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.agent.domain.SysAgent;
import cn.com.cslc.aw.agent.dto.AgentProvince;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface AgentRepository extends BaseRepository<SysAgent>,AgentRepositoryCustom {
	
	SysAgent findByCode(String agentCode);

	List<AgentProvince> findByCodeIn(Set<String> agentNoSet);
	
	 @Query("select s from SysAgent s where s.sysProvince.provinceNo= :provinceNo")
	List<SysAgent> findByProvinceNo(@Param("provinceNo") String provinceNo);
	 
	 @Query("select s from SysAgent s where s.sysProvince.id= :provinceId")
	List<SysAgent> findByProvinceId(@Param("provinceId") Long provincId);

	@Modifying 
	@Query("delete from SysAgent  where sysProvince.id=:provinceId")
	void deleteByProvinceId(@Param("provinceId") Long provinceId);

	@Query("select s from SysAgent s join s.sysOrgAgents s1 join s1.sysOrg s2 where s2.code in(:orgCodes)")
	List<SysAgent> findAgentByOrgCodes(@Param("orgCodes") Set<String> orgCodes);
}
