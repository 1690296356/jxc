package com.thomas.jxc.service;

import com.thomas.jxc.entity.GoodsUnit;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:16
 * @描述 商品单位Service接口
 */
public interface GoodsUnitService {

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
     * 查询所有商品单位信息
     * @return list list
     */
    List<GoodsUnit> listAll();

    /**
     * 添加或者修改商品单位信息
     * @param goodsUnit 商品单位
     */
    void save(GoodsUnit goodsUnit);

    /**
     * 根据id删除商品单位信息
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据id查询实体
     * @param id id
     * @return GoodsUnit goodsUnit
     */
    GoodsUnit findById(Integer id);
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}