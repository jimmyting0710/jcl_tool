package com.csi.jcl.service;

import com.csi.jcl.model.DefectListAndAdJclModel;


import java.util.List;
import java.util.Map;


/**
 * 定義ListAllDefectService介面
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */

public interface ListAllDefectService {

    public List<DefectListAndAdJclModel> listAllDefect( String ad, String jcl, List<String> issueStatusList,String testType,String programType,String systemOperation);


}
