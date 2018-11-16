package cn.com.cslc.aw.reports.shopinstanttrade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeResult;

public interface ShopInstantTradeReportRepositoryCustom {
	
	Page<QueryShopInstantTradeResult> queryByCondition(QueryShopInstantTradeCondition queryCondition, Pageable pageable);

}
