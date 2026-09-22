package com.example.productos.controller;

import com.example.productos.model.Product;
import com.example.productos.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    // ==========================================
    // LISTAR TODOS LOS PRODUCTOS
    // ==========================================

    @GetMapping
    public List<Product> listar() {

        return productService.listarTodos();
    }


    // ==========================================
    // BUSCAR PRODUCTO POR ID
    // ==========================================

    @GetMapping("/{id}")
    public Product buscar(@PathVariable Long id) {

        return productService.buscarPorId(id);
    }


    // ==========================================
    // VERIFICAR STOCK
    // ==========================================

    @GetMapping("/{id}/stock")
    public ResponseEntity<Map<String, Object>> verificarStock(
            @PathVariable Long id) {

        Product producto = productService.buscarPorId(id);

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put(
                "disponible",
                producto.getStock() > 0
        );

        respuesta.put(
                "stockActual",
                producto.getStock()
        );

        return ResponseEntity.ok(respuesta);
    }


    // ==========================================
    // CREAR PRODUCTO
    // ==========================================

    @PostMapping
    public ResponseEntity<Product> crear(
            @Valid @RequestBody Product producto) {

        Product nuevo = productService.guardar(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }


    // ==========================================
    // ACTUALIZAR PRODUCTO
    // ==========================================

    @PutMapping("/{id}")
    public Product actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Product datos) {

        return productService.actualizar(id, datos);
    }


    // ==========================================
    // ELIMINAR PRODUCTO
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        productService.eliminar(id);

        return ResponseEntity.noContent().build();
    }


    // ==========================================
    // SUBIR IMAGEN
    // ==========================================

    @PostMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Product> subirImagen(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file)
            throws Exception {

        if (file.isEmpty()) {

            return ResponseEntity.badRequest().build();
        }

        Product producto =
                productService.guardarImagen(id, file);

        return ResponseEntity.ok(producto);
    }


    // ==========================================
    // OBTENER IMAGEN
    // ==========================================

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> obtenerImagen(
            @PathVariable Long id) {

        byte[] imagen =
                productService.obtenerImagen(id);

        String tipo =
                productService.obtenerTipoImagen(id);

        MediaType mediaType;

        try {

            mediaType = MediaType.parseMediaType(tipo);

        } catch (Exception e) {

            mediaType = MediaType.IMAGE_JPEG;
        }

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(imagen);
    }
}