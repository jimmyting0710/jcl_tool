package com.csi.jcl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.csi.jcl.entity.TestCase;
import com.csi.jcl.service.ThisService;


@Repository
public interface FindRepository extends PagingAndSortingRepository<TestCase,String>{

	@Query(value="select ad,sprint,JCL,JCLDESC,tid from TESTCASE C where C.AD=?1 and C.SPRINT=?2 ",nativeQuery=true)
	List<Map<ThisService, Object>>  findbyadsprint(String ad, String sprint);

	@Query(value="select ad,sprint,JCL,JCLDESC,tid from TESTCASE C where C.AD=?1 ",nativeQuery=true)
	List<Map<ThisService,Object>> findbyad(String ad);

	@Query(value="select code_id from code_list where code_type_id=?1 and code_enabled='Y'",nativeQuery=true)
	List<Map<ThisService, Object>> findbytester(String tester);

	@Query(value="select code_desc,code_id from code_list where code_type_id=?1 " ,nativeQuery=true)
	List<Map<ThisService, Object>> findresult(String result);

	//用ad jcl sprint找
	@Query(value="select ad,sprint,JCL,JCLDESC,tid from TESTCASE C where C.AD=?1 and C.SPRINT=?2 and C.JCL=?3",nativeQuery=true)
	List<Map<ThisService, Object>> findbyadsprintjcl(String ad, String sprint, String jcl);
	
	//用ad jcl 找 無sprint
	@Query(value="select ad,sprint,JCL,JCLDESC,tid from TESTCASE C where C.AD=?1 and C.JCL=?2 ",nativeQuery=true)
	List<Map<ThisService, Object>> findbyadjcl(String ad, String jcl);

	//批次
	@Query(value="select ad,tid  from testcase where ad=?1 and sprint=?2",nativeQuery=true)
	List<Map<ThisService, Object>> findbatch(String ad, String sprint);

	@Query(value="select tid from testcase where ad=?1 " ,nativeQuery=true)
	List<String> findbyadresult(String ad);

	@Query(value="select * from test_results where tid=?1",nativeQuery=true)
	List<Map<ThisService, Object>> findbytestresult(String tid);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "	from testcase\r\n"
			+ "left join test_results on testcase.tid = test_results.tid\r\n"
			+ "left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ " left join defect_list on ad_jcl.jid=defect_list.jid",nativeQuery=true)
	List<Map<ThisService, Object>> findinner();

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "	from testcase\r\n"
			+ "left join test_results on testcase.tid = test_results.tid\r\n"
			+ "left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ " left join defect_list on ad_jcl.jid=defect_list.jid",nativeQuery=true)
	List<Map<ThisService, Object>> findleftjoin(String ad, String sprint, String jcl);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "		from testcase \r\n"
			+ "	left join test_results on testcase.tid = test_results.tid \r\n"
			+ "    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ "    left join defect_list on ad_jcl.jid=defect_list.jid\r\n"
			+ "    where  testcase.ad=?1 ORDER by testcase.tid",nativeQuery=true)
	List<Map<ThisService, Object>> findleftjoinbyad(String ad);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "		from testcase \r\n"
			+ "	left join test_results on testcase.tid = test_results.tid \r\n"
			+ "    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ "    left join defect_list on ad_jcl.jid=defect_list.jid\r\n"
			+ "    where  testcase.ad=?1 and testcase.jcl=?2 ORDER by testcase.tid" ,nativeQuery=true)
	List<Map<ThisService, Object>> findbyleftjoinadjcl(String ad, String jcl);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "		from testcase \r\n"
			+ "	left join test_results on testcase.tid = test_results.tid \r\n"
			+ "    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ "    left join defect_list on ad_jcl.jid=defect_list.jid where  testcase.ad=?1 and testcase.sprint=?2  ORDER by testcase.tid",nativeQuery=true)
	List<Map<ThisService, Object>> findbyleftjoinadsprint(String ad, String sprint);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime,test_results.tester_id ,ad_jcl.jid,defect_list.issue_key\r\n"
			+ "		from testcase \r\n"
			+ "	left join test_results on testcase.tid = test_results.tid \r\n"
			+ "    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
			+ "    left join defect_list on ad_jcl.jid=defect_list.jid\r\n"
			+ "    where testcase.ad=?1 and  testcase.sprint=?2 and  testcase.jcl=?3 ORDER by testcase.tid",nativeQuery=true)
	List<Map<ThisService, Object>> findbyleftjoinadsprintjcl(String ad, String sprint, String jcl);

	@Query(value="select jid,listagg(issue_key,',') WITHIN group (order by issue_key) AS issue_key from defect_list group by jid",nativeQuery=true)
	List<Map<String, String>> findissue();
