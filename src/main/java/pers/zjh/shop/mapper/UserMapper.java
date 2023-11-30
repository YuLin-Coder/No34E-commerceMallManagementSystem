package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.User;
import pers.zjh.shop.pojo.UserExample;

/**
 * @Description:    会员持久映射层
 */

public interface UserMapper {

    /**
     * @Describe    根据id删除某一会员
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加会员
     * @param       record
     * @return      int
     */
    int insert(User record);

    /**
     * @Describe    添加会员，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(User record);

    /**
     * @Describe    根据设置的条件查询一堆会员
     * @param       example
     * @return      List<User>
     */
    List<User> selectByExample(UserExample example);

    /**
     * @Describe    根据主键id 查询会员
     * @param       id
     * @return      User
     */
    User selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新会员(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(User record);

}