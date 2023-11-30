package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.ProductExample;

/**
 * @Description:    商品持久映射层
 */

public interface ProductMapper {

    /**
     * @Describe    根据id删除商品
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加商品
     * @param       record
     * @return      int
     */
    int insert(Product record);

    /**
     * @Describe    添加商品，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(Product record);

    /**
     * @Describe    根据设置的条件查询一堆商品
     * @param       example
     * @return      List<Product>
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * @Describe    根据主键id 查询商品
     * @param       id
     * @return      Product
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新商品(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(Product record);


    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(Product record);
    
}