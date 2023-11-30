package pers.zjh.shop.service;

import pers.zjh.shop.pojo.ProductImage;

import java.util.List;

/**
 * @Description:    产品图片业务逻辑接口
 */

public interface ProductImageService {

    /**
     * @Describe    添加产品图片
     * @param       productImage
     * @return      void
     */
    void add(ProductImage productImage);

    /**
     * @Describe    根据id 删除产品图片
     * @param           id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新产品图片
     * @param       productImage
     * @return      void
     */
    void update(ProductImage productImage);

    /**
     * @Describe    根据产品图片id 查询单张产品图片
     * @param       id
     * @return      ProductImage
     */
    ProductImage get(Integer id);

    /**
     * @Describe    根据产品id 查询产品的一组图片
     * @param       productId
     * @return      List<ProductImage>
     */
    List<ProductImage> list(Integer productId);

}
