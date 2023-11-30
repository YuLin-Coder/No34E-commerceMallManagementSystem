package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Admin;

import java.util.List;

/**
 * @Description:    管理员业务逻辑接口
 */

public interface AdminService {

    /**
     * @Describe    添加管理员
     * @param       admin
     * @return      void
     */
    void add(Admin admin);

    /**
     * @Describe    根据id 删除管理员
     * @param        id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新管理员信息
     * @param       admin
     * @return      void
     */
    void update(Admin admin);

    /**
     * @Describe    根据id 查询管理员
     * @param       id
     * @return      Admin
     */
    Admin get(Integer id);

    /**
     * @Describe    查询所有管理员
     * @param
     * @return      List<Admin>
     */
    List<Admin> list();

    /**
     * @Describe    根据参数name, password 查询管理员
     * @param       name,password
     * @return      Admin
     */
    Admin get(String name,String password);

    /**
     * @Describe    根据参数name 判断数据库是否存在相同的name 值
     * @param       name
     * @return      boolean
     */
    boolean exist(String name);

}
