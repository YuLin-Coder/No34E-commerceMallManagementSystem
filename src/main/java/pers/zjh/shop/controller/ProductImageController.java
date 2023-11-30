package pers.zjh.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.zjh.shop.pojo.Category;
import pers.zjh.shop.pojo.Product;
import pers.zjh.shop.pojo.ProductImage;
import pers.zjh.shop.service.CategoryService;
import pers.zjh.shop.service.ProductImageService;
import pers.zjh.shop.service.ProductService;
import pers.zjh.shop.util.ImageUtil;
import pers.zjh.shop.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @Description:    产品图片控制器
 */

@Controller
@RequestMapping("productImage")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * @Describe    根据pid 取出产品对应的图片
     * @param       pid,model
     * @return
     */
    @RequestMapping("productImage_list")
    public String list(Model model,Integer pid){
        if (null == pid){
            return "fail";
        }
        // 根据pid 得到对应产品
        Product product = productService.get(pid);
        // 根据产品cid 获取对应分类
        Category category = categoryService.get(product.getCid());
        // 取出所有图片
        List<ProductImage> productImages = productImageService.list(pid);
        model.addAttribute("p",product);
        model.addAttribute("c",category);
        model.addAttribute("pis",productImages);
        return "admin/listProductImage";
    }

    /**
     * @Describe    为商品增加图片
     * @param       productImage,session,uploadedImageFile
     * @return
     */
    @RequestMapping("productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile){
        if (null == productImage){
            return "fail";
        }
        productImageService.add(productImage);
        // 为正常大小图片设置存放位置
        String imageFolder = session.getServletContext().getRealPath("images/productSingle");
        // 小号图片存放位置
        String imageFolder_small = session.getServletContext().getRealPath("images/productSingle_small");
        // 中号图片存放位置
        String imageFolder_middle = session.getServletContext().getRealPath("images/productSingle_middle");
        String fileName = productImage.getId()+".jpg";
        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();
        try {
            uploadedImageFile.getMultipartFile().transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img, "jpg", f);

            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);

            ImageUtil.resizeImage(f, 56, 56, f_small);
            ImageUtil.resizeImage(f, 217, 190, f_middle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/productImage/productImage_list?pid="+productImage.getPid();
    }

    /**
     * @Describe    根据id 删除图片
     * @param       id,session
     * @return
     */
    @RequestMapping("productImage_delete")
    public String delete(Integer id, HttpSession session){
        if (null == id){
            return "fail";
        }
        ProductImage productImage = productImageService.get(id);
        // 为正常大小图片设置存放位置
        String imageFolder= session.getServletContext().getRealPath("images/productSingle");
        // 小号图片存放位置
        String imageFolder_small= session.getServletContext().getRealPath("images/productSingle_small");
        // 中号图片存放位置
        String imageFolder_middle= session.getServletContext().getRealPath("images/productSingle_middle");
        String fileName = productImage.getId()+ ".jpg";
        File imageFile = new File(imageFolder,fileName);
        File f_small = new File(imageFolder_small,fileName);
        File f_middle = new File(imageFolder_middle,fileName);
        imageFile.delete();
        f_small.delete();
        f_middle.delete();

        productImageService.delete(id);
        return "redirect:/productImage/productImage_list?pid="+productImage.getPid();
    }

}
