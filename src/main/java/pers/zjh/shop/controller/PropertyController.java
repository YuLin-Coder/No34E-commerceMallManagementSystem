package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Property;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.PropertyService;
import pers.zjh.shop.util.Page;

import java.util.List;

/**
 * @Description:    商品属性控制器
 */

@Controller
@RequestMapping("property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    /**
     * @Describe    根据cid 取出类别属性信息
     * @param          model,cid,page
     * @return
     */
    @RequestMapping("property_list")
    public String list(Model model, Integer cid, Page page){
        if (null == cid){
            return "fail";
        }
        Category category = categoryService.get(cid);
        // 取出数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> properties = propertyService.list(cid);
        // 获取属性总数
        int total = (int) new PageInfo<>(properties).getTotal();
        page.setTotal(total);
        // 为url 设置相应参数
        page.setParam("&cid="+cid);

        model.addAttribute("ps",properties);
        model.addAttribute("c",category);
        model.addAttribute("page",page);
        return "admin/listProperty";
    }

    /**
     * @Describe    为该分类增加属性
     * @param       property
     * @return      重定向到类别属性展示页面
     */
    @RequestMapping("property_add")
    public String add(Property property){
        if (null == property){
            return "fail";
        }
        propertyService.add(property);
        return "redirect:/property/property_list?cid="+property.getCid();
    }

    /**
     * @Describe    根据id 删除属性
     * @param       id
     * @return      重定向到属性展示页面
     */
    @RequestMapping("property_delete")
    public String delete(Integer id){
        if (null == id){
            return "fail";
        }
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:/property/property_list?cid="+property.getCid();
    }

    /**
     * @Describe    根据id 获取对应属性类别, 再跳转到编辑属性页面
     * @param       id,model
     * @return
     */
    @RequestMapping("property_edit")
    public String edit(Model model, Integer id){
        if (null == id){
            return "fail";
        }
        // 根据参数id 获取该属性
        Property property = propertyService.get(id);
        // 根据属性的cid 获取对应类别
        Category category = categoryService.get(property.getCid());
        model.addAttribute("p",property);
        model.addAttribute("c",category);
        return "admin/editProperty";
    }

    /**
     * @Describe    更新商品属性
     * @param       property
     * @return      重定向到属性展示页面
     */
    @RequestMapping("property_update")
    public String update(Property property){
        if (null == property){
            return "fail";
        }
        propertyService.update(property);
        return "redirect:/property/property_list?cid="+property.getCid();
    }

}
