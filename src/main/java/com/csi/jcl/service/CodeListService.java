package com.csi.jcl.service;

import com.csi.jcl.entity.CodeListEntity;

/**
 * 定義CodeListService介面
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */


import java.util.List;

public interface CodeListService {
    List<CodeListEntity> getAllCodeList();
    List<CodeListEntity> findByCodeTypeIdEqualsSystemType();
    List<CodeListEntity> findByCodeTypeIdSystemOperation();
    List<CodeListEntity> selectTestType();
    List<CodeListEntity> findSystemOperation();
}
