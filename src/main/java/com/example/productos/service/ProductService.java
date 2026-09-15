package com.example.productos.service;

import com.example.productos.exception.ProductNotFoundException;
import com.example.productos.model.Product;
import com.example.productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listarTodos() {
        return productRepository.findAll();
    }

    public Product buscarPorId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product guardar(Product producto) {
        return productRepository.save(producto);
    }

    public Product actualizar(Long id, Product datos) {
        Product producto = buscarPorId(id);
        producto.setNameProduct(datos.getNameProduct());
        producto.setPrice(datos.getPrice());
        producto.setStock(datos.getStock());
        return productRepository.save(producto);
    }

    public void eliminar(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}