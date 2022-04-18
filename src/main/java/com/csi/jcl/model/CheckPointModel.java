package com.csi.jcl.model;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/10/13
 */
public class CheckPointModel {
    private String sprintno;
    private String ad;
    private String addesc;
    private String jcl;
    private String dd;
    private String owner;

    public String getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = jcl;
    }

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

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CheckPointModel() {
    }

    public CheckPointModel(String sprintno, String ad, String addesc, String jcl, String dd, String owner) {
        this.sprintno = sprintno;
        this.ad = ad;
        this.addesc = addesc;
        this.jcl = jcl;
        this.dd = dd;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "CheckPointModel{" +
                "sprintno='" + sprintno + '\'' +
                ", ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", jcl='" + jcl + '\'' +
                ", dd='" + dd + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
