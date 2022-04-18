package com.csi.jcl.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csi.jcl.entity.TestResults;



@Repository

public interface SaveRepository extends CrudRepository<TestResults, String> {

	@Query(value = "merge into test_results t using dual on (t.tid= ?2) when not matched then insert ( status,  tid,tester_id,rdatetime,rid) values( ?1,?2,?3,?4,rid_seq.nextval)  when matched then update set status=?1,tester_id=?3,rdatetime=?4  where tid= ?2 ", nativeQuery = true)
	void saveresult(String status, String tid, String tester_id, String rdatetime, String rid);

	@Query(value = "select rid_seq.nextval from DUAL", nativeQuery = true)
	Long getseq();

	TestResults findByTID(String tid);

	@Query(value="select rdatetime from test_results where tid=?1", nativeQuery = true)
	String findtime(String tid);


	@Transactional
	@Modifying
	@Query(value="merge into test_results t using dual on (t.tid= ?2) when not matched then insert ( status,  tid,tester_id,rdatetime,rid) values( ?1,?2,?3,?4,rid_seq.nextval)  when matched then update set status=?1,tester_id=?3  where tid= ?2 ",nativeQuery = true)
	Object updatetemp(String status, String tid,  String tester_id, String rdatetime);
	
	
}
