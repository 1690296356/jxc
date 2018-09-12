package com.thomas.jxc.service;

import com.thomas.jxc.entity.Role;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:25
 * @描述 角色Service接口
 */
@SuppressWarnings("unused")
public interface RoleService {
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
     * 根据用户ID查角色集合
     * @param id 用户ID
     * @return roleList 角色集合
     */
    @SuppressWarnings("unused")
    List<Role> findByUserId(Integer id);

    /**
     * 根据用户ID查找角色实体
     * @param id 用户ID
     * @return 角色实体
     */
    Role findById(Integer id);


    /**
     * 查询所有角色信息
     * @return 角色信息结果集
     */
    List<Role> listAll();


    /**
     * 根据条件分页查询角色信息
     * @param role  role
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<Role> list(Role role, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取总记录数
     * @param role role
     * @return long 总记录数
     */
    Long getCount(Role role);


    /**
     * 添加或者修改角色信息
     * @param pRole 用户信息
     */
    void save(Role pRole);

    /**
     * 根据id删除角色
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据角色名查找角色实体
     * @param roleName 角色名称
     * @return role 角色实体
     */
    Role findByRoleByRoleName(String roleName);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
