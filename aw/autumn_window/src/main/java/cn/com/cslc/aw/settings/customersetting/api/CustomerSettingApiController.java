package cn.com.cslc.aw.settings.customersetting.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;

import cn.com.cslc.aw.customer.service.CustomerService;
import cn.com.cslc.aw.settings.customersetting.dto.QueryCustomerSettingResult;


@RestController
@RequestMapping("/api/settings/customerSetting")
public class CustomerSettingApiController extends BaseApiController{
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/queryCustomerSetted", method = POST)
	public DataTablesResponse<List<QueryCustomerSettingResult>> queryCustomers(@DataTablesRequestParams DataTablesRequest dataTablesRequest, @SessionAttribute Set<String> userProvinceCodeSet) {
		Page<QueryCustomerSettingResult> page= customerService.queryCustomerListByProvinceCodes(userProvinceCodeSet, dataTablesRequest.getPageRequest());
		return generateDTResponse(dataTablesRequest, page);
	}

}
