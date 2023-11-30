package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.PropertyValue;
import pers.zjh.shop.pojo.PropertyValueExample;

/**
 * @Description:    属性值持久映射层
 */

public interface PropertyValueMapper {

    /**
     * @Describe    根据id删除某一属性值
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加属性值
     * @param       record
     * @return      int
     */
    int insert(PropertyValue record);

    /**
     * @Describe    添加属性值，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(PropertyValue record);

    /** */
    /**
     * @Describe    根据设置的条件查询一堆属性值
     * @param
     * @return
     */
    List<PropertyValue> selectByExample(PropertyValueExample example);

    /**
     * @Describe    根据主键id 查询属性值
     * @param       id
     * @return      PropertyValue
     */
    PropertyValue selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新属性值(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(PropertyValue record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(PropertyValue record);

}