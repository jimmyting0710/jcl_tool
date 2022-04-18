package com.csi.jcl.model;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/10/01
 */
public class AdJclExeorderModel {

    private String ad;

    private String addesc;

    private String jcl;

    private String jclesc;

    private String beforead;

    private String beforejcl;

    private String afterjcl;

    private String beforeJclAd;

    private String beforeWorkStation;

    private String selfWorkStation;

    private String afterWorkStation;

    private String jid;

    public AdJclExeorderModel() {
    }

    public AdJclExeorderModel(String ad, String addesc, String jcl, String jclesc, String beforead, String beforejcl, String afterjcl, String beforeJclAd, String beforeWorkStation, String selfWorkStation, String afterWorkStation, String jid) {
        this.ad = ad;
        this.addesc = addesc;
        this.jcl = jcl;
        this.jclesc = jclesc;
        this.beforead = beforead;
        this.beforejcl = beforejcl;
        this.afterjcl = afterjcl;
        this.beforeJclAd = beforeJclAd;
        this.beforeWorkStation = beforeWorkStation;
        this.selfWorkStation = selfWorkStation;
        this.afterWorkStation = afterWorkStation;
        this.jid = jid;
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

    public String getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = jcl;
    }

    public String getJclesc() {
        return jclesc;
    }

    public void setJclesc(String jclesc) {
        this.jclesc = jclesc;
    }

    public String getBeforead() {
        return beforead;
    }

    public void setBeforead(String beforead) {
        this.beforead = beforead;
    }

    public String getBeforejcl() {
        return beforejcl;
    }

    public void setBeforejcl(String beforejcl) {
        this.beforejcl = beforejcl;
    }

    public String getAfterjcl() {
        return afterjcl;
    }

    public void setAfterjcl(String afterjcl) {
        this.afterjcl = afterjcl;
    }

    public String getBeforeJclAd() {
        return beforeJclAd;
    }

    public void setBeforeJclAd(String beforeJclAd) {
        this.beforeJclAd = beforeJclAd;
    }

    public String getBeforeWorkStation() {
        return beforeWorkStation;
    }

    public void setBeforeWorkStation(String beforeWorkStation) {
        this.beforeWorkStation = beforeWorkStation;
    }

    public String getSelfWorkStation() {
        return selfWorkStation;
    }

    public void setSelfWorkStation(String selfWorkStation) {
        this.selfWorkStation = selfWorkStation;
    }

    public String getAfterWorkStation() {
        return afterWorkStation;
    }

    public void setAfterWorkStation(String afterWorkStation) {
        this.afterWorkStation = afterWorkStation;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    @Override
    public String toString() {
        return "AdJclExeorderModel{" +
                "ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", jcl='" + jcl + '\'' +
                ", jclesc='" + jclesc + '\'' +
                ", beforead='" + beforead + '\'' +
                ", beforejcl='" + beforejcl + '\'' +
                ", afterjcl='" + afterjcl + '\'' +
                ", beforeJclAd='" + beforeJclAd + '\'' +
                ", beforeWorkStation='" + beforeWorkStation + '\'' +
                ", selfWorkStation='" + selfWorkStation + '\'' +
                ", afterWorkStation='" + afterWorkStation + '\'' +
                ", jid='" + jid + '\'' +
                '}';
    }
}
