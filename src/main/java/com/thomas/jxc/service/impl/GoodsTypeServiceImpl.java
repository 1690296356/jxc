package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.GoodsType;
import com.thomas.jxc.repository.GoodsTypeRepository;
import com.thomas.jxc.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:19
 * @描述 TODO
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsTypeRepository mGoodsTypeRepository;

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
    public List<GoodsType> findByParentId(int parentId) {
        return mGoodsTypeRepository.findByParentId(parentId);
    }

    @Override
    public void save(GoodsType goodsType) {
        mGoodsTypeRepository.save(goodsType);
    }

    @Override
    public void delete(Integer id) {
        mGoodsTypeRepository.delete(id);
    }

    @Override
    public GoodsType findById(Integer id) {
        return mGoodsTypeRepository.findOne(id);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
