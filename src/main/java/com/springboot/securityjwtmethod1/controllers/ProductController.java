package com.springboot.securityjwtmethod1.controllers;

import com.springboot.securityjwtmethod1.dtos.RequestProductDto;
import com.springboot.securityjwtmethod1.exceptions.ProductCodeExistsException;
import com.springboot.securityjwtmethod1.exceptions.ProductNameExistsException;
import com.springboot.securityjwtmethod1.exceptions.ProductNotFoundException;
import com.springboot.securityjwtmethod1.models.Product;
import com.springboot.securityjwtmethod1.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/products")
    public ResponseEntity<Page<Product>> getProducts(@PageableDefault(size = 5) Pageable pageable){
        Page<Product> productsPage= productService.getProducts(pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        try {
            Product product= productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid RequestProductDto requestProductDto){
        Product product= new Product();
        BeanUtils.copyProperties(requestProductDto,product);
        product.setCreationDate(LocalDateTime.now(ZoneId.of("UTC-5")));//local time of Colombia
        try {
            Product addedProduct= productService.addProduct(product);
            return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
        } catch (ProductCodeExistsException | ProductNameExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid RequestProductDto requestProductDto,
                                                @PathVariable Long id){
        Product product= new Product();
        BeanUtils.copyProperties(requestProductDto,product);
        product.setId(id);
        try {
            Product updatedProduct= productService.updateProduct(product);
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (ProductCodeExistsException | ProductNameExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        try {
            Product deletedProduct= productService.deleteProduct(id);
            return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
