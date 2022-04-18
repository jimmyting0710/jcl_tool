package com.csi.jcl.controller;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.AdJclExeroderRepository;
import com.csi.jcl.model.AdJclExeorderModel;
import com.csi.jcl.model.AdJclModel;
import com.csi.jcl.service.AdJclExeorderService;
import com.csi.jcl.service.JclService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 這個純粹測試用
 */
@RestController
public class TestController {

	
    private final JclService jclService;
    private final AdJclExeorderService adJclExeorderService;
    private final AdJclExeroderRepository adJclExeroderRepository;

    public TestController(JclService jclService, AdJclExeorderService adJclExeorderService, AdJclExeroderRepository adJclExeroderRepository) {
        this.jclService = jclService;
        this.adJclExeorderService = adJclExeorderService;
        this.adJclExeroderRepository = adJclExeroderRepository;
    }


//    @GetMapping("/test")
//    public List<AdJclExeorderEntity> findAll(){
//        return adJclExeroderRepository.findAll();
//    }


    /**
     * @param adName
     * @return
     * @author si1206 Sam Chen
     * @date 2021/09/13
     */
//    @GetMapping("/test")
//    public Map<Integer, List<String>> namedParameterJdbcTemplateSql(String adName) {
//
//        String sql = " SELECT ad, jcl, before_jcl_ad, beforejcl, afterjcl FROM ad_jcl_exeorder WHERE AD= :adName";
//
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("adName", adName);
//
//        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
//
//        List<AdJclExeorderModel> adJclExeorderModelList = JSONObject.parseArray(JSONObject.toJSONString(list), AdJclExeorderModel.class);
//
//        System.out.println(adJclExeorderModelList.size());
//
//        // 定義afterJclSet來裝afterJcl的資料
//        Set<String> afterJclSet = new HashSet<String>();
//
//        // 定義selfJclSet來裝selfJcl的資料
//        Set<String> selfJclSet = new HashSet<String>();
//
//        // 最終傳給前端的JSON data
//        Map<Integer, List<String>> jsonData = new LinkedHashMap<>();
//
//        // 加入資料
//        for (int i = 0; i < adJclExeorderModelList.size(); i++) {
//            if (adJclExeorderModelList.get(i).getAfterjcl() != null) {
//                afterJclSet.add(adJclExeorderModelList.get(i).getAfterjcl());
//            }
//            if (adJclExeorderModelList.get(i).getJcl() != null) {
//                selfJclSet.add(adJclExeorderModelList.get(i).getJcl());
//            }
//        }
//
//        // 取得第一個JCL
//        Set<String> findFirstJcl = new HashSet<String>();
//        findFirstJcl.addAll(selfJclSet);
//        findFirstJcl.removeAll(afterJclSet);
////        System.out.println("selfJclSet: " + selfJclSet);
////        System.out.println("afterJclSet: " + afterJclSet);
////        System.out.println(findFirstJcl);
//
//
//
//
//        return jsonData;
//    }

    @GetMapping("/test")
    public List<String> findNextAd(String beforeAd, String beforeJcl ){

        String sql = " SELECT aj.AD, aj.addesc FROM ad_jcl aj JOIN ad_jcl_exeorder aje ON aj.ad = aje.ad  WHERE beforead = :beforeAd AND beforejcl = :beforeJcl ";

        Map<String, Object> params = new HashMap<>();

        params.put("beforeAd", beforeAd);
        params.put("beforeJcl", beforeJcl);
        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
        List<AdJclModel> adJclModelList = JSONObject.parseArray(JSONObject.toJSONString(list), AdJclModel.class);
        List<String> findNextAd = adJclModelList.stream()
                .filter(a -> a.getAd()!= null && a.getAddesc()!=null)
                .map(a -> a.getAd()+a.getAddesc())
                .collect(Collectors.toList());
//        System.out.println(findNextAd);
        return findNextAd;
    }
}
