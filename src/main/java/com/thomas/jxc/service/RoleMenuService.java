package com.thomas.jxc.service;

import com.thomas.jxc.entity.RoleMenu;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/11 17:53
 * @描述 角色菜单关联service接口
 */
@SuppressWarnings("unused")
public interface RoleMenuService {

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
     * 根据角色id删除所有关联信息
     * @param roleId 角色ID
     */
    public void deleteByRoleId(Integer roleId);


    /**
     * 保存实体
     * @param roleMenu 角色菜单实体
     */
    public void save(RoleMenu roleMenu);
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}