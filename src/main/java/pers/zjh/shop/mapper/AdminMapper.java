package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.pojo.AdminExample;

/**
 * @Description:    管理员持久映射层
 */

public interface AdminMapper {

    /**
     * @Describe    根据id删除管理员
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加管理员
     * @param       record
     * @return      int
     */
    int insert(Admin record);

    /**
     * @Describe    添加管理员，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(Admin record);

    /**
     * @Describe    根据设置的条件查询一堆管理员
     * @param       example
     * @return       List<Admin>
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * @Describe    根据主键id 查询管理员
     * @param       id
     * @return      Admin
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新管理员(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Admin record);

}