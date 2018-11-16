package cn.com.cslc.aw.reports.shopcommission.repository;

import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopCommissionReportRepositoryCustom {
	
	Page<QueryShopCommissionResult> queryByCondition(QueryShopCommissionCondition queryCondition, Pageable pageable);

}
