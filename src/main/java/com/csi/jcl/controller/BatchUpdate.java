package com.csi.jcl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.service.ThisService;

@RestController
public class BatchUpdate {
	@Autowired
	ThisService thisService;

	
//	@ResponseBody
	@RequestMapping(value = "/batchupdate", method = RequestMethod.GET)
	public String name(Model model, HttpServletRequest request,
			@RequestParam(value = "ss", required = false) String SS) {
//		System.out.println("=====================================");
		JSONArray jsonArray = JSONArray.parseArray(SS);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject key = (JSONObject) jsonArray.get(i);
//			System.out.println(String.valueOf(key.get("AD")));
			if (!" ".equals(key.get("STATUS")) || !" ".equals(key.get("TESTER_ID"))) {
//			if (!key.get("STATUS").equals("1") && !key.get("TESTER_ID").equals("1")) {
				String status = "";
				if ("未測試".equals(String.valueOf(key.get("STATUS")))) {
					status = "N";
				} else if ("測試中".equals(String.valueOf(key.get("STATUS")))) {
					status = "T";
				} else if ("測試通過".equals(String.valueOf(key.get("STATUS")))) {
					status = "P";
				} else if ("測試失敗".equals(String.valueOf(key.get("STATUS")))) {
					status = "F";
				}
//				String status = key.get("STATUS").toString();
				String tid = key.get("TID").toString();
				String tester_id = key.get("TESTER_ID").toString();
				String rdatetime = key.get("RDATETIME").toString();
				String ad = String.valueOf(key.get("AD"));
				
				String rid = "";
//				System.out.println(ad+"======"+tester_id+"======"+rdatetime+"======"+status+"======"+tid);
				thisService.savebatchresult(status, tester_id, tid, rdatetime, rid, ad);
				thisService.updatebatch(tid, tester_id, status, ad);
			}
		}
//		return "batchupdate/showb";
		String jsonStr = "{\"success\":\"OK\"}";
		return jsonStr;

	}
}
