package pers.zjh.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.PropertyValue;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.ProductService;
import pers.zjh.shop.service.PropertyValueService;

import java.util.List;

/**
 * @Description:    属性值控制器
 */

@Controller
@RequestMapping("propertyValue")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * @Describe    根据pid 获取对应产品, 跳转到属性编辑页面
     * @param       model,id
     * @return
     */
    @RequestMapping("propertyValue_edit")
    public String edit(Model model, Integer pid){
        if (null == pid){
            return "fail";
        }
        Product product = productService.get(pid);
        // 根据cid 获得属性对应产品
        Category category = categoryService.get(product.getCid());
        // 产品属性值初始化
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product.getId());
        model.addAttribute("c",category);
        model.addAttribute("p",product);
        model.addAttribute("pvs",propertyValues);
        return "admin/editPropertyValue";
    }

    /**
     * @Describe    更新商品属性值, 异步刷新
     * @param       propertyValue
     * @return
     */
    @RequestMapping("propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue){
        if (null == propertyValue){
            return "fail";
        }
        propertyValueService.update(propertyValue);
        return "success";
    }

}
