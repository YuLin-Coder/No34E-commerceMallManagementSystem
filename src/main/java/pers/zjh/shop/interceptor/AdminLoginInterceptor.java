package pers.zjh.shop.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Description:    后台拦截器，登录才可访问其他页面
 */

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在业务处理器处理请求之前调用
     * 如果返回false 退出拦截器 不执行Controller 里的方法
     * 如果返回true 执行被拦截的Controller 里的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        // 存储不需要登录也能访问的路径
        String[] noNeedLoginPage = new String[]{
                "adminLogin",
                "admin_login"
        };
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        // 访问url 以admin 开头
        if (uri.startsWith("/admin/")){
            String s = StringUtils.substringAfterLast(uri,"/admin/");
            if(!Arrays.asList(noNeedLoginPage).contains(s)){
                String admin = (String) session.getAttribute("admin");
                if(null == admin){
                    response.sendRedirect("/admin/adminLogin");
                    return false;
                }
            }
        }
        // 访问路径其他开头
        if (uri.startsWith("/category/") || uri.startsWith("/credit/") || uri.startsWith("/order/") || uri.startsWith("/product/") ||
                uri.startsWith("/property/") || uri.startsWith("/user/")){
            String admin = (String) session.getAttribute("admin");
            if(null == admin){
                response.sendRedirect("/admin/adminLogin");
                return false;
            }
        }
        return true;
    }
}
