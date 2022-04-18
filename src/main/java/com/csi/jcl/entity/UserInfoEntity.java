package com.csi.jcl.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 對應資料庫TESTUSERINFO table
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Entity
@Table(name = "TESTUSERINFO", schema = "REHOST", catalog = "")
public class UserInfoEntity {
    private String userid;
    private String userpw;
    private Boolean enabled;
    private String lastlogindatetime;

//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "AUTHORITIES_USERID")
//    private AuthoritiesEntity authoritiesEntity;

//    public AuthoritiesEntity getAuthoritiesEntity() {
//        return authoritiesEntity;
//    }
//
//    public void setAuthoritiesEntity(AuthoritiesEntity authoritiesEntity) {
//        this.authoritiesEntity = authoritiesEntity;
//    }

    @Id
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "USERPW")
    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    @Basic
    @Column(name = "ENABLED")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "LASTLOGINDATETIME")
    public String getLastlogindatetime() {
        return lastlogindatetime;
    }

    public void setLastlogindatetime(String lastlogindatetime) {
        this.lastlogindatetime = lastlogindatetime;
    }

    public UserInfoEntity() {
    }

    public UserInfoEntity(String userid, String userpw, Boolean enabled, String lastlogindatetime) {
        this.userid = userid;
        this.userpw = userpw;
        this.enabled = enabled;
        this.lastlogindatetime = lastlogindatetime;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                ", enabled=" + enabled +
                ", lastlogindatetime=" + lastlogindatetime +
                '}';
    }
}
