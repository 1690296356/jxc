package com.thomas.jxc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/12 15:06
 * @描述 日志实体
 */
@Entity
@Table(name = "t_log")
public class Log {
    // ===========================================================
    // Constants
    // ===========================================================
    public final static String LOGIN_ACTION="登录操作";
    public final static String LOGOUT_ACTION="注销操作";
    public final static String SEARCH_ACTION="查询操作";
    public final static String UPDATE_ACTION="更新操作";
    public final static String ADD_ACTION="添加操作";
    public final static String DELETE_ACTION="删除操作";

    // ===========================================================
    // Fields
    // ===========================================================
    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 100)
    private String type;//日志类型

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;//操作用户

    @Column(length = 100)
    private String content;//操作内容

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Transient
    private Date bTime; //起始时间 搜索用到

    @Transient
    private Date eTime;//结束时间 搜索用到

    // ===========================================================
    // Constructors
    // ===========================================================

    public Log() {
    }

    public Log(String type, String content) {
        this.type = type;
        this.content = content;
    }

// ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
