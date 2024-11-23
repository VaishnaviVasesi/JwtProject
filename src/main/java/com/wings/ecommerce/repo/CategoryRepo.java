package com.wings.ecommerce.repo;


import com.wings.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String category);
}
