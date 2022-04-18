package com.csi.jcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="testcase_db_detail")
public class DbEntity {
	private String AD;
	@Id

	private String JCL;
	private String DB2_DELETE;
	private String DB2_INCLUDE;
	private String DB2_INSERT;
	private String DB2_SELECT;
	private String DB2_UPDATE;
	private String IMS_DELETE;
	private String IMS_GET;
	private String IMS_INSERT;
	private String IMS_UPDATE;
	
	public String getAD() {
		return AD;
	}
	public void setAD(String aD) {
		AD = aD;
	}
	public String getJCL() {
		return JCL;
	}
	public void setJCL(String jCL) {
		JCL = jCL;
	}
	public String getDB2_DELETE() {
		return DB2_DELETE;
	}
	public void setDB2_DELETE(String dB2_DELETE) {
		DB2_DELETE = dB2_DELETE;
	}
	public String getDB2_INCLUDE() {
		return DB2_INCLUDE;
	}
	public void setDB2_INCLUDE(String dB2_INCLUDE) {
		DB2_INCLUDE = dB2_INCLUDE;
	}
	public String getDB2_INSERT() {
		return DB2_INSERT;
	}
	public void setDB2_INSERT(String dB2_INSERT) {
		DB2_INSERT = dB2_INSERT;
	}
	public String getDB2_SELECT() {
		return DB2_SELECT;
	}
	public void setDB2_SELECT(String dB2_SELECT) {
		DB2_SELECT = dB2_SELECT;
	}
	public String getDB2_UPDATE() {
		return DB2_UPDATE;
	}
	public void setDB2_UPDATE(String dB2_UPDATE) {
		DB2_UPDATE = dB2_UPDATE;
	}
	public String getIMS_DELETE() {
		return IMS_DELETE;
	}
	public void setIMS_DELETE(String iMS_DELETE) {
		IMS_DELETE = iMS_DELETE;
	}
	public String getIMS_GET() {
		return IMS_GET;
	}
	public void setIMS_GET(String iMS_GET) {
		IMS_GET = iMS_GET;
	}
	public String getIMS_INSERT() {
		return IMS_INSERT;
	}
	public void setIMS_INSERT(String iMS_INSERT) {
		IMS_INSERT = iMS_INSERT;
	}
	public String getIMS_UPDATE() {
		return IMS_UPDATE;
	}
	public void setIMS_UPDATE(String iMS_UPDATE) {
		IMS_UPDATE = iMS_UPDATE;
	}
	
	
}
