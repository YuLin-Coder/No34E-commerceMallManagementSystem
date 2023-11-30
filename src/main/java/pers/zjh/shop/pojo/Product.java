package pers.zjh.shop.pojo;

import java.util.Date;
import java.util.List;

public class Product {
    private Integer id;

    private String name;

    private Float original_price;

    private Float promote_price;

    private Integer stock;

    private Integer cid;

    private Date create_date;

    private Category category;

    private ProductImage showPicture;

    // 产品图片
    private List<ProductImage> productImages;

    // 产品销量
    private Integer saleCount;

    public Float getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Float original_price) {
        this.original_price = original_price;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public ProductImage getShowPicture() {
        return showPicture;
    }

    public void setShowPicture(ProductImage showPicture) {
        this.showPicture = showPicture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

//    public Float getoriginal_price() {
//        return original_price;
//    }

    public void setoriginal_price(Float original_price) {
        this.original_price = original_price;
    }

    public Float getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(Float promote_price) {
        this.promote_price = promote_price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}