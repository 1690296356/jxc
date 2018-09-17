package com.thomas.jxc.repository;

import com.thomas.jxc.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 18:14
 * @描述 商品Repository接口
 */
public interface GoodsRepository extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {

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
    @SuppressWarnings({"UnnecessaryInterfaceModifier", "unused", "SqlDialectInspection"})
    /**
     * 查询某个商品类别下的所有商品
     * @param typeId 类别Id
     * @return goodsList 商品集合
     */
    @Query(value = "select * from t_goods where type_id=?1",nativeQuery = true)
    public List<Goods> findByTypeId(int typeId);


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}