package com.thomas.jxc.repository;

import com.thomas.jxc.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/10 18:40
 * @描述 TODO
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

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
     * 根据用户id删除所有关联信息
     * @param userId 用户ID
     */
    @Query(value = "delete from t_user_role where user_id=?1",nativeQuery = true)
    @Modifying
    public void deleteByUserId(Integer userId);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}