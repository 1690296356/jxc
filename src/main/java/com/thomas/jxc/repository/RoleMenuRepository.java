package com.thomas.jxc.repository;

import com.thomas.jxc.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/11 17:41
 * @描述 TODO
 */
public interface RoleMenuRepository  extends JpaRepository<RoleMenu, Integer>,JpaSpecificationExecutor<RoleMenu>{

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
    @SuppressWarnings({"UnnecessaryInterfaceModifier", "unused", "SqlDialectInspection", "SqlNoDataSourceInspection"})
    @Query(value = "delete from t_role_menu where role_id=?1",nativeQuery = true)
    @Modifying
    public void deleteByRoleId(Integer roleId);
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}