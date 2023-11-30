package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.ProductService;
import pers.zjh.shop.util.Page;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Description:    商品控制器
 */

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * @Describe    添加商品, 设置商品创建日期
     * @param       product
     * @return      返回到产品展示页面
     */
    @RequestMapping("product_add")
    public String add(Product product){
        if (null == product){
            return "fail";
        }
        // 设置产品创建日期
        product.setCreate_date(new Date());
        productService.add(product);
        return "redirect:/product/product_list?cid="+product.getCid();
    }

    /**
     * @Describe    根据id 删除对应商品
     * @param       id
     * @return      返回商品展示页面
     */
    @RequestMapping("product_delete")
    public String delete(Integer id){
        if (null == id){
            return "fail";
        }
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:/product/product_list?cid="+product.getCid();
    }

    /**
     * @Describe    更新商品信息
     * @param       product
     * @return      返回商品展示页面
     */
    @RequestMapping("product_update")
    public String update(Product product){
        if (null == product){
            return "fail";
        }
        productService.update(product);
        return "redirect:/product/product_list?cid="+product.getCid();
    }

    /**
     * @Describe    根据id 找到商品，跳转到该商品修改页面
     * @param       id,model
     * @return
     */
    @RequestMapping("product_edit")
    public String edit(Integer id, Model model){
        if (null == id){
            return "fail";
        }
        Product product = productService.get(id);
        // 得到当前产品对应分类
        Category category = categoryService.get(product.getCid());
        model.addAttribute("c",category);
        model.addAttribute("p",product);
        return "admin/editProduct";
    }

    /**
     * @Describe    根据cid 类别来取出某一分类下的所有商品
     * @param       model,cid,page
     * @return
     */
    @RequestMapping("product_list")
    public String list(Model model, Integer cid, Page page, HttpSession session){
        if (null == cid){
            return "fail";
        }
        Category category = categoryService.get(cid);
        // 取数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> products = productService.list(cid);
        // 得到产品总数
        int total = (int) new PageInfo<>(products).getTotal();
        // 为url设置相应参数
        page.setParam("&cid="+cid);
        page.setTotal(total);

        model.addAttribute("ps",products);
//        model.addAttribute("c",category);
        session.setAttribute("c",category);
        model.addAttribute("page",page);
        return "admin/listProductByCategory";
    }

    /**
     * @Describe    取出所有商品
     * @param
     * @return
     */
    // 取出所有产品,跳转到产品展示界面
    @RequestMapping("getItemList")
    public String list(Model model, Page page){
        // 取出数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> products = productService.list();
        // 得到产品总数
        int total = (int) new PageInfo<>(products).getTotal();
        page.setTotal(total);
        model.addAttribute("ps",products);
        model.addAttribute("page",page);
        return "admin/listAllProduct";
    }

    @RequestMapping("wqAdd")
    public String test(){
        return "admin/wqAdd";
    }

}
