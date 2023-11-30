package pers.zjh.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.util.ImageUtil;
import pers.zjh.shop.util.Page;
import pers.zjh.shop.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Description:    产品类别控制器
 */

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * @Describe    取出类别数据,分页展示
     * @param       model,page
     * @return      admin/listCategory
     */
    @RequestMapping("category_list")
    public String list(Model model, Page page){
        // 取数据前设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> categories = categoryService.list();
        if (null != categories ){
            // 获取数据总记录数
            int total = (int) new PageInfo<>(categories).getTotal();
            // 为分页类设置参数: 总记录数
            page.setTotal(total);
            model.addAttribute("page",page);
        }
        model.addAttribute("cs",categories);
        return "admin/listCategory";
    }

    /**
     * @Describe    添加产品类别
     * @param       category,session,uploadedImageFile
     * @return      返回类别展示界面
     */
    @RequestMapping("category_add")
    public String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        if (null == category){
            return "fail";
        }
        categoryService.add(category);
        // 得到分类图片所在文件夹位置
        File imageFolder = new File(session.getServletContext().getRealPath("/images/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        // 判断父级目录是否存在
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        uploadedImageFile.getMultipartFile().transferTo(file);
        // 将图片转化为2进制格式
        BufferedImage image = ImageUtil.change2jpg(file);
        ImageIO.write(image,"jpg",file);
        return "redirect:/category/category_list";
    }

    /**
     * @Describe    根据id 删除某一类别
     * @param       id
     * @return      重定向到类别展示页面
     */
    @RequestMapping("category_delete")
    public String delete(Integer id, HttpSession session){
        if (null == id){
            return "fail";
        }
        categoryService.delete(id);
        // 得到图片所在文件夹位置
        File imageFolder = new File(session.getServletContext().getRealPath("/images/category"));
        // 图片名称
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/category/category_list";
    }

    /**
     * @Describe    根据id 获取某一类别,对分类信息进行编辑
     * @param       id
     * @return      正常跳转到类别编辑页面
     */
    @RequestMapping("category_edit")
    public String edit(Integer id, Model model){
        if (null == id){
            return "fail";
        }
        Category category = categoryService.get(id);
        model.addAttribute("c",category);
        return "admin/editCategory";
    }

    /**
     * @Describe    更新类别信息
     * @param       category,session,uploadedImageFile
     * @return
     */
    @RequestMapping("category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        if (null == category){
            return "fail";
        }
        categoryService.update(category);
        MultipartFile multipartFile = uploadedImageFile.getMultipartFile();
        // 对应分类图片是否也被更换
        if(null!=multipartFile && !multipartFile.isEmpty()){
            // 得到分类图片所在文件夹目录
            File imageFolder = new File(session.getServletContext().getRealPath("/images/category"));
            File file = new File(imageFolder,category.getId()+".jpg");
            multipartFile.transferTo(file);
            // 图片格式转换为2进制
            BufferedImage image = ImageUtil.change2jpg(file);
            ImageIO.write(image,"jpg",file);
        }
        return "redirect:/category/category_list";
    }

    /**
     * @Describe    跳转功能: 跳转到添加分类页面
     * @param
     * @return
     */
    @RequestMapping("addCategoryPage")
    public String add(){return "admin/addCategory";}

}
