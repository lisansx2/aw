package cn.com.cslc.aw.reports.shopcommission.service;

import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionCondition;
import cn.com.cslc.aw.reports.shopcommission.dto.QueryShopCommissionResult;
import cn.com.cslc.aw.reports.shopcommission.repository.ShopCommissionReportRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class ShopCommissionReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ShopCommissionReportService.class);

	@Autowired
	private ShopCommissionReportRepositoryCustom shopCommissionReportRepositoryCustom;
	
	public Page<QueryShopCommissionResult> queryByCondition(QueryShopCommissionCondition queryCondition,
															Pageable pageRequest) {
		return shopCommissionReportRepositoryCustom.queryByCondition(queryCondition, pageRequest);
	}

}
