package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.User;
import com.thomas.jxc.repository.UserRepository;
import com.thomas.jxc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:19
 * @描述 TODO
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private UserRepository mUserRepository;

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
    public User findByUserName(String pUserName) {
        return mUserRepository.findByUserName(pUserName);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
