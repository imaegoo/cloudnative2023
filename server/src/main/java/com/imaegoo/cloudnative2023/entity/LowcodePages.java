package com.imaegoo.cloudnative2023.entity;

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
    private int createdUser;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private int isDeleted;

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

    public int getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(int createdUser) {
        this.createdUser = createdUser;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getIsdeleted() {
        return isDeleted;
    }

    public void setIsdeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
