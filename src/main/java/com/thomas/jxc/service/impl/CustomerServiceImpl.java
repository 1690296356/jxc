package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Customer;
import com.thomas.jxc.repository.CustomerRepository;
import com.thomas.jxc.service.CustomerService;
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
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private CustomerRepository mCustomerRepository;

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
    public List<Customer> list(Customer pCustomer, Integer pPage, Integer pPageSize, Sort.Direction pDirection, String... pProperties) {
        Pageable pageable = new PageRequest(pPage-1, pPageSize);
        Page<Customer> pageCustomer = mCustomerRepository.findAll((root, query, cb) -> {
           Predicate predicate = cb.conjunction();
           if(pCustomer != null){
                if(StringUtil.isNotEmpty(pCustomer.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+pCustomer.getName().trim()+"%"));
                }

           }
            return predicate;
        },pageable);
        return pageCustomer.getContent();
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "Duplicates"})
    @Override
    public Long getCount(Customer pCustomer) {
        Long count = mCustomerRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(pCustomer != null){
                if(StringUtil.isNotEmpty(pCustomer.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+pCustomer.getName()+"%"));
                }
            }
            return predicate;
        });
        return count;
    }

    @Override
    public void save(Customer pCustomer) {
        mCustomerRepository.save(pCustomer);
    }

    @Override
    public void delete(Integer id) {
        mCustomerRepository.delete(id);
    }

    @Override
    public Customer findById(Integer id) {
        return mCustomerRepository.findOne(id);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
