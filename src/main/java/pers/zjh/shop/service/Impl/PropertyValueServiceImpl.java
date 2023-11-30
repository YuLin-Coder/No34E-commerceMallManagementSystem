package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.PropertyValueMapper;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.Property;
import pers.zjh.shop.pojo.PropertyValue;
import pers.zjh.shop.pojo.PropertyValueExample;
import pers.zjh.shop.service.PropertyService;
import pers.zjh.shop.service.PropertyValueService;

import java.util.List;

/**
 * @Description:    属性值业务逻辑接口实现层
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 14:00
 */

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    /**
     * @Describe    为商品初始化属性值
     * @param       product
     * @return      void
     */
    @Override
    public void init(Product product) {
        // 获取分类下的所有属性
        List<Property> properties = propertyService.list(product.getCid());
        for(Property property : properties){
            PropertyValue propertyValue = get(property.getId(),product.getId());

            // 商品属性值若为空则新增属性值
            if(null == propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setPtid(property.getId());
                propertyValue.setPid(product.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    /**
     * @Describe    更新属性值
     * @param       propertyValue
     * @return      void
     */
    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    /**
     * @Describe     根据属性id 和产品id 得到唯一属性值
     * @param           productId
     * @param           propertyId
     * @return          PropertyValue
     */
    @Override
    public PropertyValue get(Integer propertyId, Integer productId) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(propertyId).andPidEqualTo(productId);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        if(propertyValues.isEmpty()){
            return null;
        }
        return propertyValues.get(0);
    }

    /**
     * @Describe    根据产品id 获取该产品所有属性值
     * @param       productId
     * @return      List<PropertyValue>
     */
    @Override
    public List<PropertyValue> list(Integer productId) {
        // 获取产品属性值
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(productId);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);

        // 为属性值设置属性
        for(PropertyValue propertyValue : propertyValues){
            Property property = propertyService.get(propertyValue.getPtid());
            propertyValue.setProperty(property);
        }
        return propertyValues;
    }

}
