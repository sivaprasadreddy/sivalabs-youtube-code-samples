package com.sivalabs.demo.domain;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update Product p set p.price = ?2 where p.code = ?1")
    void updateProductPrice(String code, BigDecimal price);

    Optional<Product> findByCode(String code);
}
