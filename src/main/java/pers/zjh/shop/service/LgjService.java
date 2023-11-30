package pers.zjh.shop.service;
import pers.zjh.shop.pojo.Admin;
import java.util.List;

public interface LgjService {
    List<Admin> findUser(String str);
}
