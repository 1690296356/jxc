package com.thomas.jxc.repository;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/3 16:40
 * @描述 TODO
 */
public interface LogRepository extends JpaRepository<Log, Integer>, JpaSpecificationExecutor<Log> {
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


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
