package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.CategoryExample;

/**
 * @Description:    分类持久映射层
 */

public interface CategoryMapper {

    /**
     * @Describe    根据id删除某一分类
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加分类
     * @param       record
     * @return      insert
     */
    int insert(Category record);

    /**
     * @Describe    添加分类，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(Category record);

    /**
     * @Describe    根据设置的条件查询一堆分类
     * @param       example
     * @return      List<Category>
     */
    List<Category> selectByExample(CategoryExample example);

    /**
     * @Describe     根据主键id 查询分类
     * @param           id
     * @return      Category
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新分类(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Category record);

}