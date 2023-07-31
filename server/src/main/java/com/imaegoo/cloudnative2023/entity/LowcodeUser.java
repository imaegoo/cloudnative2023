package com.imaegoo.cloudnative2023.entity;

import java.sql.Timestamp;

/**
 * 项目名称：springboot
 * 类 名 称：LowcodeUserService
 * 类 描 述：TODO
 * 创建时间：2023/7/28 上午10:35
 * 创 建 人：wteng
 */

public class LowcodeUser {
    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private int isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
