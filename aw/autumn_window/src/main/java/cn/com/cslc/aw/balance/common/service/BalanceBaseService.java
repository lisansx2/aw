package cn.com.cslc.aw.balance.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.cslc.aw.balance.common.dto.AccountState;

@Transactional(readOnly = true)
@Service
public class BalanceBaseService {

	private static final Logger LOG = LoggerFactory.getLogger(BalanceBaseService.class);
	private static List<AccountState> accountStateList = Lists.newArrayList();
	
	static{	
		AccountState frozenState = new AccountState();
		frozenState.setCode("0");
		frozenState.setName("已冻结");
		
		AccountState enabledState = new AccountState();
		enabledState.setCode("10");
		enabledState.setName("已启用");
		
		accountStateList.add(frozenState);
		accountStateList.add(enabledState);
		
	}
	public List<AccountState> getAllAccountState() {
		return accountStateList;
	}
}
