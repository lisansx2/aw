package cn.com.cslc.aw.reports.competitionprizecash.api;

import static cn.com.cslc.aw.core.common.datatables.DataTablesResponse.generateDTResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


import cn.com.cslc.aw.agent.service.AgentService;
import cn.com.cslc.aw.core.common.api.BaseApiController;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequest;
import cn.com.cslc.aw.core.common.datatables.DataTablesRequestParams;
import cn.com.cslc.aw.core.common.datatables.DataTablesResponse;
import cn.com.cslc.aw.reports.competitionprizecash.constant.RoundConstant;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashCondition;
import cn.com.cslc.aw.reports.competitionprizecash.dto.QueryCompetitionPrizeCashResult;
import cn.com.cslc.aw.reports.competitionprizecash.service.CompetitionPrizeCashReportService;






@RestController
@RequestMapping("/api/reports/competitionPrizeCashReport")
public class CompetitionPrizeCashReportApiController extends BaseApiController{
	
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private CompetitionPrizeCashReportService competitionPrizeCashReportService;  
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public DataTablesResponse<List<QueryCompetitionPrizeCashResult>> queryCompetitionPrizeCash(@DataTablesRequestParams DataTablesRequest dataTablesRequest, QueryCompetitionPrizeCashCondition queryCondition, @SessionAttribute Set<String> userAgentCodeSet, @SessionAttribute Set<String> userProvinceCodeSet) {
		if(StringUtils.isEmpty(queryCondition.getOrgCode())){
			queryCondition.getAgentCodeSet().addAll(userAgentCodeSet);
		}else{
			queryCondition.getAgentCodeSet().addAll(agentService.findAgentCodesByOrgCode(queryCondition.getOrgCode()));
		}
		if(StringUtils.isEmpty(queryCondition.getRoundNo())){
			queryCondition.setRoundNo(RoundConstant.ROUND_HALF_UP.getCode());
		}
		
		//对计算金额格式进行处理
		String roundNo = queryCondition.getRoundNo();
		Page<QueryCompetitionPrizeCashResult> page = competitionPrizeCashReportService.queryByCondition(queryCondition, dataTablesRequest.getPageRequest());
		for(QueryCompetitionPrizeCashResult pageRecord : page.getContent()){
			BigDecimal paidTicketAmountSum = pageRecord.getPaidTicketAmountSum();
			BigDecimal formatedPaidTicketAmountSum=null;
			if(StringUtils.isNotEmpty(roundNo)){
				if("20".equals(roundNo)){//舍尾取整
					formatedPaidTicketAmountSum = pageRecord.getPaidAmountAbandonSum();
				}else if("30".equals(roundNo)){//进位取整
					formatedPaidTicketAmountSum = pageRecord.getPaidAmountCarrySum();
				}else{
					formatedPaidTicketAmountSum = pageRecord.getPaidAmountRoundSum();//默认保留1位小数，四舍五入
				}
			}
			pageRecord.setFormatedPaidTicketAmountSum(paidTicketAmountSum.setScale(2, RoundingMode.HALF_DOWN).toPlainString());
			pageRecord.setFormatedComputePaidTicketAmountSum(formatedPaidTicketAmountSum.setScale(1, RoundingMode.HALF_DOWN).toPlainString());
		}
		return generateDTResponse(dataTablesRequest,page);
	}

}
