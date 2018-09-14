package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.User;
import com.thomas.jxc.repository.UserRepository;
import com.thomas.jxc.service.UserService;
import com.thomas.jxc.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

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

    @Override
    public List<User> list(User pUser, Integer pPage, Integer pPageSize, Sort.Direction pDirection, String... pProperties) {
        Pageable pageable = new PageRequest(pPage-1, pPageSize);
        Page<User> pageUser = mUserRepository.findAll((root, query, cb) -> {
           Predicate predicate = cb.conjunction();
           if(pUser != null){
                if(StringUtil.isNotEmpty(pUser.getUserName())){
                    predicate.getExpressions().add(cb.like(root.get("userName"),"%"+pUser.getUserName().trim()+"%"));
                }

               predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
           }
            return predicate;
        },pageable);
        return pageUser.getContent();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public Long getCount(User pUser) {
        Long count = mUserRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(pUser != null){
                if(StringUtil.isNotEmpty(pUser.getUserName())){
                    predicate.getExpressions().add(cb.like(root.get("userName"),"%"+pUser.getUserName().trim()+"%"));
                }
                predicate.getExpressions().add(cb.notEqual(root.get("id"),1));
            }
            return predicate;
        });
        return count;
    }

    @Override
    public void save(User pUser) {
        mUserRepository.save(pUser);
    }

    @Override
    public void delete(Integer id) {
        mUserRepository.delete(id);
    }

    @Override
    public User findByUserId(Integer id) {
        return mUserRepository.findOne(id);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
