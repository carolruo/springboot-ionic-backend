package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.domain.Product;
import com.carolruo.projeto.repositories.CategoryRepository;
import com.carolruo.projeto.repositories.ProductRepository;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()
        ));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);
        return repository.search(name, categories, pageRequest);

    }

    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado. Id: " + id));
    }
}
