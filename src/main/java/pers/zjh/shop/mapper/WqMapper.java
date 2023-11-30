package pers.zjh.shop.mapper;

import pers.zjh.shop.pojo.Product;

import java.util.List;

public interface WqMapper {
    public List<Product> selectByProduct(String product);
}
