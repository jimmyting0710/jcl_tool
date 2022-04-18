package com.csi.jcl.service;

import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.entity.UserInfoEntity;
import com.csi.jcl.model.AdJclModel;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * 定義JclService介面
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
public interface JclService {

    public List<AdJclEntity> findJclByAd(String adName);

    public List<AdJclModel> listAllJclByCondition(String testType,String systemOp,String adName);

    public List<UserInfoEntity> findAll();

    public ByteArrayInputStream generateExcel(List<AdJclModel> listAllJclByCondition);

    public List<AdJclModel> sortData(List<AdJclModel> adJclModelList);
}
