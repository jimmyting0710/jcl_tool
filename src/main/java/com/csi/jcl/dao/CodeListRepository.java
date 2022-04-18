package com.csi.jcl.dao;

import com.csi.jcl.entity.CodeListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeListRepository extends JpaRepository<CodeListEntity,String>, JpaSpecificationExecutor<CodeListEntity> {

    /**
     * 依條件查詢code_list列表
     *
     * @param codeDesc codeDesc參數的值
     * @author si1255
     * @date 2021/10/25
     */


    @Query(nativeQuery = true,value="select * from code_list where code_type_id = 'JIRA_STATUS' and code_desc = ?1")
    CodeListEntity findByCodeDesc(String codeDesc);

    /**
     * 依條件查詢code_list列表
     *
     *
     * @author si1206
     * @date 2022/01/19
     */
    @Query(nativeQuery = true, value ="select * from code_list where code_type_id ='SYSTEM_TYPE'")
    public List<CodeListEntity> findByCodeTypeIdEqualsSystemType();

    /**
     * 依條件查詢code_list列表
     *
     *
     * @author si1206
     * @date 2022/10/19
     */
    @Query(nativeQuery = true, value ="select * from code_list where code_type_id ='SYSTEM_OPERATION'")
    public List<CodeListEntity> findByCodeTypeIdSystemOperation();


    @Query(nativeQuery = true, value ="select * from code_list where code_type_id='TEST_TYPE'")
    public List<CodeListEntity>  findSeletTestType();

}
