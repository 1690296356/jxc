package com.thomas.jxc.repository;

import com.thomas.jxc.entity.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 18:14
 * @描述 商品类别Repository接口
 */
public interface GoodsTypeRepository extends JpaRepository<GoodsType, Integer>{

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
    @Query(value = "select * from t_goodstype where p_id=?1",nativeQuery = true)
    public List<GoodsType> findByParentId(int parentId);


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}