package com.csi.jcl.model;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/10/13
 */
public class CheckPointJoinTestCaseModel {

    private String sprintno;
    private String ad;
    private String addesc;
    private String cpdd;
    private String tsdd;
    private String cht_ap;
    private String cht_dc;
    private String open_mode;
    private String dsn;
    private String system;
    private String systemtype;
    private String sprint;
    private String jcl;
    private String passform;
    private String iochecklist;
    private String alljcl;

    public String getSprintno() {
        return sprintno;
    }

    public void setSprintno(String sprintno) {
        this.sprintno = sprintno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc;
    }

    public String getCpdd() {
        return cpdd;
    }

    public void setCpdd(String cpdd) {
        this.cpdd = cpdd;
    }

    public String getTsdd() {
        return tsdd;
    }

    public void setTsdd(String tsdd) {
        this.tsdd = tsdd;
    }

    public String getCht_ap() {
        return cht_ap;
    }

    public void setCht_ap(String cht_ap) {
        this.cht_ap = cht_ap;
    }

    public String getCht_dc() {
        return cht_dc;
    }

    public void setCht_dc(String cht_dc) {
        this.cht_dc = cht_dc;
    }

    public String getOpen_mode() {
        return open_mode;
    }

    public void setOpen_mode(String open_mode) {
        this.open_mode = open_mode;
    }

    public String getDsn() {
        return dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = jcl;
    }

    public String getPassform() {
        return passform;
    }

    public void setPassform(String passform) {
        this.passform = passform;
    }

    public String getIochecklist() {
        return iochecklist;
    }

    public void setIochecklist(String iochecklist) {
        this.iochecklist = iochecklist;
    }

    public String getAlljcl() {
        return alljcl;
    }

    public void setAlljcl(String alljcl) {
        this.alljcl = alljcl;
    }

    public CheckPointJoinTestCaseModel() {
    }

    public CheckPointJoinTestCaseModel(String sprintno, String ad, String addesc, String cpdd, String tsdd, String cht_ap, String cht_dc, String open_mode, String dsn, String system, String systemtype, String sprint, String jcl, String passform, String iochecklist, String alljcl) {
        this.sprintno = sprintno;
        this.ad = ad;
        this.addesc = addesc;
        this.cpdd = cpdd;
        this.tsdd = tsdd;
        this.cht_ap = cht_ap;
        this.cht_dc = cht_dc;
        this.open_mode = open_mode;
        this.dsn = dsn;
        this.system = system;
        this.systemtype = systemtype;
        this.sprint = sprint;
        this.jcl = jcl;
        this.passform = passform;
        this.iochecklist = iochecklist;
        this.alljcl = alljcl;
    }

    @Override
    public String toString() {
        return "CheckPointJoinTestCaseModel{" +
                "sprintno='" + sprintno + '\'' +
                ", ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", cpdd='" + cpdd + '\'' +
                ", tsdd='" + tsdd + '\'' +
                ", cht_ap='" + cht_ap + '\'' +
                ", cht_dc='" + cht_dc + '\'' +
                ", open_mode='" + open_mode + '\'' +
                ", dsn='" + dsn + '\'' +
                ", system='" + system + '\'' +
                ", systemtype='" + systemtype + '\'' +
                ", sprint='" + sprint + '\'' +
                ", jcl='" + jcl + '\'' +
                ", passform='" + passform + '\'' +
                ", iochecklist='" + iochecklist + '\'' +
                ", alljcl='" + alljcl + '\'' +
                '}';
    }
}
