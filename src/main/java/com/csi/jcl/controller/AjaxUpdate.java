package com.csi.jcl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.service.ThisService;

@RestController
//@RequestMapping("/ajax")
public class AjaxUpdate {

	@Autowired
	ThisService thisService;

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpServletRequest request,
			@RequestParam(value = "ss", required = false) String SS) throws IOException {
//		System.out.println("========================");
		// 轉jsonarray
//		JSONObject jsonObject=JSONObject.parseObject(SS);
		JSONArray jsonArray = JSONArray.parseArray(SS);

		for (int i = 0; i < jsonArray.size(); i++) {
//			Integer seq = thisService.getseq();
			JSONObject key = (JSONObject) jsonArray.get(i);
			if (!" ".equals(key.get("STATUS")) || !" ".equals(key.get("TESTER_ID"))) {

//				String status = key.get("STATUS").toString();
				String status = "";
				if ("未測試".equals(key.get("STATUS").toString())) {
					status = "N";
				} else if ("測試中".equals(key.get("STATUS").toString())) {
					status = "T";
				} else if ("測試通過".equals(key.get("STATUS").toString())) {
					status = "P";
				} else if ("測試失敗".equals(key.get("STATUS").toString())) {
					status = "F";
				}
				String tid = key.get("TID").toString();
				String tester_id = key.get("TESTER_ID").toString();
				String rdatetime = String.valueOf(key.get("RDATETIME"));
				String rid = "";
				String iString = String.valueOf(key.get("bb"));

//				System.out.println(iString);
//				System.out.println(tid+"======"+tester_id+"======"+rdatetime+"======"+rid);
				if ("1".equals(iString)) {
					thisService.saveresulttemp(status, tid, tester_id,rdatetime);
				} else {
					thisService.saveresult(status, tid, tester_id, rdatetime, rid);
				}
				thisService.updatetestcase(tid, status, tester_id);
			} else {
				String jsonStr = "{\"error\":\"error\"}";

			}
		}


		// ajax回寫json格式
		String jsonStr = "{\"success\":\"OK\"}";
		return jsonStr;

	}
	
}
