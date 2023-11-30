package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Property;

import java.util.List;

/**
 * @Description:    属性业务逻辑接口
 */

public interface PropertyService {

    /**
     * @Describe    添加属性
     * @param       property
     * @return      void
     */
    void add(Property property);

    /**
     * @Describe    根据id 删除属性
     * @param       id
     * @return      void
     */
    void delete(Integer id);

    /**
     * @Describe    更新属性
     * @param       property
     * @return      void
     */
    void update(Property property);

    /**
     * @Describe    根据id 查询单个属性
     * @param       id
     * @return      Property
     */
    Property get(Integer id);

    /**
     * @Describe    根据商品id 查询商品的所有属性
     * @param       productId
     * @return      List<Property>
     */
    List<Property> list(Integer productId);

}
