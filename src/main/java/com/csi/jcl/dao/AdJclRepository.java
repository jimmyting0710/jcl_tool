package com.csi.jcl.dao;

import com.csi.jcl.entity.AdJclEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * JclRepository 繼承PagingAndSortingRepository對資料庫進行數據持久化操作
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
@Repository
public interface AdJclRepository extends PagingAndSortingRepository<AdJclEntity, String> {


    /**
     * 依adName查詢相關的JCL
     *
     * @param adName adName參數的值
     * @author si1206
     * @date 2021/08/04
     */
    public List<AdJclEntity> findByAd(String adName);

//    /**
//     * 依條件查詢JCL列表
//     *
//     * @param sprint sprint參數的值
//     * @param adName adName參數的值
//     * @author si1206
//     * @date 2021/08/04
//     */
//    @Query(nativeQuery = true, value = "SELECT DISTINCT A1.sprint,A1.AD ,A1.ADDESC,A1.SYSTEMTYPE,A1.SYSTEMDESC,A1.CHT ,CAD.CHT_AP,CAD.CHT_DC ,(Select count(JCL) from AD_JCL WHERE AD=A1.AD) AS JCLCOUT " +
//            "from AD_JCL  A1 " +
//            "inner join CHT_AP_DC CAD ON CAD.SPRINT=A1.SPRINT AND CAD.SYSTEMTYPE=A1.SYSTEMTYPE " +
//            "WHERE A1.AD NOT IN (Select AD FROM ad_jcl_exeorder WHERE BEFOREAD IS NOT NULL) " +
//            "and ( :adName is null OR A1.AD = :adName)" +
//            "and ( :sprint is null OR A1.SPRINT = :sprint )" +
//            "and ( :codeTypeId is null OR A1.SYSTEMTYPE = :codeTypeId)" +
//            "Order by A1.Sprint,A1.SYSTEMTYPE,A1.AD")
//    public List<Map<String, Object>> listAllJclByCondition(@Param("adName") String adName, @Param("sprint") String sprint,@Param("codeTypeId") String codeTypeId);


    /**
     * 依條件查詢JCL列表
     *
     * @param testType testType參數的值
     * @param systemOp systemOp參數的值
     * @param adName   adName參數的值
     * @return
     * @author si1206
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT A1.SYSTEM_OPERATION,A1.AD ,A1.ADDESC,A1.CHT " +
            ",(Select count(JCL) from AD_JCL WHERE AD=A1.AD) AS JCLCOUT " +
            "from AD_JCL  A1 " +
            "INNER JOIN TESTCASE T ON T.AD=A1.AD AND T.JCL= A1.JCL " +
            "WHERE A1.AD NOT IN (Select AD FROM ad_jcl_exeorder WHERE BEFOREAD IS NOT NULL) " +
            "AND (:testType is null OR T.TEST_TYPE = :testType) " +
            "AND (:systemOp is null OR A1.SYSTEM_OPERATION = :systemOp) " +
            "AND ( :adName is null OR A1.AD Like %:adName% ) " +
            "Order by  A1.SYSTEM_OPERATION,A1.AD ")
    public List<Map<String, Object>> listAllJclByCondition(@Param("testType") String testType,
                                                           @Param("systemOp") String systemOp,
                                                           @Param("adName") String adName);


    /**
     * 依條件查詢JCL列表
     *
     * @author si1255
     * @date 2021/10/22
     */

    @Query(nativeQuery = true, value = "select * from ad_jcl where ad = ?1 and jcl = ?2")
    AdJclEntity findByAdAndJcl(String ad, String jcl);


}
