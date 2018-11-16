package cn.com.cslc.aw.domain.service;

import cn.com.cslc.aw.domain.dto.B_DomainDto;
import cn.com.cslc.aw.domain.repository.BDomainRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Transactional(readOnly = true)
@Service
public class BDomainService {

	private static final Logger LOG = LoggerFactory.getLogger(BDomainService.class);
	
	@Autowired
	private BDomainRepositoryCustom bDomainRepository;

	public List<B_DomainDto> queryBDomain(String domTypeCode) {
		return bDomainRepository.queryBDomain(domTypeCode);
	}

	public B_DomainDto queryBDomain(String domTypeCode, BigDecimal domKey){
		return bDomainRepository.queryBDomain(domTypeCode, domKey);
	}

}
