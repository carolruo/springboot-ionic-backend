package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.dto.CategoryDTO;
import com.carolruo.projeto.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        Category category = categoryService.find(id);
        return ResponseEntity.ok().body(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categories = categoryService.findAll();
        //COnverter uma lista em outra lista
        List<CategoryDTO> categoryDTOS = categories.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryDTOS);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Category category) {
        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri(); //chamada que pega a URI do recurso inserido
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {
        category.setId(id);
        category = categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Category> categories = categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> categoryDTOS = categories.map(obj -> new CategoryDTO(obj)); //O Page já é Java8 Compliance, aceita direto
        return ResponseEntity.ok().body(categoryDTOS);
    }
}
