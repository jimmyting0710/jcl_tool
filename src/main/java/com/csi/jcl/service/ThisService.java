package com.csi.jcl.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csi.jcl.dao.*;
import com.csi.jcl.entity.DbEntity;
import com.csi.jcl.entity.TestCase;
import com.csi.jcl.entity.TestResults;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class ThisService {

	@Autowired
	FindRepository findRepository;
	@Autowired
	SaveRepository saveRepository;
	@Autowired
	SaveTestCase savetestcase;
	@Autowired
	JclKeyInRepository savekeyin;

	public List<Map<ThisService, Object>> findbyad(String ad) {
		return findRepository.findbyad(ad);
	}

	public List<Map<ThisService, Object>> findbyadsprint(String ad, String sprint, String jcl) {
		if (sprint.equals("ALL")) {
			if (jcl == "" || jcl.equals(null)) {
				return findRepository.findbyad(ad);
			} else {
				return findRepository.findbyadjcl(ad, jcl);
			}
		} else {
			if (jcl == "" || jcl.equals(null)) {
				return findRepository.findbyadsprint(ad, sprint);
			} else {
				return findRepository.findbyadsprintjcl(ad, sprint, jcl);
			}
		}

	}

	public List<Map<ThisService, Object>> findbycodetypeid(String tester) {

		return findRepository.findbytester(tester);
	}

	public List<Map<ThisService, Object>> findbyresult(String result) {

		return findRepository.findresult(result);

	}

//	public String updateresult(String tid, String selecttester, String selectresult,String timeStamp) {
//		SaveRepository.updateresult(tid,selecttester,selectresult,timeStamp);
//		return "ok";
//	}

//	public String save(TestResults entity) {
////		System.out.println(entity.getTID());
//		if (entity.getSTATUS().equals("N") || entity.getSTATUS().equals("T")) {
//			entity.setRDATETIME("");
//			entity.setSTATUS(entity.getSTATUS());
//			entity.setTID(entity.getTID());
//			entity.setTESTER_ID(entity.getTESTER_ID());
//			saveRepository.save(entity);
//		} else {
//			entity.setRDATETIME(entity.getRDATETIME());
//			entity.setSTATUS(entity.getSTATUS());
//			entity.setTID(entity.getTID());
//			entity.setTESTER_ID(entity.getTESTER_ID());
//			saveRepository.save(entity);
//		}
//		return "ok";
//	}

	public List<Map<ThisService, Object>> findbyadsprintjcl(String ad, String sprint, String jcl) {

		return findRepository.findbyadsprintjcl(ad, sprint, jcl);
	}

	public Long getseq() {
		return saveRepository.getseq();
	}

	public String saveresult(String status, String tid, String tester_id, String rdatetime, String rid) {

		if (status.equals("N") || status.equals("T")) {
			TestResults data = new TestResults();
			data = saveRepository.findByTID(tid);
			if (data != null) {
				data.setRDATETIME("");
				data.setSTATUS(status);
				data.setTESTER_ID(tester_id);
				saveRepository.save(data);

			} else {
				data = new TestResults();
				data.setRID(getseq());
				data.setRDATETIME("");
				data.setSTATUS(status);
				data.setTESTER_ID(tester_id);
				data.setTID(tid);
				saveRepository.save(data);
			}
		} else {
			TestResults data = new TestResults();
			data = saveRepository.findByTID(tid);
			String time = saveRepository.findtime(tid);
			if (time == null) {
				time = rdatetime;
			}
			if (data != null) {
				data.setRDATETIME(time);
				data.setSTATUS(status);
				data.setTESTER_ID(tester_id);
				saveRepository.save(data);
			} else {
				data = new TestResults();
				data.setRID(getseq());
				data.setRDATETIME(rdatetime);
				data.setSTATUS(status);
				data.setTESTER_ID(tester_id);
				data.setTID(tid);
				saveRepository.save(data);
			}
		}

		return "ok";

	}

	public void updatetestcase(String tid, String status, String tester_id) {
		savetestcase.updatetestcase(tid, status, tester_id);

	}

//批次更新
	public List<Map<ThisService, Object>> findbatchad(String ad) {
		return findRepository.findbyad(ad);
	}

	public void savebatchresult(String status, String tester_id, String tid, String rdatetime, String rid, String ad) {

		List<String> adresult = findRepository.findbyadresult(ad);
		for (String alltid : adresult) {
			if (status.equals("N") || status.equals("T")) {
				TestResults data = new TestResults();
				data = saveRepository.findByTID(alltid);
				if (data != null) {
					data.setRDATETIME("");
					data.setSTATUS(status);
					data.setTESTER_ID(tester_id);
					saveRepository.save(data);

				} else {
					data = new TestResults();
					data.setRID(getseq());
					data.setRDATETIME("");
					data.setSTATUS(status);
					data.setTESTER_ID(tester_id);
					data.setTID(alltid);
					saveRepository.save(data);
				}
			} else {
				TestResults data = new TestResults();
				data = saveRepository.findByTID(alltid);
				if (data != null) {
					data.setRDATETIME(rdatetime);
					data.setSTATUS(status);
					data.setTESTER_ID(tester_id);
					saveRepository.save(data);
				} else {
					data = new TestResults();
					data.setRID(getseq());
					data.setRDATETIME(rdatetime);
					data.setSTATUS(status);
					data.setTESTER_ID(tester_id);
					data.setTID(alltid);
					saveRepository.save(data);
				}
			}
		}
	}

	public void updatebatch(String tid, String tester_id, String status, String ad) {
		List<String> adresult = findRepository.findbyadresult(ad);
		for (String alltid : adresult) {
			savetestcase.updatetestcase(alltid, status, tester_id);

		}

	}

	public List<Map<ThisService, Object>> findhavetime(String tid) {

		return findRepository.findbytestresult(tid);
	}

	public List<Map<ThisService, Object>> findinner() {
		return findRepository.findinner();
	}

	public List<Map<ThisService, Object>> findleftinner(String ad, String sprint, String jcl) {
		// TODO Auto-generated method stub
		if (sprint.equals("ALL")) {
			if (jcl == "" || jcl.equals(null)) {
				return findRepository.findleftjoinbyad(ad);
			} else {
				return findRepository.findbyleftjoinadjcl(ad, jcl);
			}
		} else {
			if (jcl == "" || jcl.equals(null)) {
				return findRepository.findbyleftjoinadsprint(ad, sprint);
			} else {
				return findRepository.findbyleftjoinadsprintjcl(ad, sprint, jcl);
			}
		}

//		return findRepository.findleftjoin(ad,sprint,jcl);
	}

	public List<Map<String, String>> findissue() {

		return findRepository.findissue();
	}

	public List<Map<String, String>> finddefectdetail(String jid) {
		return findRepository.finddetail(jid);
	}

	public String findbyonead(String ad) {
		return findRepository.findonead(ad);
	}

	public List<Map<String, String>> findjclkeyin(String tid) {
		return findRepository.findjclkeyin(tid);
	}

	public void savejclkeyin(String aD, String jCL, String dB2_DELETE, String dB2_INCLUDE, String dB2_INSERT,
			String dB2_SELECT, String dB2_UPDATE, String iMS_DELETE, String iMS_GET, String iMS_INSERT,
			String iMS_UPDATE) {

		DbEntity data = new DbEntity();
		data.setAD(aD);
		data.setJCL(jCL);
		data.setDB2_DELETE(dB2_DELETE);
		data.setDB2_INCLUDE(dB2_INCLUDE);
		data.setDB2_INSERT(dB2_INSERT);
		data.setDB2_SELECT(dB2_SELECT);
		data.setDB2_UPDATE(dB2_UPDATE);
		data.setIMS_DELETE(iMS_DELETE);
		data.setIMS_GET(iMS_GET);
		data.setIMS_INSERT(iMS_INSERT);
		data.setIMS_UPDATE(iMS_UPDATE);
		savekeyin.save(data);

	}

	public List<Map<String, String>> finddbims(String jcl1) {
		if (findRepository.finddbjcl(jcl1) != null) {
			return findRepository.finddbims(jcl1);
		} else {
			DbEntity data = new DbEntity();
			data.setJCL(jcl1);
			savekeyin.save(data);
			return findRepository.finddbims(jcl1);
		}
	}

	public List<Map<String, String>> findbreakpoint(String ad) {
		String y = "Y";
		return findRepository.findbreakpoint(ad, y);
	}

	public List<Map<String, String>> findcheckpoint(String ad) {
		String y = "Y";
		return findRepository.findcheckpoint(ad, y);
	}

	public void savepara(String para, String tid) {
		savetestcase.savepara(para, tid);
	}

	public Map<String, String> findpara(String tid) {
		return findRepository.findpara(tid);
	}

	// report controller
	public List<Map<String, String>> findtestcase() {
		return findRepository.findtestcase();
	}
	// report controller
	public List<Map<String, String>> findtestcaseOk() {
		return findRepository.findtestcaseOK();
	}
	// report controller
	public List<Map<String, String>> findtestcaseFail() {
		return findRepository.findtestcaseFail();
	}
	// report controller
	public List<Map<String, String>> findtestcaseUndone() {
		return findRepository.findtestcaseUndone();
	}
	// report controller
	public List<Map<String, String>> findDefectUndone() {
		return findRepository.findDefectUndone();
	}
	// report controller
	public List<Map<String, String>> findDefectDone() {
		return findRepository.findDefectDone();
	}
	
	public List<Map<String, String>> findtest_type() {
		// TODO Auto-generated method stub
		return findRepository.findtest_type();
	}

	public List<Map<String, String>> findsystem_operation() {
		// TODO Auto-generated method stub
		return findRepository.findsystem_operation();
	}

	public List<Map<String, String>> findbatchonline(String ad, String jcl, String test_type, String program_type,
			String system_operation, String online_operation) {
//		System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
		if (!jcl.isEmpty() ) {
			if ("BATCH".equals(program_type)) {
				// batch
				if ("ALL".equals(test_type)) {
					if ("ALL".equals(system_operation)) {
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						// batch test_typeall all
						return findRepository.findallbatchallall(ad, jcl, program_type);

					} else {
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						// batch test_typeall NA
						return findRepository.findallbatch(ad, jcl, program_type, system_operation);
					}
				} else {
					if ("ALL".equals(system_operation)) {
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						// batch test_typenotall system_operationall
						return findRepository.findallbatchnaall(ad, jcl, test_type, program_type);
					}
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					// batch test_typenotall system_operationNotall
					return findRepository.findbatch(ad, jcl, test_type, program_type, system_operation);
				}
			} else {

				// online All
				if ("ALL".equals(test_type)) {
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					return findRepository.findonlineall(jcl, program_type, online_operation);
				} else {
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					// online Not all
					return findRepository.findonline(jcl, test_type, program_type, online_operation);
				}
			}

		} else {
			
			//no JCL
			if ("BATCH".equals(program_type)) {
				// batch
				if ("ALL".equals(test_type)) {
					if ("ALL".equals(system_operation)) {
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						// batch test_typeall all
						return findRepository.findallbatchallallnoJCL(ad,  program_type);

					} else {
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						// batch test_typeall NA
						return findRepository.findallbatchnoJCL(ad,  program_type, system_operation);
					}
				} else {
					if ("ALL".equals(system_operation)) {
						// batch test_typenotall system_operationall
//						System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
						return findRepository.findallbatchnaallnoJCL(ad, test_type, program_type);
					}
					// batch test_typenotall system_operationNotall
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					return findRepository.findbatchnoJCL(ad, test_type, program_type, system_operation);
				}
			} else {

				// online All
				if ("ALL".equals(test_type)) {
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					return findRepository.findonlineallnoJCL( program_type, online_operation);
					
				} else {
					// online Not all
//					System.out.println(test_type+"===="+program_type+"===="+system_operation+"===="+online_operation+"===="+ad+"===="+jcl);
					return findRepository.findonlinenoJCL( test_type, program_type, online_operation);
					
				}
			}
		}
	}

	public void saveresulttemp(String status, String tid, String tester_id, String rdatetime ) {
		saveRepository.updatetemp(status,tid,tester_id,rdatetime);
		return ;
	}

	public List<Map<String, String>> findbatchforad(String ad) {
		
		return findRepository.findbytoad(ad);
	}

	public String findtid(String adname, String jcl) {
		return findRepository.findtid(adname,jcl);
	}

	public String findtester_id(String tester) {
		return findRepository.findtester_id(tester);
	}

	public String findstatus(String status) {
		return findRepository.findstatus(status);
	}

	public void saveresult2(String findstatus, String findtid, String findtester_id, String fintime, String rid) {
		if (findstatus.equals("N") || findstatus.equals("T")) {
			TestResults data = new TestResults();
			data = saveRepository.findByTID(findtid);
			if (data != null) {
				data.setRDATETIME("");
				data.setSTATUS(findstatus);
				data.setTESTER_ID(findtester_id);
				saveRepository.save(data);

			} else {
				data = new TestResults();
				data.setRID(getseq());
				data.setRDATETIME("");
				data.setSTATUS(findstatus);
				data.setTESTER_ID(findtester_id);
				data.setTID(findtid);
				saveRepository.save(data);
			}
		} else {
			TestResults data = new TestResults();
			data = saveRepository.findByTID(findtid);
//			String time = saveRepository.findtime(findtid);
			
			if (data != null) {
				data.setRDATETIME(fintime);
				data.setSTATUS(findstatus);
				data.setTESTER_ID(findtester_id);
				saveRepository.save(data);
			} else {
				data = new TestResults();
				data.setRID(getseq());
				data.setRDATETIME(fintime);
				data.setSTATUS(findstatus);
				data.setTESTER_ID(findtester_id);
				data.setTID(findtid);
				saveRepository.save(data);
			}
		}

		return;

	}


}