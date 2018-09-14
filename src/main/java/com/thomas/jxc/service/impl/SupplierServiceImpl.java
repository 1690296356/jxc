package com.thomas.jxc.service.impl;

import com.thomas.jxc.entity.Supplier;
import com.thomas.jxc.repository.SupplierRepository;
import com.thomas.jxc.service.SupplierService;
import com.thomas.jxc.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @创建人 thomas_liu
 * @创建时间 2018/9/13 19:27
 * @描述 TODO
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    @Resource
    private SupplierRepository mSupplierRepository;

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
    public List<Supplier> list(Supplier supplier, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        Pageable pageable = new PageRequest(page-1, pageSize);
        Page<Supplier> pageSupplier = mSupplierRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(supplier != null){
                if(StringUtil.isNotEmpty(supplier.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+supplier.getName()+"%"));
                }
            }
            return predicate;
        },pageable);
        return pageSupplier.getContent();
    }

    @Override
    public Long getCount(Supplier supplier) {
        Long count = mSupplierRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(supplier != null){
                if(StringUtil.isNotEmpty(supplier.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"),"%"+supplier.getName()+"%"));
                }
            }
            return predicate;
        });
        return count;
    }

    @Override
    public void save(Supplier supplier) {
        mSupplierRepository.save(supplier);
    }

    @Override
    public void delete(Integer id) {
        mSupplierRepository.delete(id);
    }

    @Override
    public Supplier findById(Integer id) {
        return mSupplierRepository.findOne(id);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
