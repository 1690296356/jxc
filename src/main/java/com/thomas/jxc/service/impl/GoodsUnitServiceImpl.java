package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.GoodsUnit;
import com.thomas.jxc.repository.GoodsUnitRepository;
import com.thomas.jxc.service.GoodsUnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:19
 * @描述 TODO
 */
@Service("goodsUnitService")
public class GoodsUnitServiceImpl implements GoodsUnitService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsUnitRepository mGoodsUnitRepository;

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
    public List<GoodsUnit> listAll() {
        return mGoodsUnitRepository.findAll();
    }

    @Override
    public void save(GoodsUnit goodsUnit) {
        mGoodsUnitRepository.save(goodsUnit);
    }

    @Override
    public void delete(Integer id) {
        mGoodsUnitRepository.delete(id);
    }

    @Override
    public GoodsUnit findById(Integer id) {
        return mGoodsUnitRepository.findOne(id);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
