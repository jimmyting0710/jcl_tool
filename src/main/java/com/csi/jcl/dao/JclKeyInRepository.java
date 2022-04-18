package com.csi.jcl.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csi.jcl.entity.DbEntity;
@Repository
public interface JclKeyInRepository extends CrudRepository<DbEntity, String>{

}
