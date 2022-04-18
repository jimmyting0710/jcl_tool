package com.csi.jcl.dao;

import com.csi.jcl.entity.DefectListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DefectListRepository extends PagingAndSortingRepository<DefectListEntity, String> {

    /**
     * 依條件查詢defectlist+ad_jcl+code_list列表
     *
     *
     * @param ad ad參數的值
     * @param jcl jcl參數的值
     * @param issueStatusList issueStatusList 參數的值
     * @author si1255
     * @date 2021/10/25
     */

    @Query(nativeQuery = true, value = "SELECT DISTINCT DL.TEST_TYPE,AJ.AD,AJ.JCL,DL.ISSUE_KEY,DL.ISSUE_TYPE,DL.ISSUE_STATUS,CL.CODE_ID,CL.CODE_DESC,CL.CODE_TYPE_ID " +
            "from DEFECT_LIST DL,AD_JCL AJ,CODE_LIST CL,TESTCASE TC " +
            "WHERE DL.JID=AJ.jid " +
            "and DL.ISSUE_STATUS=CL.CODE_ID " +
            "and ( :ad is null OR AJ.AD = :ad) " +
            "and ( :jcl is null OR AJ.JCL = :jcl) " +
            "and ( :testType is null OR DL.TEST_TYPE = :testType ) " +
            "and ( :systemOperation is null OR TC.SYSTEM_OPERATION = :systemOperation ) " +
            "and  DL.ISSUE_STATUS in :issueStatusList " +
            "and TC.AD = AJ.AD " +
            "and TC.JCL = AJ.JCL " +
            "and (TC.PROGRAM_TYPE =:programType)")
    public List<Map<String, Object>> listAllDefect(
            @Param("ad") String ad, @Param("jcl") String jcl, @Param("issueStatusList") List<String> issueStatusList,
            @Param("testType") String testType,@Param("programType") String programType,@Param("systemOperation")String systemOperation);


    /**
     * 依條件查詢defectlist+ad_jcl+code_list列表
     *
     *
     * @param ad ad參數的值
     * @param jcl jcl參數的值
     * @author si1255
     * @date 2021/10/25
     */



    @Query(nativeQuery = true, value = "SELECT DISTINCT DL.TEST_TYPE,AJ.AD,AJ.JCL,DL.ISSUE_KEY,DL.ISSUE_TYPE,DL.ISSUE_STATUS,CL.CODE_ID,CL.CODE_DESC,CL.CODE_TYPE_ID " +
            "from DEFECT_LIST DL,AD_JCL AJ,CODE_LIST CL,TESTCASE TC " +
            "WHERE DL.JID=AJ.jid " +
            "and DL.ISSUE_STATUS=CL.CODE_ID " +
            "and ( :ad is null OR AJ.AD = :ad) " +
            "and ( :jcl is null OR AJ.JCL = :jcl) " +
            "and ( :testType is null OR DL.TEST_TYPE = :testType ) " +
            "and ( :systemOperation is null OR TC.SYSTEM_OPERATION = :systemOperation ) " +
            "and TC.AD = AJ.AD " +
            "and TC.JCL = AJ.JCL " +
            "and (TC.PROGRAM_TYPE =:programType)")
    public List<Map<String, Object>> listAllDefect123(@Param("ad") String ad,
                                                      @Param("jcl") String jcl,@Param("testType")String testType,
                                                      @Param("programType") String programType,@Param("systemOperation")String systemOperation);

}
