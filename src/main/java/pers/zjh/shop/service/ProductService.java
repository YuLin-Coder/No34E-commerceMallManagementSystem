package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Product;

import java.util.List;

/**
 * @Description:    商品业务逻辑接口
 */

public interface ProductService {

    /**
     * @Describe    添加商品
     * @param       product
     * @return      void
     */
    void add(Product product);

    /**
     * @Describe    根据id 删除商品
     * @param       id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新商品信息
     * @param       product
     * @return      void
     */
    void update(Product product);

    /**
     * @Describe    根据id 查询商品
     * @param       id
     * @return      Product
     */
    Product get(Integer id);

    /**
     * @Describe    查询所有商品
     * @param
     * @return      List<Product>
     */
    List<Product> list();

    /**
     * @Describe    为单个商品设置展示图片
     * @param           product
     * @return          void
     */
    void setShowProductImage(Product product);

    /**
     * @Describe    根据分类id 查询该类别下的所有商品
     * @param       categoryId
     * @return      List<Product>
     */
    List<Product> list(Integer categoryId);

    /**
     * @Describe    为单个分类填充产品集合
     * @param       category
     * @return      void
     */
    void fill(Category category);

    /**
     * @Describe    为一组分类的每个类别分别填充产品集合
     * @param       categories
     * @return      void
     */
    void fill(List<Category> categories);

    /**
     * @Describe    为单个产品设置销量
     * @param       product
     * @return      void
     */
    void setSaleNumber(Product product);

    /**
     * @Describe    为多个产品设置销量
     * @param       products
     * @return      void
     */
    void setSaleNumber(List<Product> products);

    /**
     * @Describe    关键字搜索产品
     * @param       keyWord
     * @return      List<Product>
     */
    List<Product> list(String keyWord);

}
