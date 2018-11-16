package cn.com.cslc.aw.province.service;

import cn.com.cslc.aw.province.dto.B_ProvinceCenterDto;
import cn.com.cslc.aw.province.repository.BProvinceCenterRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Transactional(readOnly = true)
@Service
public class BProvinceCenterService {

	private static final Logger LOG = LoggerFactory.getLogger(BProvinceCenterService.class);
	
	@Autowired
	private BProvinceCenterRepositoryCustom bProvinceCenterRepository;

	public List<B_ProvinceCenterDto> queryBProvinceCenter(Set<String> provinceIdSet) {
		return bProvinceCenterRepository.queryBProvinceCenter(provinceIdSet);
	}

}
