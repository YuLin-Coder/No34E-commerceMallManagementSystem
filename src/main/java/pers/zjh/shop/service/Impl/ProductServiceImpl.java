package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.ProductMapper;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.ProductExample;
import pers.zjh.shop.pojo.ProductImage;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.OrderItemService;
import pers.zjh.shop.service.ProductImageService;
import pers.zjh.shop.service.ProductService;

import java.util.List;

/**
 * @Description:    商品业务逻辑接口实现层
 * @Author:         Zhujinghui
 * @CreateDate:     2018/10/27 13:54
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * @Describe    添加商品
     * @param       product
     * @return      void
     */
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    /**
     * @Describe    根据id 删除商品
     * @param       id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新商品信息
     * @param       product
     * @return      void
     */
    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * @Describe    根据id 查询商品
     * @param       id
     * @return      Product
     */
    @Override
    public Product get(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        // 设置商品展示图片
        setShowProductImage(product);
        return product;
    }

    /**
     * @Describe    根据分类id 查询该类别下的所有商品
     * @param       cid
     * @return      List<Product>
     */
    @Override
    public List<Product> list(Integer cid) {
        // 根据分类id 查出该分类下所有商品
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);

        // 为商品设置分类属性
        setCategory(products);
        // 为商品设置展示图片属性
        setShowProductImage(products);
        return products;
    }

    /**
     * @Describe    查询所有商品
     * @param
     * @return      List<Product>
     */
    @Override
    public List<Product> list() {
        ProductExample example = new ProductExample();
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);
        // 为一组商品设置展示图片属性
        setShowProductImage(products);
        return products;
    }

    /**
     * @Describe    为单个分类填充产品集合
     * @param       category
     * @return      void
     */
    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    /**
     * @Describe    为一组分类的每个类别分别填充产品集合
     * @param       categories
     * @return      void
     */
    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories){
            fill(category);
        }
    }

    /**
     * @Describe    为单个产品设置销量
     * @param       product
     * @return      void
     */
    @Override
    public void setSaleNumber(Product product) {
        // 通过产品id 得到销量
        int saleCount = orderItemService.getSaleCount(product.getId());
        // 设置销量属性
        product.setSaleCount(saleCount);
    }

    /**
     * @Describe    为多个产品设置销量
     * @param       products
     * @return      void
     */
    @Override
    public void setSaleNumber(List<Product> products) {
        for (Product product : products){
            setSaleNumber(product);
        }
    }

    /**
     * @Describe    关键字搜索产品
     * @param       keyWord
     * @return      List<Product>
     */
    @Override
    public List<Product> list(String keyWord) {
        // 通过关键字查询商品
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyWord + "%");
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);

        // 为一组商品设置展示图片属性
        setShowProductImage(products);
        // 为一组商品设置分类属性
        setCategory(products);
        return products;
    }

    /**
     * @Describe    为单个商品设置展示图片
     * @param           product
     * @return          void
     */
    @Override
    public void setShowProductImage(Product product) {
        if (null != product){
            // 根据产品id 取出该产品的所有图片
            List<ProductImage> productImages = productImageService.list(product.getId());

            if(!productImages.isEmpty()){
                // 取产品图片的第一张
                ProductImage productImage = productImages.get(0);
                // 为产品设置展示图片属性
                product.setShowPicture(productImage);
            }
        }
    }

    /**
     * @Describe    为一组商品设置展示图片
     * @param       products
     * @return      void
     */
    public void setShowProductImage(List<Product> products) {
        for (Product p : products) {
            setShowProductImage(p);
        }
    }

    /**
     * @Describe    为单个商品设置分类属性
     * @param       product
     * @return      void
     */
    public void setCategory(Product product){
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
    }

    /**
     * @Describe    为一组商品的每个商品分别设置分类属性
     * @param       products
     * @return      void
     */
    public void setCategory(List<Product> products){
        for (Product product : products){
            setCategory(product);
        }
    }

}
