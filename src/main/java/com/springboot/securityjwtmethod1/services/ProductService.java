package com.springboot.securityjwtmethod1.services;

import com.springboot.securityjwtmethod1.exceptions.ProductCodeExistsException;
import com.springboot.securityjwtmethod1.exceptions.ProductNameExistsException;
import com.springboot.securityjwtmethod1.exceptions.ProductNotFoundException;
import com.springboot.securityjwtmethod1.models.Product;
import com.springboot.securityjwtmethod1.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id: "+id+" not found!"));
    }

    @Transactional
    public Product addProduct(Product product) throws ProductCodeExistsException, ProductNameExistsException {
        if(productRepository.existsByProductCode(product.getProductCode())){
            throw new ProductCodeExistsException("Product Code: "+product.getProductCode()+" already exists!");
        }
        if(productRepository.existsByNameIgnoreCase(product.getName())){
            throw new ProductNameExistsException("Product with name: "+product.getName()+" already exists!");
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) throws ProductNotFoundException,ProductCodeExistsException,ProductNameExistsException {
        Product recoveredProduct= productRepository.findById(product.getId())
                .orElseThrow(()-> new ProductNotFoundException("Product with id: "+product.getId()+" not found!"));

        if(productCodeBelongsToAnotherExistingInstance(product.getProductCode(),recoveredProduct)){
            throw new ProductCodeExistsException("Product Code: "+product.getProductCode()+" already exists!");
        }
        if(nameBelongsToAnotherExistingInstance(product.getName(),recoveredProduct)){
            throw new ProductNameExistsException("Product with name: "+product.getName()+" already exists!");
        }

        BeanUtils.copyProperties(product,recoveredProduct,"date");
        return recoveredProduct;
    }

    private boolean productCodeBelongsToAnotherExistingInstance(String productCode,Product product) {
        return productRepository.existsByProductCode(productCode) && !productCode.equals(product.getProductCode());
    }

    private boolean nameBelongsToAnotherExistingInstance(String name,Product product) {
        return productRepository.existsByNameIgnoreCase(name) && !name.equalsIgnoreCase(product.getName());
    }

    @Transactional
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Product product= productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with id: "+id+" not found!"));
        productRepository.delete(product);
        return product;
    }

}
