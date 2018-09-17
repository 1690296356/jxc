package com.thomas.jxc.service;

import com.thomas.jxc.entity.Goods;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/17 14:55
 * @描述 商品Service接口
 */
public interface GoodsService {
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
     * 查询某个类别下面的所有商品
     * @param typeId 商品类别
     * @return goodsList 商品信息集合
     */
    List<Goods> findByTypeId(int typeId);


    /**
     * 根据条件分页查询商品信息
     * @param goods  goods
     * @param page page
     * @param pageSize pageSize
     * @param direction direction
     * @param properties properties
     * @return list list
     */
    List<Goods> list(Goods goods, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取总记录数
     * @param goods goods
     * @return long 总记录数
     */
    Long getCount(Goods goods);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
