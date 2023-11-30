package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.ProductImageMapper;
import pers.zjh.shop.pojo.ProductImage;
import pers.zjh.shop.pojo.ProductImageExample;
import pers.zjh.shop.service.ProductImageService;

import java.util.List;

/**
 * @Description:    产品图片业务逻辑接口实现层
 * @Author:         Zhujinghui
 * @CreateDate:     2018/10/27 13:51
 */

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;

    /**
     * @Describe    添加产品图片
     * @param       productImage
     * @return      void
     */
    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    /**
     * @Describe    根据id 删除产品图片
     * @param           id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新产品图片
     * @param       productImage
     * @return      void
     */
    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    /**
     * @Describe    根据产品图片id 查询单张产品图片
     * @param       id
     * @return      ProductImage
     */
    @Override
    public ProductImage get(Integer id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    根据产品id 查询产品的一组图片
     * @param       productId
     * @return      List<ProductImage>
     */
    @Override
    public List<ProductImage> list(Integer productId) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(productId);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }

}
