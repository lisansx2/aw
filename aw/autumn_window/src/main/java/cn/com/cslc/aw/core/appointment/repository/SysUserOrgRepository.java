package cn.com.cslc.aw.core.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.domain.SysUserOrg;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface SysUserOrgRepository extends BaseRepository<SysUserOrg> {
		
	@Modifying
	@Query("delete from SysUserOrg  where sysUser.id=:userId")
	void deleteByUserId(@Param("userId") Long userId);
	
	@Query("select t from SysUserOrg t join fetch t.sysOrg t1 where t.sysUser.id=:userId")
	List<SysUserOrg> findOrgByUserId(@Param("userId") Long userId);
}
