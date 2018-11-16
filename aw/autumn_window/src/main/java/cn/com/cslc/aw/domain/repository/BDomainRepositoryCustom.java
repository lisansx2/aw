package cn.com.cslc.aw.domain.repository;

import cn.com.cslc.aw.domain.dto.B_DomainDto;

import java.math.BigDecimal;
import java.util.List;

public interface BDomainRepositoryCustom {

	List<B_DomainDto> queryBDomain(String domTypeCode);

	B_DomainDto queryBDomain(String domTypeCode, BigDecimal domKey);
}
