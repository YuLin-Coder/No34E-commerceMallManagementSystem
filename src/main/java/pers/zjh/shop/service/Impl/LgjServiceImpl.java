package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjh.shop.mapper.LgjMapper;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.service.LgjService;

import java.util.List;

@Service
@Transactional
public class LgjServiceImpl implements LgjService{

    @Autowired
    private LgjMapper admindao;

    public List<Admin> findUser(String str) {
        return admindao.selectByName(str);
    }
}
