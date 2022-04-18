package com.csi.jcl.dao;

import com.csi.jcl.entity.DefectListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UploadExcelRepository extends PagingAndSortingRepository<DefectListEntity, String> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT JID from AD_JCL " +
            "WHERE AD = :ad " +
            "and JCL = :jcl ")
    public List<Map<String, Object>> listJid(@Param("ad") String ad, @Param("jcl") String jcl);

}
