package com.csi.jcl.controller;

import com.csi.jcl.service.CodeListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodeListController {


    @Autowired
    private CodeListService codeListService;

    private static final Logger logger =
            LogManager.getLogger(CodeListController.class);

    @GetMapping("/codelist")
    public String viewHomePage2(Model model){
        logger.info("It's allCodeListPage");
        System.out.println(codeListService.getAllCodeList());
        model.addAttribute("listCodeList",codeListService.getAllCodeList());
        return "DefectSearch/allCodeList";
    }
}
