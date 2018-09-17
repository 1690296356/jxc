package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Goods;
import com.thomas.jxc.repository.GoodsRepository;
import com.thomas.jxc.service.GoodsService;
import com.thomas.jxc.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/6 11:19
 * @描述 商品Service实现类
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private GoodsRepository mGoodsRepository;

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
    public List<Goods> findByTypeId(int typeId) {
        return mGoodsRepository.findByTypeId(typeId);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<Goods> list(Goods goods, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        PageRequest pageable = new PageRequest(page-1, pageSize);
        Page<Goods> pageGoods = mGoodsRepository.findAll((root, query, cb) ->{
            Predicate predicate = cb.conjunction();
            if(goods != null){
                if(StringUtil.isNotEmpty(goods.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"), "%"+goods.getName().trim()+"%"));
                }
                if(goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1){
                    predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
                }
            }
            return predicate;
        }, pageable);
        return pageGoods.getContent();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Long getCount(Goods goods) {
        Long count = mGoodsRepository.count((root, query, cb) ->{
            Predicate predicate = cb.conjunction();
            if(goods != null){
                if(StringUtil.isNotEmpty(goods.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"), "%"+goods.getName().trim()+"%"));
                }
                if(goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1){
                    predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
                }
            }
            return predicate;
        });
        return count;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
