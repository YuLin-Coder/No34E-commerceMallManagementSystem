package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjh.shop.mapper.WqMapper;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.service.WqService;

import java.util.List;

@Service
@Transactional
public class WqServiceImpl implements WqService {

    @Autowired
    private WqMapper productdao;

    @Override
    public List<Product> findProduct(String str) {
        return productdao.selectByProduct(str);
    }
}
