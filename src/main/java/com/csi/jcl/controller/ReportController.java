package com.csi.jcl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csi.jcl.service.ThisService;

@Controller
public class ReportController {
	@Autowired
	ThisService service;

	@GetMapping(value = "/report")
	public String report() {
		return "report/report";
	}

//	@ResponseBody
	@GetMapping(value = "/findtestcase")
	public String find(Model model) {
		List<Map<String, String>> testcase = service.findtestcase();
		List<Map<String, String>> testcaseOk = service.findtestcaseOk();
		List<Map<String, String>> testcaseFail = service.findtestcaseFail();
		List<Map<String, String>> testcaseUndone = service.findtestcaseUndone();
		List<Map<String, String>> DefectUndone = service.findDefectUndone();
		List<Map<String, String>> DefectDone = service.findDefectDone();
		
		model.addAttribute("defectundone", DefectUndone);
		model.addAttribute("defectdone", DefectDone);
		model.addAttribute("testcase", testcase);
		model.addAttribute("OK", testcaseOk);
		model.addAttribute("Fail", testcaseFail);
		model.addAttribute("Undone", testcaseUndone);
		return "report/testcase";

	}
}
