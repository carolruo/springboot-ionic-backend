package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.dto.CategoryDTO;
import com.carolruo.projeto.repositories.CategoryRepository;
import com.carolruo.projeto.services.exceptions.DataIntegrityException;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> category = repo.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()
        ));
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category insert(Category category) {
        category.setId(null);
        return repo.save(category);
    }

    public Category update(Category category) {
        Category categoryDB = find(category.getId());
        updateData(categoryDB, category);
        return repo.save(category);
    }

    private void updateData(Category categoryDB, Category category) {
        categoryDB.setName(category.getName());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
