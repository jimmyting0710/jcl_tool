package com.csi.jcl.dao;

import com.csi.jcl.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfoRepository 繼承JpaRepository對資料庫進行數據持久化操作
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,String> {
}
