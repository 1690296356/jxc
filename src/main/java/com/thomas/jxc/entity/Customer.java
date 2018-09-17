package com.thomas.jxc.entity;

import javax.persistence.*;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/14 11:36
 * @描述 客户实体
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "t_customer")
public class Customer {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 200)
    private String name;//客户名称

    @Column(length = 50)
    private String contact;//联系人

    @Column(length = 50)
    private String number;//联系电话

    @Column(length = 300)
    private String address;//联系地址

    @Column(length = 1000)
    private String remarks;//备注


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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
