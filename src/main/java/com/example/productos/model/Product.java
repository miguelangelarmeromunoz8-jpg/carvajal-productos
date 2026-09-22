package com.example.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank
    private String nameProduct;

    @NotNull
    @Positive
    private Long price;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    private Long idUser;

    /*
     * Nombre del archivo de imagen.
     * Ejemplo: camiseta.jpg
     */
    private String imageName;

    /*
     * Tipo MIME de la imagen.
     * Ejemplo: image/jpeg
     */
    private String imageType;

    /*
     * Imagen almacenada directamente en la base de datos.
     */
    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    public Product() {
    }

    public Product(String nameProduct, Long price, Integer stock, Long idUser) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
        this.idUser = idUser;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}