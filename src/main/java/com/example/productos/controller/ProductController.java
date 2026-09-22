package com.example.productos.controller;

import com.example.productos.model.Product;
import com.example.productos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listar() {
        return productService.listarTodos();
    }

    @GetMapping("/{id}")
    public Product buscar(@PathVariable Long id) {
        return productService.buscarPorId(id);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Map<String, Object>> verificarStock(@PathVariable Long id) {
        Product p = productService.buscarPorId(id);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("disponible", p.getStock() > 0);
        respuesta.put("stockActual", p.getStock());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<Product> crear(@Valid @RequestBody Product producto) {
        Product nuevo = productService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public Product actualizar(@PathVariable Long id, @Valid @RequestBody Product datos) {
        return productService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}