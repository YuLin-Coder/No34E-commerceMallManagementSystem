package pers.zjh.shop.service;

import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.PropertyValue;

import java.util.List;

/**
 * @Description:    属性值业务逻辑接口
 */

public interface PropertyValueService {

    /**
     * @Describe    为商品初始化属性值
     * @param       product
     * @return      void
     */
    void init(Product product);

    /**
     * @Describe    更新属性值
     * @param       propertyValue
     * @return      void
     */
    void update(PropertyValue propertyValue);

    /**
     * @Describe     根据属性id 和产品id 得到唯一属性值
     * @param           productId
     * @param           propertyId
     * @return          PropertyValue
     */
    PropertyValue get(Integer propertyId, Integer productId);

    /**
     * @Describe    根据产品id 获取该产品所有属性值
     * @param       productId
     * @return      List<PropertyValue>
     */
    List<PropertyValue> list(Integer productId);

}
