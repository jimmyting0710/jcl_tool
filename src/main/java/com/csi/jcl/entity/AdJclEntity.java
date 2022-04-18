package com.csi.jcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 對應資料庫AD_JCL table
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */

@Entity
@Table(name = "AD_JCL", schema = "REHOST", catalog = "")
public class AdJclEntity {

    @Column(name = "SPRINT")
    private String sprint;

    @Column(name = "AD")
    private String ad;

    @Column(name = "ADDESC")
    private String addesc;


    @Column(name = "JCL")
    private String jcl;

    @Column(name = "JCLDESC")
    private String jcldesc;

    @Id
    @Column(name = "JID")
    private String jid;

    @Column(name = "SYSTEMTYPE")
    private String systemtype;

    @Column(name = "SYSTEMDESC")
    private String systemdesc;

    @Column(name = "CHT")
    private String cht;

    @Column(name = "SYSTEM_OPERATION")
    private String systemOp;

    public AdJclEntity() {
    }

    public AdJclEntity(String sprint, String ad, String addesc, String jcl, String jcldesc, String jid, String systemtype, String systemdesc, String cht, String systemOp) {
        this.sprint = sprint;
        this.ad = ad;
        this.addesc = addesc;
        this.jcl = jcl;
        this.jcldesc = jcldesc;
        this.jid = jid;
        this.systemtype = systemtype;
        this.systemdesc = systemdesc;
        this.cht = cht;
        this.systemOp = systemOp;
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

    public String getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = jcl;
    }

    public String getJcldesc() {
        return jcldesc;
    }

    public void setJcldesc(String jcldesc) {
        this.jcldesc = jcldesc;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
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

    public String getSystemOp() {
        return systemOp;
    }

    public void setSystemOp(String systemOp) {
        this.systemOp = systemOp;
    }

    @Override
    public String toString() {
        return "AdJclEntity{" +
                "sprint='" + sprint + '\'' +
                ", ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", jcl='" + jcl + '\'' +
                ", jcldesc='" + jcldesc + '\'' +
                ", jid='" + jid + '\'' +
                ", systemtype='" + systemtype + '\'' +
                ", systemdesc='" + systemdesc + '\'' +
                ", cht='" + cht + '\'' +
                ", systemOp='" + systemOp + '\'' +
                '}';
    }
}
