package com.thomas.jxc.entity;

import javax.persistence.*;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/18 14:48
 * @描述 商品单位实体
 */
@Entity
@Table(name = "t_goodsunit")
public class GoodsUnit {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 10)
    private String name;//商品单位名称


    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoodsUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