//	ad='JCVGET' and jcl='CVCHKOFF'
	
	@Query(value=" select defect_list.issue_key,defect_list.jid,defect_list.issue_type,defect_list.issue_status,code_list.code_desc from defect_list\r\n"
			+ " left join code_list on defect_list.issue_status=code_list.code_id\r\n"
			+ " where defect_list.jid=?1 " ,nativeQuery=true)
	List<Map<String, String>> finddetail(String jid);

	@Query(value="select ad,sprint,JCL,JCLDESC,tid from testcase where ad=?1",nativeQuery=true)
	String findonead(String ad);

	@Query(value="select TESTCASE.SYSTEMTYPE,testcase.testcase,testcase.sprint, TESTCASE.AD, TESTCASE.JCL, TESTCASE_Detail.STEP_NAME, TESTCASE_Detail.JCLPROGRAM,\r\n"
			+ "TESTCASE_Detail.DD,TESTCASE_Detail.DSN, TESTCASE_Detail.OPEN_MODE, TESTCASE_DetaiL.DISP \r\n"
			+ "from TESTCASE\r\n"
			+ "left join TESTCASE_Detail \r\n"
			+ " on TESTCASE.AD=TESTCASE_Detail.AD and \r\n"
			+ " TESTCASE.jcl=TESTCASE_Detail.jcl \r\n"
			+ " where testcase.tid=?1 ",nativeQuery=true)
	List<Map<String, String>> findjclkeyin(String tid);

	@Query(value="select * from testcase_db_detail where jcl=?1",nativeQuery=true)
	List<Map<String, String>> finddbims(String jcl1);

	@Query(value="select * from testcase_db_detail where jcl=?1",nativeQuery=true)
	String finddbjcl(String jcl1);

	@Query(value="select jcl,COUNT(pause) from checkpoint where pause=?2 and ad=?1 GROUP by jcl ",nativeQuery=true)
	List<Map<String, String>> findbreakpoint(String ad,String y);

	@Query(value="SELECT JCL,count(passform),count(IOCHECKLIST),count(ALLJCL) FROM checkpoint WHERE AD = ?1 AND \r\n"
			+ "(PASSFORM=?2 OR IOCHECKLIST=?2 OR ALLJCL=?2) GROUP by jcl",nativeQuery=true)
	List<Map<String, String>> findcheckpoint(String ad, String y);

	@Query(value="select para from testcase where tid=?1",nativeQuery=true)
	Map<String, String> findpara(String tid);

	// report controller
	@Query(value="Select systemtype,count(JCL) AS C\r\n"
			+ "from testcase where PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by systemtype\r\n"
			,nativeQuery=true)
	List<Map<String, String>> findtestcase();

	// report controller
	@Query(value="Select systemtype,count(JCL) AS C\r\n"
			+ "from testcase \r\n"
			+ "where status='P' and PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by systemtype",nativeQuery=true)
	List<Map<String, String>> findtestcaseOK();

	// report controller
	@Query(value="Select systemtype,count(JCL) AS C\r\n"
			+ "from testcase \r\n"
			+ "where status='F' and PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by systemtype",nativeQuery=true)
	List<Map<String, String>> findtestcaseFail();

	// report controller
	@Query(value="Select systemtype,count(JCL) AS C\r\n"
			+ "from testcase \r\n"
			+ "where (status='N' or status='T' or status is null) and PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by systemtype",nativeQuery=true)
	List<Map<String, String>> findtestcaseUndone();
	 
	// report controller
	@Query(value="select testcase.systemtype,count(defect_list.ISSUE_KEY) AS C\r\n"
			+ "from defect_list \r\n"
			+ "inner join AD_JCL ON AD_JCL.JID = Defect_List.JID \r\n"
			+ "inner join TESTCASE ON TESTCASE.AD = AD_JCL.AD AND TESTCASE.JCL = AD_JCL.JCL\r\n"
			+ "where defect_list.issue_status !='CLOSE' and PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by testcase.systemtype",nativeQuery=true)
	List<Map<String, String>> findDefectUndone();
	
	// report controller
	@Query(value="select testcase.systemtype,count(defect_list.ISSUE_KEY) AS C\r\n"
			+ "from defect_list \r\n"
			+ "inner join AD_JCL ON AD_JCL.JID = Defect_List.JID \r\n"
			+ "inner join TESTCASE ON TESTCASE.AD = AD_JCL.AD AND TESTCASE.JCL = AD_JCL.JCL\r\n"
			+ "where defect_list.issue_status ='CLOSE' and PROGRAM_TYPE ='BATCH'\r\n"
			+ "Group by testcase.systemtype",nativeQuery=true)
	List<Map<String, String>> findDefectDone();

	@Query(value="select * from code_list where code_type_id='TEST_TYPE'",nativeQuery=true)
	List<Map<String, String>> findtest_type();

	@Query(value="select * from code_list where code_type_id='SYSTEM_OPERATION'",nativeQuery=true)
	List<Map<String, String>> findsystem_operation();

	
	
	
	
	
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.test_type=?3 and testcase.program_type=?4 and testcase.system_operation=?5 \r\n"
			+ "                 and (testcase.ad=?1 or testcase.ad like '%?1%') and testcase.jcl=?2 \r\n"
			+ "                 ORDER by testcase.tid   ",nativeQuery=true)
	List<Map<String, String>> findbatch(String ad, String jcl, String test_type, String program_type,
			String system_operation);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.jcl=?1 and testcase.test_type=?2 and testcase.program_type=?3 and testcase.system_operation=?4 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findonline(String jcl, String test_type, String program_type,
			String online_operation);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.ad=?1 and testcase.jcl=?2  and testcase.program_type=?3 and testcase.system_operation=?4 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findallbatch(String ad, String jcl, String program_type, String system_operation);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.ad=?1 and testcase.jcl=?2  and testcase.program_type=?3  \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findallbatchallall(String ad, String jcl, String program_type);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.test_type=?3 and testcase.program_type=?4  \r\n"
			+ "                 and testcase.ad=?1 and testcase.jcl=?2 \r\n"
			+ "                 ORDER by testcase.tid   ",nativeQuery=true)
	List<Map<String, String>> findallbatchnaall(String ad, String jcl, String test_type, String program_type);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.jcl=?1  and testcase.program_type=?3 and testcase.system_operation=?3 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findonlineall(String jcl, String program_type, String online_operation);
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.ad=?1   and testcase.program_type=?2  \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findallbatchallallnoJCL(String ad, String program_type);

	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.ad=?1  and testcase.program_type=?2 and testcase.system_operation=?3 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findallbatchnoJCL(String ad, String program_type, String system_operation);
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.test_type=?2 and testcase.program_type=?3  \r\n"
			+ "                 and testcase.ad=?1 \r\n"
			+ "                 ORDER by testcase.tid   ",nativeQuery=true)
	List<Map<String, String>> findallbatchnaallnoJCL(String ad, String test_type, String program_type);
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.test_type=?2 and testcase.program_type=?3 and testcase.system_operation=?4 \r\n"
			+ "                 and testcase.ad=?1  \r\n"
			+ "                 ORDER by testcase.tid   ",nativeQuery=true)
	List<Map<String, String>> findbatchnoJCL(String ad, String test_type, String program_type, String system_operation);
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.program_type=?1 and testcase.system_operation=?2 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findonlineallnoJCL(String program_type, String online_operation);
	@Query(value="select testcase.ad,testcase.sprint,testcase.jcl,testcase.jcldesc,testcase.tid,test_results.status,test_results.rdatetime\r\n"
			+ ",test_results.tester_id ,ad_jcl.jid\r\n"
			+ "					from testcase \r\n"
			+ "				left join test_results on testcase.tid = test_results.tid \r\n"
			+ "			    left join ad_jcl on  testcase.ad=ad_jcl.ad and testcase.jcl=ad_jcl.jcl\r\n"
	
			+ "			     where testcase.test_type=?1 and testcase.program_type=?2 and testcase.system_operation=?3 \r\n"
			+ "                 ORDER by testcase.tid  ",nativeQuery=true)
	List<Map<String, String>> findonlinenoJCL(String test_type, String program_type, String online_operation);

	
	@Query(value="select ad,JCL,JCLDESC,tid from TESTCASE C where C.AD=?1 ",nativeQuery=true)
	List<Map<String, String>> findbytoad(String ad);

	@Query(value="select tid from testcase where ad=?1 and jcl=?2",nativeQuery=true)
	String findtid(String adname, String jcl);

	@Query(value="select code_id from code_list where code_id=?1 ",nativeQuery=true)
	String findtester_id(String tester);

	@Query(value="select code_id from code_list where code_desc=?1",nativeQuery=true)
	String findstatus(String status);

	
	


	
}
