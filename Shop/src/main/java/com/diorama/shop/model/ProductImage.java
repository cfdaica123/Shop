package com.diorama.shop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Đường dẫn tới file ảnh
    @Column(nullable = false)
    private String url;

    // Văn bản thay thế cho ảnh, tốt cho SEO và người khiếm thị
    @Column(name = "alt_text")
    private String altText;

    // Cờ để xác định đây có phải là ảnh bìa/ảnh chính của sản phẩm không
    @Column(name = "is_cover", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isCover;
    
    // Mỗi ảnh phải thuộc về một sản phẩm
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Getters and Setters...
    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the altText
     */
    public String getAltText() {
        return altText;
    }

    /**
     * @param altText the altText to set
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    /**
     * @return boolean return the isCover
     */
    public boolean isIsCover() {
        return isCover;
    }

    /**
     * @param isCover the isCover to set
     */
    public void setIsCover(boolean isCover) {
        this.isCover = isCover;
    }

    /**
     * @return Product return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

}