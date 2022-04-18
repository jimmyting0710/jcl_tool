package com.csi.jcl.service;

import com.csi.jcl.entity.DefectListEntity;


import java.util.List;


/**
 * 定義DefectListService介面
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */
public interface DefectListService {
    List<DefectListEntity> getAllDefectList();

}
