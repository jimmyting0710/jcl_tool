package com.csi.jcl.service;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.DefectListRepository;
import com.csi.jcl.model.DefectListAndAdJclModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 實作ListAllDefectService功能
 *
 * @author si1255 Morris Mao
 * @date 2021/10/25
 */

@Service
public class ListAllDefectServiceImpl implements ListAllDefectService {

    private static final Logger logger =
            LogManager.getLogger(ListAllDefectServiceImpl.class);

    private final DefectListRepository defectListRepository;


    public ListAllDefectServiceImpl(DefectListRepository defectListRepository) {
        this.defectListRepository = defectListRepository;
    }

    @Override
    public List<DefectListAndAdJclModel> listAllDefect(String ad, String jcl, List<String> issueStatusList, String testType,String programType, String systemOperation) {

        System.out.println("systemOperation :"+systemOperation);
        //如果SPRINT跟DEFECT狀態都選擇ALL
        if (issueStatusList.contains("ALL") && testType.contains("ALL")&& systemOperation.contains("ALL")  ) {
            testType = "";
            systemOperation="";
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123(ad,jcl,testType,programType,systemOperation);
            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("testType and DEFECT selected All and systemOperation");
            return DefectListAndAdJclModel;
        //只有SPRINT選擇ALL
        }else if (testType.contains("ALL")&& systemOperation.contains("ALL")) {
            testType = "";
            systemOperation = "";
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect( ad, jcl, issueStatusList,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("testType and systemOperation selected All");
            return DefectListAndAdJclModel;
        //DEFECT選擇ALL
        } else if(testType.contains("ALL")&&issueStatusList.contains("ALL")){

            testType="";
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123( ad, jcl,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info(" testType and  issueStatusList selected All");
            return DefectListAndAdJclModel;

        }else if(testType.contains("ALL")){
            testType = "";

            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect( ad, jcl, issueStatusList,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("testType selected All");
            return DefectListAndAdJclModel;

        }else if(issueStatusList.contains("ALL")&& systemOperation.contains("ALL")){
            systemOperation="";
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123( ad, jcl,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info(" DEFECT selected All");
            return DefectListAndAdJclModel;

        } else if(systemOperation.contains("ALL")){
            systemOperation = "";
            System.out.println("testType :"+testType);
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect( ad, jcl, issueStatusList,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("systemOperation selected All");
            return DefectListAndAdJclModel;

        }else if(issueStatusList.contains("ALL")){

            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123( ad, jcl,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info(" DEFECT selected All");
            return DefectListAndAdJclModel;
        }else{
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect( ad, jcl, issueStatusList,testType,programType,systemOperation);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("All didn't be selected");
            return DefectListAndAdJclModel;
        }

    }


}
