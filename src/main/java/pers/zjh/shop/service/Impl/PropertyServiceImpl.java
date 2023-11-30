package pers.zjh.shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zjh.shop.mapper.PropertyMapper;
import pers.zjh.shop.pojo.Property;
import pers.zjh.shop.pojo.PropertyExample;
import pers.zjh.shop.service.PropertyService;

import java.util.List;

/**
 * @Description:    属性业务逻辑接口
 * @Author: Zhujinghui
 * @CreateDate: 2018/10/27 13:56
 */

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;

    /**
     * @Describe    添加属性
     * @param       property
     * @return      void
     */
    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    /**
     * @Describe    根据id 删除属性
     * @param       id
     * @return      void
     */
    @Override
    public void delete(Integer id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Describe    更新属性
     * @param       property
     * @return      void
     */
    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);
    }

    /**
     * @Describe    根据id 查询单个属性
     * @param       id
     * @return      Property
     */
    @Override
    public Property get(Integer id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    /**
     * @Describe    根据商品id 查询商品的所有属性
     * @param       productId
     * @return      List<Property>
     */
    @Override
    public List<Property> list(Integer productId) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(productId);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }

}
