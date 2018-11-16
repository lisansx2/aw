package cn.com.cslc.aw.reports.shoppayment.repository;

import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentCondition;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopPaymentReportRepositoryCustom {
	
	Page<QueryShopPaymentResult> queryByCondition(QueryShopPaymentCondition queryCondition, Pageable pageable);

}
