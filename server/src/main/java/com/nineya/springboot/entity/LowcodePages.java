package com.nineya.springboot.entity;

import java.sql.Blob;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodePagesMapper
 * 类 描 述：TODO
 * 创建时间：2023/7/25 下午2:52
 * 创 建 人：wteng
 */

public class LowcodePages {
    private int id;
    private String pageId;
    private String title;
    private String content;
    private String createdUser;
    private Timestamp createdTime;
    private Timestamp updateTime;
    private int isdeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }
}
