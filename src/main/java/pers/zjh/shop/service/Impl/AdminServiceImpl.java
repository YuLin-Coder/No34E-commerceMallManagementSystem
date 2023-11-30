package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.AdminMapper;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.pojo.AdminExample;
import pers.zjh.shop.service.AdminService;

import java.util.List;

/**
 * @Description:    管理员业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 14:05
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * @Describe    添加管理员
     * @param       admin
     * @return      void
     */
    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * @Describe    根据id 删除管理员
     * @param        id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新管理员信息
     * @param       admin
     * @return      void
     */
    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * @Describe    根据id 查询管理员
     * @param       id
     * @return      Admin
     */
    @Override
    public Admin get(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    查询所有管理员
     * @param
     * @return      List<Admin>
     */
    @Override
    public List<Admin> list() {
        AdminExample example = new AdminExample();
        example.setOrderByClause("id desc");
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins;
    }

    /**
     * @Describe    根据参数name, password 查询管理员
     * @param       name,password
     * @return      Admin
     */
    @Override
    public Admin get(String name, String password) {
        AdminExample example = new AdminExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Admin> admins = adminMapper.selectByExample(example);
        if(admins.isEmpty())
            return null;
        return admins.get(0);
    }

    /**
     * @Describe    根据参数name 判断数据库是否存在相同的name 值
     * @param       name
     * @return      boolean
     */
    @Override
    public boolean exist(String name) {
        AdminExample example = new AdminExample();
        example.createCriteria().andNameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        if(admins.isEmpty()){
            return false;
        }
        return true;
    }

}
