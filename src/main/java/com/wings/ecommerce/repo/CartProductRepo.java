package com.wings.ecommerce.repo;

import com.wings.ecommerce.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
    Optional<CartProduct> findByCartUserIdAndProductProductId(Integer userId, Integer productId);
    @Transactional
    void deleteByCartUserUserIdAndProductProductId(Integer userId, Integer productId);
}
