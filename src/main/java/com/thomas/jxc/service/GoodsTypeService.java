package com.thomas.jxc.service;

import com.thomas.jxc.entity.GoodsType;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 19:21
 * @描述 商品类别Service接口
 */
@SuppressWarnings("unused")
public interface GoodsTypeService {

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
     * 根据父节点查找所有子节点
     * @param parentId  parentId
     * @return list list
     */
    List<GoodsType> findByParentId(int parentId);

    /**
     * 添加或者修改商品类别信息
     * @param goodsType 商品类别信息
     */
    void save(GoodsType goodsType);

    /**
     * 根据id删除商品类别信息
     * @param id id
     */
    void delete(Integer id);

    /**
     * 根据id查询商品类别信息
     * @param id id
     * @return GoodsType goodsType
     */
    GoodsType findById(Integer id);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}