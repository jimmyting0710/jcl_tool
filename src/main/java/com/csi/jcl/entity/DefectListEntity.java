package com.csi.jcl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 對應資料庫defect_list table
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */



@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="defect_list")
public class DefectListEntity {

    @Column(name="test_type")
    private String testType;

    @Id
    @Column(name="issue_key")
    private String issueKey;



    @Column(name="jid")
    private String jid;

    @Column(name="issue_type")
    private String issueType;


    @Column(name="issue_status")
    private String issueStatus;

    @Column(name="issue_create_datetime")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ssAM")
//    @DateTimeFormat(pattern = "yyyy-MM-dd aHH:mm:ss")
    private Date issueCreateDatetime;





    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issue_key) {
        this.issueKey = issue_key;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issue_type) {
        this.issueType = issue_type;
    }

    public Date getIssueCreateDatetime() {
        return issueCreateDatetime;
    }

    public void setIssueCreateDatetime(Date issue_create_datetime) {
        this.issueCreateDatetime = issue_create_datetime;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String test_type) {
        this.testType = test_type;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }


    public DefectListEntity(String testType, String issueKey, String jid, String issueType, String issueStatus, Date issueCreateDatetime) {
        this.testType = testType;
        this.issueKey = issueKey;
        this.jid = jid;
        this.issueType = issueType;
        this.issueStatus = issueStatus;
        this.issueCreateDatetime = issueCreateDatetime;
    }

    public DefectListEntity() {
    }
}
