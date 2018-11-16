package cn.com.cslc.aw.core.authority.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.cslc.aw.core.common.domain.SysAuthority;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface AuthorityRepository extends BaseRepository<SysAuthority> {	
	@Modifying
	@Query("delete from SysAuthority  where sysUser.id=:userId")
	void deleteByUserId(@Param("userId") Long userId);
	
	@Query("select s from SysAuthority  s  join fetch s.sysRole where s.sysUser.id=:userId")
	List<SysAuthority> findSysAuthorityByUserId(@Param("userId") Long userId);
}
