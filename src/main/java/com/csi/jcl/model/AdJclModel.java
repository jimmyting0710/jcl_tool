package com.csi.jcl.model;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */

public class AdJclModel {

    private Integer index;

    private String sprint;

    private String ad;

    private String addesc;

    private String systemtype;

    private String systemdesc;

    private String cht;

    private Integer jclcout;

    private String jid;


    private String system_operation;

    public AdJclModel() {
    }

    public AdJclModel(Integer index, String sprint, String ad, String addesc, String systemtype, String systemdesc, String cht, Integer jclcout, String jid, String system_operation) {
        this.index = index;
        this.sprint = sprint;
        this.ad = ad;
        this.addesc = addesc;
        this.systemtype = systemtype;
        this.systemdesc = systemdesc;
        this.cht = cht;
        this.jclcout = jclcout;
        this.jid = jid;
        this.system_operation = system_operation;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
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

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }

    public String getSystemdesc() {
        return systemdesc;
    }

    public void setSystemdesc(String systemdesc) {
        this.systemdesc = systemdesc;
    }

    public String getCht() {
        return cht;
    }

    public void setCht(String cht) {
        this.cht = cht;
    }

    public Integer getJclcout() {
        return jclcout;
    }

    public void setJclcout(Integer jclcout) {
        this.jclcout = jclcout;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }


    public String getSystem_operation() {
        return system_operation;
    }

    public void setSystem_operation(String system_operation) {
        this.system_operation = system_operation;
    }

    @Override
    public String toString() {
        return "AdJclModel{" +
                "index=" + index +
                ", sprint='" + sprint + '\'' +
                ", ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", systemtype='" + systemtype + '\'' +
                ", systemdesc='" + systemdesc + '\'' +
                ", cht='" + cht + '\'' +
                ", jclcout=" + jclcout +
                ", jid='" + jid + '\'' +
                ", system_operation='" + system_operation + '\'' +
                '}';
    }
}
