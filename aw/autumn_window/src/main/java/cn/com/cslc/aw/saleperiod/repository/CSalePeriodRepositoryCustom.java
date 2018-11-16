package cn.com.cslc.aw.saleperiod.repository;

import cn.com.cslc.aw.core.common.repository.BaseRepository;
import cn.com.cslc.aw.saleperiod.dto.C_SalePeriodDto;

import java.util.List;
import java.util.Set;

public interface CSalePeriodRepositoryCustom {

	List<C_SalePeriodDto> querySalePeriodFromC_Sale_Period(Set<String> userProvinceCodeSet);
}
