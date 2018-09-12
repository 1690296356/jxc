package com.thomas.jxc.service;

import com.thomas.jxc.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:16
 * @描述 用户Service接口
 */
public interface UserService {

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
     * 根据用户名查找用户实体
     * @param pUserName 用户名
     * @return User 用户实体
     */
    public User findByUserName(String pUserName);


    /**
     * 根据条件分页查询用户信息
     * @param user  user
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取总记录数
     * @param user user
     * @return long 总记录数
     */
    Long getCount(User user);


    /**
     * 添加或者修改用户信息
     * @param pUser 用户信息
     */
    void save(User pUser);

    /**
     * 根据id删除用户
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据id查询用户
     * @param id id
     * @return User user
     */
    User findByUserId(Integer id);
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}