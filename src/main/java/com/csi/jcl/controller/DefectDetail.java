package com.csi.jcl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csi.jcl.service.ThisService;


@Controller

public class DefectDetail {
	@Autowired
	ThisService thisservice;
	
	@GetMapping(value = "/DefectDetail")
	public String showDefectDetail(Model model, HttpServletRequest request) {
		String jid =request.getParameter("jid");
		List<Map<String, String>> finddefectdetail = thisservice.finddefectdetail(jid);
		model.addAttribute("detail", finddefectdetail);
		return "update/showdetail";
		
	}
}
