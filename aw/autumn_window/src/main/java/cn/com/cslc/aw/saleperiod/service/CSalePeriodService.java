package cn.com.cslc.aw.saleperiod.service;

import cn.com.cslc.aw.game.domain.SysGameSystem;
import cn.com.cslc.aw.saleperiod.dto.C_SalePeriodDto;
import cn.com.cslc.aw.saleperiod.repository.CSalePeriodRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Transactional(readOnly = true)
@Service
public class CSalePeriodService {

	private static final Logger LOG = LoggerFactory.getLogger(CSalePeriodService.class);
	
	@Autowired
	private CSalePeriodRepositoryCustom cSalePeriodRepository;

	public List<C_SalePeriodDto> querySalePeriodFromC_Sale_Period(Set<String> userProvinceCodeSet) {
		return cSalePeriodRepository.querySalePeriodFromC_Sale_Period(userProvinceCodeSet);
	}

}
