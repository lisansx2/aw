package cn.com.cslc.aw.reports.shophistorybalance.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceCondition;
import cn.com.cslc.aw.reports.shophistorybalance.dto.QueryShopHistoryBalanceResult;

public interface ShopHistoryBalanceReportRepositoryCustom {
	
	Page<QueryShopHistoryBalanceResult> queryByCondition(QueryShopHistoryBalanceCondition queryCondition, Pageable pageable);
	
	Page<QueryShopHistoryBalanceResult> queryByConditionNatural(QueryShopHistoryBalanceCondition queryCondition, Pageable pageable);
	
	List<Object[]> queryProvinceIdByAgentNo(List<String> agentNoList);
}
