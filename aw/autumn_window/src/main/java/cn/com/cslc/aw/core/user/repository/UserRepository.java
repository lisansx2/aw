package cn.com.cslc.aw.core.user.repository;

import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysUser;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface UserRepository extends BaseRepository<SysUser> {
	
	SysUser findByUserName(String username);
	
	SysUser findByUmpUserId(Long umpUserId);
	
	void deleteByIdIn(Long... ids);
	
	List<SysUser> findByIdIn(Long... ids);
}
