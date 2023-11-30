package pers.zjh.shop.mapper;

import java.util.List;
import pers.zjh.shop.pojo.ProductImage;
import pers.zjh.shop.pojo.ProductImageExample;

public interface ProductImageMapper {

    /**
     * @Describe    根据id删除商品图片
     * @param       id
     * @return      int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @Describe    添加商品图片
     * @param       record
     * @return      int
     */
    int insert(ProductImage record);

    /**
     * @Describe    添加商品图片，选择性插入数据
     * @param       record
     * @return      int
     */
    int insertSelective(ProductImage record);

    /**
     * @Describe    根据设置的条件查询一堆商品图片
     * @param       example
     * @return      List<ProductImage>
     */
    List<ProductImage> selectByExample(ProductImageExample example);

    /**
     * @Describe    根据主键id 查询商品图片
     * @param       id
     * @return      ProductImage
     */
    ProductImage selectByPrimaryKey(Integer id);

    /**
     * @Describe    对字段进行判断再更新商品图片(如果为Null就忽略更新)
     * @param       record
     * @return      int
     */
    int updateByPrimaryKeySelective(ProductImage record);

    /**
     * @Describe    对注入的字段全部更新
     * @param       record
     * @return      int
     */
    int updateByPrimaryKey(ProductImage record);
}