package com.carolruo.projeto.repositories;

import com.carolruo.projeto.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
