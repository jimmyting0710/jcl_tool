package com.csi.jcl.controller;

import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.model.CheckPointJoinTestCaseModel;
import com.csi.jcl.model.CheckPointModel;
import com.csi.jcl.service.AdJclExeorderService;
import com.csi.jcl.service.JclService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 定義AdJclExeorderController類別
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/09/13
 */
@Controller
@RequestMapping("/jcl_sequence")
public class AdJclExeorderController {

    private static final Logger logger =
            LogManager.getLogger(AdJclExeorderController.class);

    private final AdJclExeorderService adJclExeorderService;
    private final JclService jclService;

    public AdJclExeorderController(AdJclExeorderService adJclExeorderService, JclService jclService) {
        this.adJclExeorderService = adJclExeorderService;
        this.jclService = jclService;
    }

    /**
     * 執行順序查詢首頁
     *
     * @return
     * @author si1206 Sam Chen
     * @date 2021/09/13
     */
    @GetMapping("/jcl_sequence_home")
    public String sequenceHomePage() {
        logger.info("It's sequenceHomePage");
        return "jcl_sequence/jcl_sequence_home";
    }


    /**
     * 執行順序查詢
     *
     * @param adName
     * @return jcl_sequence_list.html
     * @author si1206 Sam Chen
     * @date 2021/09/16
     */
    @GetMapping("/jcl_sequence_list")
    public String findJclSequence(@RequestParam("adName") String adName, Model model) {
        logger.info("It's findJclSequencePage");
        List<AdJclEntity> listJclByAd = jclService.findJclByAd(adName);
        model.addAttribute("ad", adName);
        if (listJclByAd.size() == 0) {
            model.addAttribute("addesc", "");
        } else {
            String addesc = listJclByAd.get(0).getAddesc();
            model.addAttribute("addesc", addesc);
        }
        return "jcl_sequence/jcl_sequence_list";
    }

    /**
     * 取得jcl執行順序
     *
     * @param adName
     * @return adJclExeorderService.getJclSequence(adName)
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    @GetMapping("/getJclSequenceData")
    @ResponseBody
    public Map<Integer, List<String>> getJclSequence(String adName) {
        logger.info("Call adJclExeorderService.getJclSequence(adName)");
        return adJclExeorderService.getJclSequence(adName);
    }

    /**
     * 取得所有要顯示在頁面上的selfjcl
     *
     * @param adName
     * @return adJclExeorderService.getAllSelfJcl(adName)
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    @GetMapping("/getAllSelfJcl")
    @ResponseBody
    public Set<String> getAllSelfJcl(String adName) {
        logger.info("Call adJclExeorderService.getAllSelfJcl(adName)");
        return adJclExeorderService.getAllSelfJcl(adName);
    }

    /**
     * 取得之後要執行的AD名稱
     *
     * @param beforeAd,beforeJcl
     * @return adJclExeorderService.findNextAd(beforeAd, beforeJcl)
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    @GetMapping("/findNextAd")
    @ResponseBody
    public List<String> findNextAd(String beforeAd, String beforeJcl) {
        logger.info("call adJclExeorderService.findNextAd(beforeAd, beforeJcl)");
        return adJclExeorderService.findNextAd(beforeAd, beforeJcl);
    }

    /**
     * 取得資料庫比對斷點的JCL名稱
     *
     * @param adName
     * @return adJclExeorderService.findBreakPoint(adName)
     * @author si1206 Sam Chen
     * @date 2021/10/04
     */
    @GetMapping("/findBreakPoint")
    @ResponseBody
    public List<String> findBreakPoint(String adName) {
        logger.info("call adJclExeorderService.findBreakPoint(adName)");
        return adJclExeorderService.findBreakPoint(adName);
    }

    /**
     * 取得檔案查核點的JCL名稱
     *
     * @param adName
     * @return adJclExeorderService.findCheckPoint(adName)
     * @author si1206 Sam Chen
     * @date 2021/10/04
     */
    @GetMapping("/findCheckPoint")
    @ResponseBody
    public Set<String> findCheckPoint(String adName) {
        logger.info("call adJclExeorderService.findCheckPoint(adName)");
        return adJclExeorderService.findCheckPoint(adName);
    }

    /**
     * 資料庫比對斷點分頁
     *
     * @param jcl
     * @return adJclExeorderService.findCheckPoint(adName)
     * @author si1206 Sam Chen
     * @date 2021/10/04
     */
    @GetMapping("/findBreakPointDetail/{jcl}")
    public String findBreakPointDetail(Model model, @PathVariable("jcl") String jcl) {
        logger.info("資料庫比對斷點分頁");
        List<CheckPointModel> findBreakPointDetail = adJclExeorderService.findBreakPointDetail(jcl);
        model.addAttribute("findBreakPointDetail", findBreakPointDetail);
        return "jcl_sequence/breakpoint_detail";
    }

    /**
     * 檔案查核點分頁
     *
     * @param jcl
     * @return /jcl_sequence/checkpoint_detail
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    @GetMapping("/findCheckPointDetail/{jcl}")
    public String findCheckPointDetail(Model model, @PathVariable("jcl") String jcl) {
        logger.info("檔案查核點分頁");
        List<CheckPointJoinTestCaseModel> findCheckPointInputDetail = adJclExeorderService.findCheckPointInput(jcl);
        List<CheckPointJoinTestCaseModel> findCheckPointOutputDetail = adJclExeorderService.findCheckPointOutput(jcl);
        String sprint = findCheckPointInputDetail.get(0).getSprintno();
        String ad = findCheckPointInputDetail.get(0).getAd();
        String addesc = findCheckPointInputDetail.get(0).getAddesc();
        String jclName = findCheckPointInputDetail.get(0).getJcl();
        String cpdd = findCheckPointInputDetail.get(0).getCpdd();
        String cht_ap = findCheckPointInputDetail.get(0).getCht_ap();
        String cht_dc = findCheckPointInputDetail.get(0).getCht_dc();
        model.addAttribute("sprint", sprint);
        model.addAttribute("ad", ad);
        model.addAttribute("addesc", addesc);
        model.addAttribute("jclName", jclName);
        model.addAttribute("cpdd", cpdd);
        model.addAttribute("cht_ap", cht_ap);
        model.addAttribute("cht_dc", cht_dc);
        Set findCheckPointInputDetailSet = findCheckPointInputDetail.stream()
                .map(CheckPointJoinTestCaseModel::getDsn)
                .collect(Collectors.toSet());
        model.addAttribute("findCheckPointInputDetailSet", findCheckPointInputDetailSet);

        Set findCheckPointOutputDetailSet = findCheckPointOutputDetail.stream()
                .map(CheckPointJoinTestCaseModel::getDsn)
                .collect(Collectors.toSet());
        model.addAttribute("findCheckPointOutputDetailSet", findCheckPointOutputDetailSet);

        return "jcl_sequence/checkpoint_detail";
    }
}
