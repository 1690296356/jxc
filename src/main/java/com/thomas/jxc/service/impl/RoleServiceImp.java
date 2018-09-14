package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Role;
import com.thomas.jxc.repository.RoleRepository;
import com.thomas.jxc.service.RoleService;
import com.thomas.jxc.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:28
 * @描述 角色ServiceImpl
 */
@Service("roleService")
@Transactional
public class RoleServiceImp implements RoleService {
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

    @Override
    public List<Role> listAll() {
        return mRoleRepository.findAll();
    }


    @Override
    public List<Role> list(Role pRole, Integer pPage, Integer pPageSize, Sort.Direction pDirection, String... pProperties) {
        Pageable pageable = new PageRequest(pPage-1, pPageSize);
        Page<Role> pageRole = mRoleRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(pRole != null){
                if(StringUtil.isNotEmpty(pRole.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+pRole.getName().trim()+"%"));
                }

                predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
            }
            return predicate;
        },pageable);
        return pageRole.getContent();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public Long getCount(Role pRole) {
        Long count = mRoleRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(pRole != null){
                if(StringUtil.isNotEmpty(pRole.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+pRole.getName().trim()+"%"));
                }
                predicate.getExpressions().add(cb.notEqual(root.get("id"),1));
            }
            return predicate;
        });
        return count;
    }

    @Override
    public void save(Role pRole) {
        mRoleRepository.save(pRole);
    }

    @Override
    public void delete(Integer id) {
        mRoleRepository.delete(id);
    }

    @Override
    public Role findByRoleByRoleName(String roleName) {
        return mRoleRepository.findByRoleByRoleName(roleName);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
