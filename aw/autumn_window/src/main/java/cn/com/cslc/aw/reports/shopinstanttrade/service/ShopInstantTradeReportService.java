package cn.com.cslc.aw.reports.shopinstanttrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeCondition;
import cn.com.cslc.aw.reports.shopinstanttrade.dto.QueryShopInstantTradeResult;
import cn.com.cslc.aw.reports.shopinstanttrade.repository.ShopInstantTradeReportRepository;

@Service
public class ShopInstantTradeReportService {

	@Autowired
	private ShopInstantTradeReportRepository shopInstantTradeReportRepository;
	
	public Page<QueryShopInstantTradeResult> queryByCondition(QueryShopInstantTradeCondition queryCondition,
			Pageable pageRequest) {
		return shopInstantTradeReportRepository.queryByCondition(queryCondition, pageRequest);
	}
}
