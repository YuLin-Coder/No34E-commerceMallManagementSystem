package pers.zjh.shop.service;

import pers.zjh.shop.pojo.User;

import java.util.List;

/**
 * @Description:    会员业务逻辑接口
 */

public interface UserService {

    /**
     * @Describe    添加会员
     * @param       user
     * @return      void
     */
    void add(User user);

    /**
     * @Describe    根据id 删除会员
     * @param       id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新会员信息
     * @param       user
     * @return      void
     */
    void update(User user);

    /**
     * @Describe    根据id 查询会员
     * @param       id
     * @return      User
     */
    User get(Integer id);

    /**
     * @Describe    查询所有会员
     * @param
     * @return      List<User>
     */
    List<User> list();

    /**
     * @Describe    根据参数name 判断数据库是否存在相同 name 值
     * @param       name
     * @return      boolean
     */
    boolean exist(String name);

    /**
     * @Describe    根据参数name, password 验证会员账号密码是否正确
     * @param       name
     * @param       password
     * @return      User
     */
    User get(String name, String password);

}
