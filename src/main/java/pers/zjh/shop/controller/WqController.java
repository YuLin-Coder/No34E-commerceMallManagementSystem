package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjh.shop.pojo.Admin;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.service.LgjService;
import pers.zjh.shop.service.WqService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("product")
public class WqController {
    @Autowired
    private WqService wqservice;

    @RequestMapping("finds")
    @ResponseBody
    public List<Product> findsUser(@RequestParam(value="product" ,required=false)String productName, HttpSession session, @RequestParam(value="pageNo",required=false,defaultValue="1")int pageNo, @RequestParam(value="pageSize",required=false,defaultValue="10")int pageSize){
        System.out.println(productName);
        PageHelper.startPage(pageNo, pageSize);
        List<Product> list= wqservice.findProduct(productName);
        PageInfo<Product> page=new PageInfo<Product>(list);
        session.setAttribute("list", list);
    return list;
    }

    @RequestMapping("test")
    public String test(){
        return "admin/wqList";
    }
}
