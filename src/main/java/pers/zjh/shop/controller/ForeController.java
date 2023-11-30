package pers.zjh.shop.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjh.shop.pojo.*;
import pers.zjh.shop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:    前端页面展示控制器
 */

@Controller
@RequestMapping("fore")
public class ForeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CreditService creditService;

    /**
     * @Describe    取出分类数据, 跳转到商城首页
     * @param       model
     * @return      跳转到首页
     */
    @RequestMapping("foreHome")
    public String home(Model model){
        List<Category> categories = categoryService.list();
        if (null != categories){
            productService.fill(categories);
        }
        model.addAttribute("categories",categories);
        return "fore/foreHome";
    }

    /**
     * @Describe    用户退出登录
     * @param       session
     * @return      重定向到商城首页
     */
    @RequestMapping("forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/fore/foreHome";
    }

    /**
     * @Describe        展示产品页面
     * @param           pid
     * @param           model
     * @return
     */
    @RequestMapping("foreproduct")
    public String showProduct(int pid, Model model){
        // 根据pid 获取分类
        Product product = productService.get(pid);
        if (null == product){
            return "fail";
        }
        // 获取产品图片
        List<ProductImage> productImages = productImageService.list(product.getId());
        product.setProductImages(productImages);
        // 获取产品属性值
        List<PropertyValue> propertyValues = propertyValueService.list(product.getId());
        // 为产品设置销量
        productService.setSaleNumber(product);
        // 取出产品图片
        ProductImage productImage = product.getShowPicture();
        // 取出分类
        Category category = categoryService.get(product.getCid());
        model.addAttribute("category",category);
        model.addAttribute("productImage",productImage);
        model.addAttribute("product",product);
        model.addAttribute("propertyValues",propertyValues);
        return "fore/showProduct";
    }

    /**
     * @Describe    检查用户是否登录
     * @param       session
     * @return
     */
    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (null != user)
            return "success";
        return "fail";
    }

    /**
     * @Describe    Ajax验证 模态窗口登录账号密码是否正确
     * @param       name
     * @param       password
     * @param       session
     * @return
     */
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(String name, String password, HttpSession session){
        User user = userService.get(name,password);
        if (null == user){
            return "fail";
        }
        session.setAttribute("user",user);
        return "success";
    }

    /**
     * @Describe    根据分类展示这一类别的所有商品
     * @param       cid
     * @param       model
     * @return
     */
    @RequestMapping("forecategory")
    public String category(int cid, Model model){
        Category category = categoryService.get(cid);
        if (null != category){
            // 分类填充商品
            productService.fill(category);
            // 为这一类别下的商品都设置销量
            List<Product> products = category.getProducts();
            if (null != products){
                productService.setSaleNumber(products);
            }
        }
        model.addAttribute("category",category);
        return "fore/showCategory";
    }

    /**
     * @Describe    搜索框搜索商品
     * @param       keyword
     * @param       model
     * @return      根据关键字展示商品
     */
    @RequestMapping("foresearch")
    public String search(String keyword, Model model){
        List<Product> products = productService.list(keyword);
        if (null != products){
            productService.setSaleNumber(products);
        }
        model.addAttribute("products",products);
        return "fore/searchResult";
    }

    /**
     * @Describe    直接点击购买商品
     * @param       pid
     * @param       num
     * @param       session
     * @return      跳转到填写收货信息页面
     */
    @RequestMapping("forebuyFirst")
    public String buyFirst(int pid, int num, HttpSession session){
        Product product = productService.get(pid);
        // 新增订单项ID
        int oiid = 0;
        User user = (User) session.getAttribute("user");
        OrderItem orderItem = new OrderItem();
        orderItem.setUid(user.getId());
        orderItem.setNumber(num);
        orderItem.setPid(pid);
        orderItemService.add(orderItem);
        oiid = orderItem.getId();
        return "redirect:forebuy?oiid="+oiid;
    }

    /**
     * @Describe    根据订单项id数组找到每一个订单 结算商品
     * @param       oiid
     * @param       session
     * @param       model
     * @return      跳转到填写收货地址页面
     */
    @RequestMapping("forebuy")
    public String buy(String[] oiid, HttpSession session, Model model){
        List<OrderItem> orderItems = new ArrayList<>();
        float totalPrice = 0;
        // 根据订单项id 查找订单
        for (String s : oiid) {
            int id = Integer.parseInt(s);
            OrderItem orderItem = orderItemService.get(id);
            totalPrice += orderItem.getProduct().getPromote_price()*orderItem.getNumber();
            orderItems.add(orderItem);
        }
        session.setAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", totalPrice);
        return "fore/buy";
    }

    /**
     * @Describe    根据pid 把商品加入购物车
     * @param       pid
     * @param       num
     * @return
     */
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid, int num, HttpSession session){
        Product product = productService.get(pid);
        if (null == product){
            return "fail";
        }
        User user = (User) session.getAttribute("user");
        // 标识购物车是否已经存在该产品
        boolean exist = false;

        // 查询当前用户购物车订单项, 存在该产品则加对应数量
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        for (OrderItem orderItem : orderItems){
            if(orderItem.getProduct().getId().intValue() == product.getId().intValue()){
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                exist = true;
                break;
            }
        }

        // 购物车不存在该产品，则生成新的订单项
        if(!exist){
            OrderItem orderItem = new OrderItem();
            orderItem.setUid(user.getId());
            orderItem.setNumber(num);
            orderItem.setPid(pid);
            orderItemService.add(orderItem);
        }
        return "success";
    }

    /**
     * @Describe    查看我的购物车
     * @param       model
     * @param       session
     * @return      返回购物车页面
     */
    @RequestMapping("forecart")
    public String cart(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (null == user){
            return "fail";
        }
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        model.addAttribute("orderItems",orderItems);
        return "fore/shoppingCart";
    }

    /**
     * @Describe    调整订单数量
     * @param       session
     * @param       pid
     * @param       number
     * @return
     */
    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(HttpSession session, int pid, int number){
        User user = (User) session.getAttribute("user");
        if (null == user){
            return "fail";
        }
        // 判断用户已经登录, 调整订单数量
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        for (OrderItem orderItem : orderItems){
            if (orderItem.getProduct().getId().intValue() == pid){
                orderItem.setNumber(number);
                orderItemService.update(orderItem);
                break;
            }
        }
        return "success";
    }

    /**
     * @Describe    删除订单项
     * @param       session
     * @param       oiid
     * @return
     */
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(HttpSession session, int oiid){
        User user = (User) session.getAttribute("user");
        if (null == user){
            return "fail";
        }
        orderItemService.delete(oiid);
        return "success";
    }

    /**
     * @Describe    跳转功能，跳转到错误界面
     * @param
     * @return
     */
    @RequestMapping("fail")
    public String fail(){
        return "fail";
    }

    /**
     * @Describe    跳转功能, 跳转到前端用户登录界面
     * @param
     * @return
     */
    @RequestMapping("Login")
    public String foreLogin(){ return "fore/foreLogin"; }

    /**
     * @Describe    点击登录，进行用户账号密码验证
     * @param       request,name,password,session
     * @return
     */
    @RequestMapping("fore_login")
    public String forelogin(HttpServletRequest request, String name, String password, HttpSession session){
        User user = userService.get(name,password);
        if (null == user){
            request.setAttribute("message","账号密码有误");
            return "fore/foreLogin";
        }
        // 用户已登录, 保存用户名, 跳转到前台首页
        session.setAttribute("user",user);
        return "redirect:/fore/foreHome";
    }

    /**
     * @Describe    点击注册, 根据该用户名是否已经被使用验证是否注册成功
     * @param       user
     * @param       request
     * @return
     */
    @RequestMapping("register")
    public String register(User user, HttpServletRequest request){
        if (null == user){
            return "fail";
        }
        String name = user.getName();
        // 标识用户名是否已经被人使用
        boolean isExist = userService.exist(name);
        if (isExist){
            String s = "用户名已经存在! ";
            request.setAttribute("message",s);
        }else{
            // 为新会员创建积分记录
            Credit credit = new Credit();
            credit.setName("青铜");
            credit.setNumber(0);
            // 添加会员
            userService.add(user);
            // 添加积分记录
            credit.setUid(user.getId());
            creditService.add(credit);
            String s = "注册成功! ";
            request.setAttribute("message",s);
        }
        return "fore/foreLogin";
    }

    /**
     * @Describe    提交订单,为订单设置参数
     * @param       model
     * @param       order
     * @param       session
     * @return      重定向，带参请求到支付页面
     */
    @RequestMapping("forecreateOrder")
    public String createOrder(Model model, Order order, HttpSession session){
        User user = (User) session.getAttribute("user");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreate_date(new Date());
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        float totalPrice = orderService.add(order,orderItems);

        // 购买商品增加积分
        int total = (int)totalPrice;
        Credit credit = creditService.get(user.getId());
        credit.setNumber(credit.getNumber() + total);
        creditService.update(credit);
        return "redirect:forealipay?orderId="+order.getId() + "&totalPrice=" + totalPrice;
    }

    /**
     * @Describe       跳转功能，发起支付请求
     * @param
     * @return
     */
    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }

    /**
     * @Describe    点击确认支付,服务端跳转到支付页面
     * @param       orderId,totalPrice,model
     * @return
     */
    @RequestMapping("forepay")
    public String pay(int orderId, float totalPrice, Model model){
        Order order = orderService.get(orderId);
        if (null == order){
            return "fail";
        }
        order.setStatus(OrderService.waitDelivery);
        order.setPay_date(new Date());
        orderService.update(order);
        model.addAttribute("order",order);
        return "fore/pay";
    }

    /**
     * @Describe       跳转到我的订单页面
     * @param
     * @return
     */
    @RequestMapping("forebought")
    public String bought(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.list(user.getId(),OrderService.delete);
        if (null != orders){
            orderItemService.fillOrder(orders);
            model.addAttribute("orders",orders);
        }
        return "fore/bought";
    }

    /**
     * @Describe    点击确认收货，跳转到确认页面
     * @param       model
     * @param       orderId
     * @return
     */
    @RequestMapping("foreconfirmPay")
    public String confirmPay(Model model, Integer orderId){
        Order order = orderService.get(orderId);
        if (null != order){
            orderItemService.fill(order);
        }
        model.addAttribute("order",order);
        return "fore/confirmPay";
    }

    /**
     * @Describe    确认收货后，修改订单状态为已完成，跳转到交易成功页面
     * @param       model
     * @param       orderId
     * @return
     */
    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(Model model, int orderId){
        Order order = orderService.get(orderId);
        if (null != order){
            // 修改订单状态
            order.setStatus(OrderService.finish);
            // 设置订单确认时间
            order.setConfirm_date(new Date());
        }
        orderService.update(order);
        return "fore/orderConfirmed";
    }

    /**
     * @Describe    点击删除订单，修改订单状态为 删除
     * @param
     * @return
     */
    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(Integer orderId){
        if (null == orderId){
            return "fail";
        }
        Order order = orderService.get(orderId);
        order.setStatus(OrderService.delete);
        orderService.update(order);
        return "success";
    }

    /**
     * @Describe    展示个人信息
     * @param       model
     * @param       session
     * @return
     */
    @RequestMapping("information")
    public String showInformation(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (null == user){
            return "fail";
        }
        // 为用户增加积分属性
        Credit credit = creditService.get(user.getId());
        user.setCredit(credit);

        session.setAttribute("user",user);
        return "fore/myInformation";
    }

}
