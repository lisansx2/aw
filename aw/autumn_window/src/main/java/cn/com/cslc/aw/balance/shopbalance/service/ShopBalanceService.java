package cn.com.cslc.aw.balance.shopbalance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceCondition;
import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceResult;
import cn.com.cslc.aw.balance.shopbalance.repository.ShopBalanceRepository;

@Transactional(readOnly = true)
@Service
public class ShopBalanceService {

	private static final Logger LOG = LoggerFactory.getLogger(ShopBalanceService.class);
	
	@Autowired
	private ShopBalanceRepository shopBalanceRepository;
	
	public Page<QueryShopBalanceResult> queryShopBalanceResultByCondition(QueryShopBalanceCondition queryShopBalanceCondition, Pageable pageRequest) {
		return shopBalanceRepository.queryShopBalanceResultByCondition(queryShopBalanceCondition, pageRequest);
	}
}
