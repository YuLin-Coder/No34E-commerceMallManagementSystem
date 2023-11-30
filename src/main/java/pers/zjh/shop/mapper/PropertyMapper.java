package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Property;
import pers.zjh.shop.pojo.PropertyExample;

/**
 * @Description:    属性持久映射层
 */

public interface PropertyMapper {

    /**
     * @Describe    根据id删除属性
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加属性
     * @param       record
     * @return      int
     */
    int insert(Property record);

    /**
     * @Describe     添加属性，选择性插入数据
     * @param           record
     * @return      int
     */
    int insertSelective(Property record);

    /**
     * @Describe    根据设置的条件查询一堆属性
     * @param       example
     * @return       List<Property>
     */
    List<Property> selectByExample(PropertyExample example);

    /**
     * @Describe    根据主键id 查询属性
     * @param       id
     * @return      Property
     */
    Property selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新属性(如果为Null就忽略更新)
     * @param           record
     * @return      int
     */
    int updateByPrimaryKeySelective(Property record);

    /**
     * @Describe    注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Property record);
    
}