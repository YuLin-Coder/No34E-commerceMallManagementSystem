package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.CategoryMapper;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.CategoryExample;
import pers.zjh.shop.service.CategoryService;

import java.util.List;

/**
 * @Description:    分类业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 13:42
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * @Describe    查询所有分类
     * @param
     * @return      List<Category>
     */
    @Override
    public List<Category> list() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    /**
     * @Describe    添加分类
     * @param       category
     * @return      void
     */
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * @Describe    根据id 删除分类
     * @param       id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    根据id 查询分类
     * @param       id
     * @return      Category
     */
    @Override
    public Category get(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    更新分类
     * @param       category
     * @return      void
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

}
