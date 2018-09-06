package com.thomas.jxc.repository;

import com.thomas.jxc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 16:40
 * @描述 TODO
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
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
    @Query(value = "select * from t_user where user_name =?1",nativeQuery =true)
    public User findByUserName(String userName);


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
