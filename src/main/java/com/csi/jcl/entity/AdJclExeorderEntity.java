package com.csi.jcl.entity;
import javax.persistence.*;

/**
 * 對應資料庫AD_JCL_EXEORDER table
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
@Entity
@Table(name = "AD_JCL_EXEORDER", schema = "REHOST", catalog = "")
//@IdClass(AdJclExeorderKey.class)
public class AdJclExeorderEntity {

    @Id
    @Column(name = "AD")
    private String ad;

    @Column(name = "ADDESC")
    private String addesc;

//    @Id
    @Column(name = "JCL")
    private String jcl;

    @Column(name = "JCLESC")
    private String jclesc;

    @Column(name = "BEFOREAD")
    private String beforead;

    @Column(name = "BEFOREJCL")
    private String beforejcl;

    @Column(name = "AFTERJCL")
    private String afterjcl;

    @Column(name = "BEFORE_JCL_AD")
    private String beforeJclAd;

    @Column(name = "BEFORE_WORK_STATION")
    private String beforeWorkStation;

    @Column(name = "SELF_WORK_STATION")
    private String selfWorkStation;

    @Column(name = "AFTER_WORK_STATION")
    private String afterWorkStation;

//    @Id
    @Column(name = "JID")
    private String jid;


    public AdJclExeorderEntity() {
    }

    public AdJclExeorderEntity(String ad, String addesc, String jcl, String jclesc, String beforead, String beforejcl, String afterjcl, String beforeJclAd, String beforeWorkStation, String selfWorkStation, String afterWorkStation, String jid) {
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
        return "AdJclExeorderEntity{" +
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
