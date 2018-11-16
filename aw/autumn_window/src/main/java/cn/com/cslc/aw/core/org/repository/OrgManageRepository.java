package cn.com.cslc.aw.core.org.repository;



import cn.com.cslc.aw.core.common.domain.SysOrgManage;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface OrgManageRepository extends BaseRepository<SysOrgManage> {

	SysOrgManage findByName(String name);
	
	
}
