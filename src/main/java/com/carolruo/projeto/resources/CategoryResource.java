package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> listar() {

        Category cat1 = new Category(1, "Informatica");
        Category cat2 = new Category(2, "Escritorio");

        List<Category> list = new ArrayList<>();
        list.add(cat1);
        list.add(cat2);

        return list;
    }
}
