package com.csi.jcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 對應資料庫AUTHORITIES table
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Entity
@Table(name = "AUTHORITIES")
public class AuthoritiesEntity {

    @Id
    @Column(name = "USERID")
    private String userid;

    @Column(name = "AUTHORITY")
    private String authority;

    public AuthoritiesEntity() {
    }

    public AuthoritiesEntity(String userid, String authority) {
        this.userid = userid;
        this.authority = authority;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthoritiesEntity{" +
                "userid='" + userid + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
