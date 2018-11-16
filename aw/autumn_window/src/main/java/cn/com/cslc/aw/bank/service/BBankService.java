package cn.com.cslc.aw.bank.service;

import cn.com.cslc.aw.bank.dto.B_BankDto;
import cn.com.cslc.aw.bank.repository.BBankRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Transactional(readOnly = true)
@Service
public class BBankService {

	private static final Logger LOG = LoggerFactory.getLogger(BBankService.class);
	
	@Autowired
	private BBankRepositoryCustom bBankRepository;

	public List<B_BankDto> queryBBank(Set<String> provinceIdSet) {
		return bBankRepository.queryBBank(provinceIdSet);
	}

	public List<B_BankDto> queryBBank(Set<String> provinceIdSet, String bankNo){
		return bBankRepository.queryBBank(provinceIdSet, bankNo);
	}

}
