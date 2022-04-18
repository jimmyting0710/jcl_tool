package com.csi.jcl.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AdJclExeroderRepository 注入NamedParameterJdbcTemplate對資料庫做JDBC操作
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Repository
public class AdJclExeroderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AdJclExeroderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 依原生sql操作資料庫
     *
     * @param sql 原生sql
     * @param param param參數的值
     * @return namedParameterJdbcTemplate.queryForList(sql, param)
     * @author si1206 Sam Chen
     * @date 2021/10/13
     */
    public List<Map<String, Object>> query(String sql, Map<String, ?> param) {
        return namedParameterJdbcTemplate.queryForList(sql, param);
    }

}
