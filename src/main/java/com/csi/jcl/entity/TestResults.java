package com.csi.jcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TEST_RESULTS")
public class TestResults {
	@Id
	@Column
	private long RID;
	
	@Column
	private String TID;
	@Column
	private String STATUS;
	@Column
	private String RDATETIME;
	@Column
	private String TESTER_ID;
	
	public TestResults() {
		super();
	}
	
	public TestResults(String tID, long rID, String sTATUS, String rDATETIME, String tESTER_ID) {
		super();
		TID = tID;
		RID = rID;
		STATUS = sTATUS;
		RDATETIME = rDATETIME;
		TESTER_ID = tESTER_ID;
	}
	public long getRID() {
		return RID;
	}
	public void setRID(long rID) {
		RID = rID;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getRDATETIME() {
		return RDATETIME;
	}
	public void setRDATETIME(String rDATETIME) {
		RDATETIME = rDATETIME;
	}
	public String getTESTER_ID() {
		return TESTER_ID;
	}
	public void setTESTER_ID(String tESTER_ID) {
		TESTER_ID = tESTER_ID;
	}
	
}
