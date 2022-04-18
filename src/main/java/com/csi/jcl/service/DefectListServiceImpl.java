package com.csi.jcl.service;

import com.csi.jcl.dao.DefectListRepository;
import com.csi.jcl.entity.DefectListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 實作DefectListService功能
 *
 * @author si1255   Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */



@Service
public class DefectListServiceImpl implements DefectListService{
    @Autowired
    private DefectListRepository defectListRepository;

    @Override
    public List<DefectListEntity> getAllDefectList() {

        return null;
    }
}
