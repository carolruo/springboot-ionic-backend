package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
