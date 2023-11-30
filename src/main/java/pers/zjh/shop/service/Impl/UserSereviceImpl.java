package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.UserMapper;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.pojo.UserExample;
import pers.zjh.shop.service.UserService;

import java.util.List;

/**
 * @Description:    会员业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 14:03
 */

@Service
public class UserSereviceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * @Describe    添加会员
     * @param       user
     * @return      void
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * @Describe    根据id 删除会员
     * @param       id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新会员信息
     * @param       user
     * @return      void
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * @Describe    根据id 查询会员
     * @param       id
     * @return      User
     */
    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    查询所有会员
     * @param
     * @return      List<User>
     */
    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    /**
     * @Describe    根据参数name 判断数据库是否存在相同 name 值
     * @param       name
     * @return      boolean
     */
    @Override
    public boolean exist(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * @Describe    根据参数name, password 验证会员账号密码是否正确
     * @param       name
     * @param       password
     * @return      User
     */
    @Override
    public User get(String name, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

}
