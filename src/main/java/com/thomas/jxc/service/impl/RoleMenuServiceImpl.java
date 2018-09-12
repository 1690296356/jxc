package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.RoleMenu;
import com.thomas.jxc.repository.RoleMenuRepository;
import com.thomas.jxc.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/11 18:00
 * @描述 TODO
 */
@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private RoleMenuRepository mRoleMenuRepository;
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
    public void deleteByRoleId(Integer roleId) {
        mRoleMenuRepository.deleteByRoleId(roleId);
    }

    @Override
    public void save(RoleMenu roleMenu) {
        mRoleMenuRepository.save(roleMenu);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
