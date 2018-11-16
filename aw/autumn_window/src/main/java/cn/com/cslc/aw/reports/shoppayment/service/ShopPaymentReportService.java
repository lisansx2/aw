package cn.com.cslc.aw.reports.shoppayment.service;

import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentCondition;
import cn.com.cslc.aw.reports.shoppayment.dto.QueryShopPaymentResult;
import cn.com.cslc.aw.reports.shoppayment.repository.ShopPaymentReportRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class ShopPaymentReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ShopPaymentReportService.class);

	@Autowired
	private ShopPaymentReportRepositoryCustom shopPaymentReportRepositoryCustom;
	
	public Page<QueryShopPaymentResult> queryByCondition(QueryShopPaymentCondition queryCondition,
														 Pageable pageRequest) {
		return shopPaymentReportRepositoryCustom.queryByCondition(queryCondition, pageRequest);
	}

}
