package cn.com.cslc.aw.core.common.repository;

import cn.com.cslc.aw.core.common.domain.SysCredentialType;

public interface SysCredentialTypeRepository extends BaseRepository<SysCredentialType> {
	SysCredentialType findByCode(String code);
}
