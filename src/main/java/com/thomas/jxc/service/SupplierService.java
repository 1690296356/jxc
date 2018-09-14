package com.thomas.jxc.service;

import com.thomas.jxc.entity.Supplier;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 19:21
 * @描述 TODO
 */
public interface SupplierService {

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
     * 根据条件分页查询供应商信息
     * @param supplier  supplier
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<Supplier> list(Supplier supplier, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取总记录数
     * @param supplier supplier
     * @return long 总记录数
     */
    Long getCount(Supplier supplier);


    /**
     * 添加或者修改供应商信息
     * @param supplier 供应商信息
     */
    void save(Supplier supplier);

    /**
     * 根据id删除供应商
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据id查询供应商
     * @param id id
     * @return Supplier supplier
     */
    Supplier findById(Integer id);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}