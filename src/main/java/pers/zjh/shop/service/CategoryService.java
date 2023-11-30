package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Category;

import java.util.List;

/**
 * @Description:    分类业务逻辑接口
 */

public interface CategoryService {

    /**
     * @Describe    查询所有分类
     * @param
     * @return      List<Category>
     */
    List<Category> list();

    /**
     * @Describe    添加分类
     * @param       category
     * @return      void
     */
    void add(Category category);

    /**
     * @Describe    根据id 删除分类
     * @param       id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    根据id 查询分类
     * @param       id
     * @return      Category
     */
    Category get(Integer id);

    /**
     * @Describe    更新分类
     * @param       category
     * @return      void
     */
    void update(Category category);

}
