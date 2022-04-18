package com.csi.jcl.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.entity.DbEntity;
import com.csi.jcl.entity.TestCase;
import com.csi.jcl.service.ThisService;
@Controller

public class JclKeyIn {
	@Autowired
	ThisService thisisservice;
	

	
	@GetMapping(value = "/jclkeyin")
	public String showjcl(Model model, HttpServletRequest request) {
		String tid = request.getParameter("tid");
		String jcl1= request.getParameter("jcl");
		
		String ad = null;
		String jcl = null;
	
		Map<String, String> findpara = thisisservice.findpara(tid);
//		System.out.println(findpara.get("para"));
		if(findpara.get("para") == null ) {
			findpara.remove("para");
			findpara.put("para", " ");
		}
		List<Map<String, String>> findjclkeyin = thisisservice.findjclkeyin(tid);
		List<Map<String, String>> finddbims = thisisservice.finddbims(jcl1);
		//db裡的ad jcl，都重覆的只需要取一個
		for (Map<String, String> getname : findjclkeyin) {
			ad=String.valueOf(getname.get("AD"));
			jcl = String.valueOf(getname.get("JCL"));
			break;
		}
		//去查testcase_db_detail裡的資料回顯
		for(Map<String, String> getdbims:finddbims) {
			model.addAttribute("finddbims", getdbims);
			break;
		}
		List<Map<String, String>> inputList = findjclkeyin.stream()
				.filter(item -> " I".equals(String.valueOf(item.get("OPEN_MODE")))
						|| " IO".equals(String.valueOf(item.get("OPEN_MODE")))
						|| "SORTIN".equals(String.valueOf(item.get("DD"))))
				.collect(Collectors.toList());

		List<Map<String, String>> ouputList = findjclkeyin.stream()
				.filter(item -> " O".equals(String.valueOf(item.get("OPEN_MODE")))
						|| " IO".equals(String.valueOf(item.get("OPEN_MODE")))
						|| "SORTOUT".equals(String.valueOf(item.get("DD"))))
				.collect(Collectors.toList());
		model.addAttribute("input", inputList);
		model.addAttribute("output", ouputList);
		model.addAttribute("adname",ad);
		model.addAttribute("jclname",jcl);
		model.addAttribute("tid",tid);
		model.addAttribute("para",findpara);
		return "update/jclkeyin";

	}

	@ResponseBody
	@GetMapping("/savekeyin")
	public String save(Model model, HttpServletRequest request,@RequestParam(value = "ss", required = false) String SS) {
		
		JSONArray jsonArray = JSONArray.parseArray(SS);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject key = (JSONObject) jsonArray.get(i);
//			System.out.println(key.get("AD").toString());
			String para = key.get("para").toString();
			String tid = key.get("tid").toString();
			String AD = key.get("AD").toString();
			String JCL = key.get("JCL").toString();
			String DB2_DELETE = key.get("DB2_DELETE").toString();
			String DB2_INCLUDE = key.get("DB2_INCLUDE").toString();
			String DB2_INSERT = key.get("DB2_INSERT").toString();
			String DB2_SELECT = key.get("DB2_SELECT").toString();
			String DB2_UPDATE = key.get("DB2_UPDATE").toString();
			String IMS_DELETE = key.get("IMS_DELETE").toString();
			String IMS_GET = key.get("IMS_GET").toString();
			String IMS_INSERT = key.get("IMS_INSERT").toString();
			String IMS_UPDATE = key.get("IMS_UPDATE").toString();
			
			thisisservice.savejclkeyin(AD,JCL,DB2_DELETE,DB2_INCLUDE,DB2_INSERT,DB2_SELECT,
					DB2_UPDATE,IMS_DELETE,IMS_GET,IMS_INSERT,IMS_UPDATE);
			thisisservice.savepara(para,tid);
		}
//		String AD = request.getParameter("AD");
//		String JCL = request.getParameter("JCL");
//		String DB2_DELETE = request.getParameter("DB2_DELETE");
//		String DB2_INCLUDE = request.getParameter("DB2_INCLUDE");
//		String DB2_INSERT = request.getParameter("DB2_INSERT");
//		String DB2_SELECT = request.getParameter("DB2_SELECT");
//		String DB2_UPDATE = request.getParameter("DB2_UPDATE");
//		String IMS_DELETE = request.getParameter("IMS_DELETE");
//		String IMS_GET = request.getParameter("IMS_GET");
//		String IMS_INSERT = request.getParameter("IMS_INSERT");
//		String IMS_UPDATE = request.getParameter("IMS_UPDATE");
//		thisisservice.savejclkeyin(AD,JCL,DB2_DELETE,DB2_INCLUDE,DB2_INSERT,DB2_SELECT,
//				DB2_UPDATE,IMS_DELETE,IMS_GET,IMS_INSERT,IMS_UPDATE);
//		return "update/jclkeyin";
		String jsonStr = "{\"success\":\"OK\"}";
		return jsonStr;

	}
}
