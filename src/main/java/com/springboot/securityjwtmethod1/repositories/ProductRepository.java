package com.springboot.securityjwtmethod1.repositories;

import com.springboot.securityjwtmethod1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByProductCode(String productCode);
    boolean existsByNameIgnoreCase(String productCode);


}

