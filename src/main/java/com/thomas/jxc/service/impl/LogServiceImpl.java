package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Log;
import com.thomas.jxc.repository.LogRepository;
import com.thomas.jxc.repository.UserRepository;
import com.thomas.jxc.service.LogService;
import com.thomas.jxc.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:19
 * @描述 TODO
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private LogRepository mLogRepository;

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
    public void save(Log pLog) {
        pLog.setTime(new Date());//设置操作日期
        pLog.setUser(mUserRepository.findByUserName((String) SecurityUtils.getSubject().getPrincipal()));//设置当前用户
        mLogRepository.save(pLog);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<Log> list(Log log, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        Pageable pageable = new PageRequest(page-1, pageSize);
        Page<Log> pageLog = mLogRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(log != null){
                if(StringUtil.isNotEmpty(log.getType())){
                    predicate.getExpressions().add(cb.equal(root.get("type"),log.getType()));
                }
                if(log.getUser() != null && StringUtil.isNotEmpty(log.getUser().getTrueName())){
                    predicate.getExpressions().add(cb.like(root.get("user").get("trueName"), "%"+log.getUser().getTrueName().trim()+"%"));
                }
                if(log.getbTime() !=null){
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("time"), log.getbTime()));
                }
                if(log.geteTime() !=null){
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("time"), log.geteTime()));
                }
            }
            return predicate;
        },pageable);
        return pageLog.getContent();
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "Duplicates"})
    @Override
    public Long getCount(Log log) {
        Long count = mLogRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(log != null){
                if(StringUtil.isNotEmpty(log.getType())){
                    predicate.getExpressions().add(cb.equal(root.get("type"),log.getType()));
                }
                if(log.getUser() != null && StringUtil.isNotEmpty(log.getUser().getTrueName())){
                    predicate.getExpressions().add(cb.like(root.get("user").get("trueName"), "%"+log.getUser().getTrueName()+"%"));
                }
                if(log.getbTime() !=null){
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("time"), log.getbTime()));
                }
                if(log.geteTime() !=null){
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("time"), log.geteTime()));
                }
            }
            return predicate;
        });
        return count;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
