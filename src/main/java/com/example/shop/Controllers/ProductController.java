package com.example.shop.Controllers;

import com.example.shop.Models.Product;
import com.example.shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public List<Product> getProducts(){
        return productRepository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return productRepository.getProductById(id);
    }

    @PostMapping("")
    public int addProduct(@RequestBody LinkedList<Product> prods){
        return productRepository.addProduct(prods);
    }

    @DeleteMapping("{id}")
    public int deleteProductById(@PathVariable("id") int id){
        return productRepository.deleteProductById(id);
    }

    @PutMapping("/{id}")// V
    public int updateProduct(@PathVariable("id") int id, @RequestBody Product updateProduct){
        Product prod = productRepository.getProductById(id);
        if(prod != null) {
            updateProduct.setId(id);
            productRepository.updateProduct(updateProduct);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}") // V
    public int patchProduct(@PathVariable("id") int id, @RequestBody Product updateProduct){
        Product prod = productRepository.getProductById(id);
        if(prod != null) {
            if (updateProduct.getName() == null) {
                updateProduct.setName(prod.getName());
            }
            if (updateProduct.getCategory() == 0) {
                updateProduct.setCategory(prod.getCategory());
            }
            if (updateProduct.getDescription() == null) {
                updateProduct.setDescription(prod.getDescription());
            }
            if (updateProduct.getPrice() == 0) {
                updateProduct.setPrice(prod.getPrice());
            }
            if (updateProduct.getImage() == null) {
                updateProduct.setImage(prod.getImage());
            }
            updateProduct.setId(id);
            productRepository.patchProduct(updateProduct);
            return 1;
        } else {
            return -1;
        }
    }
}
