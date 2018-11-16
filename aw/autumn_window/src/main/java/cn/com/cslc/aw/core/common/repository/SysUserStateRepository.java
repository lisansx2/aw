package cn.com.cslc.aw.core.common.repository;

import cn.com.cslc.aw.core.common.domain.SysUserState;

public interface SysUserStateRepository extends BaseRepository<SysUserState> {
	SysUserState findByCode(String code);
}
