package com.csi.jcl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csi.jcl.entity.TestCase;
import com.csi.jcl.service.ThisService;

@Controller


public class Update {

	@Autowired
	ThisService thisService;

	@RequestMapping("/su")
	public String sus(Model model) {
		List<Map<String, String>> findtest_type = thisService.findtest_type();
		List<Map<String, String>> findsystem_operation = thisService.findsystem_operation();
		model.addAttribute("test_type",findtest_type);
		model.addAttribute("system_operation",findsystem_operation);

		return "update/su";
	}

//	@ResponseBody
	@GetMapping(value = "/show")
	public String find(Model model, HttpServletRequest request, Pageable pageable, @RequestParam("page") Integer page) {
		String test_type= request.getParameter("test_type");
		String program_type= request.getParameter("program_type");
		String system_operation= request.getParameter("system_operation");
		String online_operation= request.getParameter("online_operation");
		String ad = request.getParameter("AD");
//		String sprint = request.getParameter("sprint");
		String jcl = request.getParameter("JCL");
//		String tid = request.getParameter("TID");
		
		
		
		List<Map<String,String>> find=thisService.findbatchforad(ad);
		List<Map<String, String>> findtest_type = thisService.findtest_type();
		List<Map<String, String>> findsystem_operation = thisService.findsystem_operation();
//		System.out.println(page+"===="+test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
	
		
		
		if (ad != "" || !ad.equals(null)) {
			List<Map<String, String>> findissue = thisService.findissue();
//		List<Map<ThisService, Object>> findad = thisService.findbyadsprint(ad, sprint,jcl);
			// 找使用者給下拉選單
			List<Map<ThisService, Object>> findtester = thisService.findbycodetypeid("TESTER");
			// 找status給下拉選單
			List<Map<ThisService, Object>> findtestresult = thisService.findbyresult("TEST_RESULTS");
//		List<Map<ThisService, Object>> findinner = thisService.findinner();

			// leftjoin查詢後的資料
//			List<Map<ThisService, Object>> findleftinner = thisService.findleftinner(ad, sprint, jcl);
			
			List<Map<String, String>> findbatchonline =thisService.findbatchonline(ad,jcl,test_type,program_type,system_operation,online_operation);
//			System.out.println(findbatchonline.size());
			if(findbatchonline.size()<=0) {
				return "update/su2";
			}
			
			List<Map<String, String>> breakpoint = thisService.findbreakpoint(ad);

			List<Map<String, String>> checkpoint = thisService.findcheckpoint(ad);
//			List<Map<String, String>> afterRemove = new ArrayList<Map<String, String>>();
//			for (Map<String, String> all : checkpoint) {
//				Map<String, String> d = new HashMap<String, String>(all);
//				d.remove("COUNT(PASSFORM)");
//				d.remove("COUNT(IOCHECKLIST)");
//				d.remove("COUNT(ALLJCL)");
//				afterRemove.add(d);
//			}

//			System.out.println(afterRemove);
//			for(Map<String, String> a:afterRemove) {
//				for (Entry<String, String> allmap : a.entrySet()) {
//					System.out.print(allmap.getKey() + "    ");
//					System.out.println(allmap.getValue());
//				}
//			}

			// 計算資料的起始與結束位置
			int start = (int) pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), findbatchonline.size());

			// 把adJclModelList轉換成Page型別
			Page<TestCase> allJclList = new PageImpl(findbatchonline.subList(start, end), pageable, findbatchonline.size());

			// 顯示頁面總數
			List<Integer> pageList = new ArrayList<Integer>();
			for (int i = 0; i < allJclList.getTotalPages(); i++) {
				pageList.add(i);
			}
			model.addAttribute("checkpoint", checkpoint);
			model.addAttribute("breakpoint", breakpoint);
			model.addAttribute("result", findtestresult);
			model.addAttribute("tester", findtester);
			model.addAttribute("allJclList", allJclList);
//			model.addAttribute("sprint", sprint);
			model.addAttribute("adName", ad);
			model.addAttribute("page", page);
			model.addAttribute("pageList", pageList);
			model.addAttribute("jcl", jcl);
			model.addAttribute("test_type",findtest_type);
			model.addAttribute("system_operation",findsystem_operation);
			model.addAttribute("test_type1", test_type);
			model.addAttribute("program_type1", program_type);
			model.addAttribute("system_operation1", system_operation);
			model.addAttribute("online_operation1", online_operation);
//			for(Map<String, String> allissue:findissue)
			model.addAttribute("issue", findissue);
			for (Map<String, String> all : find) 
			model.addAttribute("batch", all);
//		model.addAttribute("havetime", findleftinner);

		}

		return "update/show";
	}

	
}
