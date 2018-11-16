package cn.com.cslc.aw.balance.shopbalance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceCondition;
import cn.com.cslc.aw.balance.shopbalance.dto.QueryShopBalanceResult;

public interface ShopBalanceRepositoryCustom {
	
	Page<QueryShopBalanceResult> queryShopBalanceResultByCondition(QueryShopBalanceCondition queryShopBalanceCondition, Pageable pageable);
	
}
