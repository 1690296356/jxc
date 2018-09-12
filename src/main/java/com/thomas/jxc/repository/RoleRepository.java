package com.thomas.jxc.repository;

import com.thomas.jxc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/5 14:10
 * @描述 角色Repository接口
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
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
     * 根据用户id查角色集合
     * @param id 用户id
     * @return     菜单列表
     */
    @Query(value = "SELECT r.* FROM t_user u,t_role r,t_user_role ur WHERE ur.`user_id`=u.`id` AND ur.`role_id`=r.`id` AND u.`id`=?1",nativeQuery = true)
    public List<Role> findByUserId(Integer id);


    /**
     * 根据角色名查找角色实体
     * @param roleName 角色名称
     * @return role 角色实体
     */
    @Query(value = "select * from t_role where name=?1",nativeQuery = true)
    public Role findByRoleByRoleName(String roleName);
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
