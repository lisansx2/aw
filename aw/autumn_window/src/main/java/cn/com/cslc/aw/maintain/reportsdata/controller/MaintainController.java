package cn.com.cslc.aw.maintain.reportsdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.cslc.aw.core.common.controller.BaseController;
import cn.com.cslc.aw.reports.periodsales.service.PeriodSalesReportService;


@Controller
@RequestMapping("/maintain")
public class MaintainController  extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(MaintainController.class);
	
	private String indexViewName = "redirect:/maintain";

	@Autowired
	private PeriodSalesReportService periodSalesReportService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMaintain(){
		return "maintain/maintainIndex";
	}
	
	
	@RequestMapping(value="/excecutePeriodSalesReportJob",method=RequestMethod.GET)
	public String excecutePeriodSalesReportJob(RedirectAttributes model){
		periodSalesReportService.generateBaseReportData();
		model.addFlashAttribute("success", Boolean.TRUE);
		model.addFlashAttribute("message", "执行时段销量报表维护任务操作成功！");
		return getIndexViewName();
	}
	
	@Override
	protected String getIndexViewName() {
		return indexViewName;  
	}
}
