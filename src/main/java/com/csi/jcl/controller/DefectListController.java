package com.csi.jcl.controller;

import com.csi.jcl.service.DefectListService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefectListController {

    @Autowired
    private DefectListService defectListService;

    private static final Logger logger =
            LogManager.getLogger(DefectListController.class);

    @GetMapping("/123")
    public String viewHomePage(Model model) {
        logger.info("It's allDefectPage");
        model.addAttribute("listDefectList", defectListService.getAllDefectList());
        return "DefectSearch/allCodeList";
    }

}
