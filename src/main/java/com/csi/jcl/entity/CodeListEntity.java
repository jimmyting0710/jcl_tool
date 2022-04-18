package com.csi.jcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 對應資料庫code_list table
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */


@Entity
@Table(name="code_list")
public class CodeListEntity {

        @Id
        @Column(name="code_id")
        private String codeId;

        @Column(name="code_type")
        private String codeType;

        @Column(name="code_desc")
        private String codeDesc;

        @Column(name="code_enabled")
        private String codeEnabled;

        @Column(name="code_type_id")
        private String codeTypeId;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeEnabled() {
        return codeEnabled;
    }

    public void setCodeEnabled(String codeEnabled) {
        this.codeEnabled = codeEnabled;
    }

    public String getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(String codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public CodeListEntity(String codeId, String codeType, String codeDesc, String codeEnabled, String codeTypeId) {
        this.codeId = codeId;
        this.codeType = codeType;
        this.codeDesc = codeDesc;
        this.codeEnabled = codeEnabled;
        this.codeTypeId = codeTypeId;
    }

    public CodeListEntity(){

    }

    @Override
    public String toString() {
        return "CodeListEntity{" +
                "codeId='" + codeId + '\'' +
                ", codeType='" + codeType + '\'' +
                ", codeDesc='" + codeDesc + '\'' +
                ", codeEnabled='" + codeEnabled + '\'' +
                ", codeTypeId='" + codeTypeId + '\'' +
                '}';
    }
}
