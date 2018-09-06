package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Role;
import com.thomas.jxc.repository.RoleRepository;
import com.thomas.jxc.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:28
 * @描述 角色ServiceImpl
 */
@Service("roleService")
public class RoleServiceImplmpl implements RoleService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private RoleRepository mRoleRepository;
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
    public List<Role> findByUserId(Integer id) {
        return mRoleRepository.findByUserId(id);
    }

    @Override
    public Role findById(Integer id) {
        return mRoleRepository.findOne(id);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
