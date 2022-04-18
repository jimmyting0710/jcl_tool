package com.csi.jcl.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csi.jcl.entity.TestCase;


@Repository
public interface SaveTestCase extends CrudRepository<TestCase, String> {
	@Transactional
	@Modifying
	@Query(value="update testcase set status=?2 ,tester_id=?3 where tid=?1",nativeQuery = true)
	void updatetestcase(String tid, String status, String tester_id);

	@Transactional
	@Modifying
	@Query(value="update testcase set para=?1 where tid=?2",nativeQuery = true)
	void savepara(String para,String tid);
}
