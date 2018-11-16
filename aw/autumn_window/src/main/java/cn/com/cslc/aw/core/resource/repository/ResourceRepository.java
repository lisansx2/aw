package cn.com.cslc.aw.core.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.cslc.aw.core.common.domain.SysResource;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface ResourceRepository extends BaseRepository<SysResource> {
	
	@Query("select s from SysResource s where s.sysResourceType.code='10' order by s.parentId,s.weight asc")
	List<SysResource> findAllOrderedMenu();
}
