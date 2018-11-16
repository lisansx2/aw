package cn.com.cslc.aw.bank.repository;

import cn.com.cslc.aw.bank.dto.B_BankDto;

import java.util.List;
import java.util.Set;

public interface BBankRepositoryCustom {

	List<B_BankDto> queryBBank(Set<String> provinceIdSet);

	List<B_BankDto> queryBBank(Set<String> provinceIdSet, String bankNo);
}
