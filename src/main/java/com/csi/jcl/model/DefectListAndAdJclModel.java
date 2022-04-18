package com.csi.jcl.model;


import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */




public class DefectListAndAdJclModel extends ActionSupport {
    @Override
    public String toString() {
        return "DefectListAndAdJclModel{" +
                "testType='" + testType + '\'' +
                ", ad='" + ad + '\'' +
                ", jcl='" + jcl + '\'' +
                ", issueType='" + issueType + '\'' +
                ", issueStatusList=" + issueStatusList +
                ", issueStatus='" + issueStatus + '\'' +
                ", issueKey='" + issueKey + '\'' +
                ", codeId='" + codeId + '\'' +
                ", codeDesc='" + codeDesc + '\'' +
                ", codeTypeId='" + codeTypeId + '\'' +
                '}';
    }

    private String testType;
    private String ad;
    private String jcl;
    private String issueType;
    private List<String> issueStatusList;

    public DefectListAndAdJclModel(String testType, String ad, String jcl, String issueType, List<String> issueStatusList, String issueStatus, String issueKey, String codeId, String codeDesc, String codeTypeId) {
        this.testType = testType;
        this.ad = ad;
        this.jcl = jcl;
        this.issueType = issueType;
        this.issueStatusList = issueStatusList;
        this.issueStatus = issueStatus;
        this.issueKey = issueKey;
        this.codeId = codeId;
        this.codeDesc = codeDesc;
        this.codeTypeId = codeTypeId;
    }

    private String issueStatus;

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }


    public List<String> getIssueStatusList() {
        return issueStatusList;
    }

    public void setIssueStatusList(List<String> issueStatusList) {
        this.issueStatusList = issueStatusList;
    }


    private String issueKey;


    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(String codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    private String codeId;
    private String codeDesc;
    private String codeTypeId;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getJcl() {
        return jcl;
    }

    public void setJcl(String jcl) {
        this.jcl = jcl;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }


    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public DefectListAndAdJclModel() {

    }

    public DefectListAndAdJclModel(String testType, String ad, String jcl, String issueType, List issueStatusList, String issueKey, String codeId, String codeDesc, String codeTypeId) {
        this.testType = testType;
        this.ad = ad;
        this.jcl = jcl;
        this.issueType = issueType;
        this.issueStatusList = issueStatusList;
        this.issueKey = issueKey;
        this.codeId = codeId;
        this.codeDesc = codeDesc;
        this.codeTypeId = codeTypeId;
    }

}
