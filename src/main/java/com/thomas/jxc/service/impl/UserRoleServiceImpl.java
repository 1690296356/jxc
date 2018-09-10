package com.thomas.jxc.service.impl;

import com.thomas.jxc.repository.UserRoleRepository;
import com.thomas.jxc.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/10 18:43
 * @描述 TODO
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private UserRoleRepository mUserRoleRepository;
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

    @Override
    public void deleteByUserId(Integer userId) {
        mUserRoleRepository.deleteByUserId(userId);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
