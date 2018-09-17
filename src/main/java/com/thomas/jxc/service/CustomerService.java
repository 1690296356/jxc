package com.thomas.jxc.service;

import com.thomas.jxc.entity.Customer;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 19:21
 * @描述 客户Service接口
 */
@SuppressWarnings("unused")
public interface CustomerService {

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
    /**
         * 根据条件分页查询客户信息
     * @param customer  customer
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<Customer> list(Customer customer, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取总记录数
     * @param customer customer
     * @return long 总记录数
     */
    Long getCount(Customer customer);


    /**
     * 添加或者修改客户信息
     * @param customer 客户信息
     */
    void save(Customer customer);

    /**
     * 根据id删除客户
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据id查询客户
     * @param id id
     * @return Customer customer
     */
    Customer findById(Integer id);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}