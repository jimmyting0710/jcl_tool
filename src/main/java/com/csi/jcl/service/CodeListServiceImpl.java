package com.csi.jcl.service;

import com.csi.jcl.dao.CodeListRepository;
import com.csi.jcl.entity.CodeListEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 實作CodeListService功能
 *
 * @author si1255   Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */

@Service
public class CodeListServiceImpl implements CodeListService {

    private static final Logger logger =
            LogManager.getLogger(CodeListServiceImpl.class);

    @Autowired
    private CodeListRepository codeListRepository;


    @Override
    public List<CodeListEntity> getAllCodeList() {
        logger.info(codeListRepository.findAll());
        return codeListRepository.findAll();
    }

    @Override
    public List<CodeListEntity> findByCodeTypeIdEqualsSystemType() {
        return  codeListRepository.findByCodeTypeIdEqualsSystemType();
    }

    @Override
    public List<CodeListEntity> findByCodeTypeIdSystemOperation() {
        return codeListRepository.findByCodeTypeIdSystemOperation();
    }


    @Override
    public List<CodeListEntity>  selectTestType() {
        List<CodeListEntity> selectTestType= codeListRepository.findSeletTestType();
       return selectTestType;
    }

    @Override
    public List<CodeListEntity> findSystemOperation() {
         List<CodeListEntity> selectSystemOperation= codeListRepository.findByCodeTypeIdSystemOperation();;
        return selectSystemOperation;
    }

}
