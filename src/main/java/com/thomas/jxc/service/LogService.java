package com.thomas.jxc.service;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.entity.Role;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:25
 * @描述 角色Service接口
 */
@SuppressWarnings("unused")
public interface LogService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 添加或者修改日志信息
     * @param pLog 日志信息
     */
    void save(Log pLog);

    /**
     * 根据条件分页查询日志信息
     * @param log  log
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<Log> list(Log log, Integer page, Integer pageSize, Sort.Direction direction, String... properties);

    /**
     * 获取总记录数
     * @param log log
     * @return
     */
    Long getCount(Log log);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
