package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.pojo.Product;

import java.util.List;

public interface WqService {
    List<Product> findProduct(String str);
}
