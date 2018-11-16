package cn.com.cslc.aw.province.repository;

import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;

import java.util.List;
import java.util.Set;

public interface BProvinceCenterRepositoryCustom {

	List<B_ProvinceCenterDto> queryBProvinceCenter(Set<String> provinceIdSet);
}
