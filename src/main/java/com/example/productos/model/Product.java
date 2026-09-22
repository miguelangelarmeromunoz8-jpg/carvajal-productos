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

    private String imagenUrl;   // NUEVO: ruta pública de la imagen, ej. "/uploads/abc123.jpg"

    public Product() {}

    public Product(String nameProduct, Long price, Integer stock, Long idUser, String imagenUrl) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
        this.idUser = idUser;
        this.imagenUrl = imagenUrl;
    }

    public Long getIdProduct() { return idProduct; }
    public void setIdProduct(Long idProduct) { this.idProduct = idProduct; }

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}