package com.thomas.jxc.service;

import com.thomas.jxc.entity.Menu;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/7 11:22
 * @描述 权限菜单业务层
 */
public interface MenuService {

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
     * 根据父节点以及用户角色id查询子节点
     * @param parentId 父节点ID
     * @param  roleId 用户角色ID
     * @return menuList 菜单结果集
     */
    List<Menu> findByParentIDAndRoleID(int parentId, int roleId);


    /**
     * 根据id 获取菜单实体
     * @param id 菜单ID
     * @return menu 菜单实体
     */
    public Menu findById(Integer id);

    /**
     * 根据角色id获取菜单集合
     * @param roleId 角色Id
     * @return menuList 菜单集合
     */
    public List<Menu> findByRoleId(int roleId);


    /**
     * 根据父节点查找所有子节点
     * @param parentId 父节点Id
     * @return menuList 菜单集合
     */
    public List<Menu> findByParentId(int parentId);



    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}