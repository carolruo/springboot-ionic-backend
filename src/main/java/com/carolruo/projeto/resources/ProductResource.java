package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.domain.Product;
import com.carolruo.projeto.domain.StoreOrder;
import com.carolruo.projeto.dto.CategoryDTO;
import com.carolruo.projeto.dto.ProductDTO;
import com.carolruo.projeto.resources.utils.URL;
import com.carolruo.projeto.services.ProductService;
import com.carolruo.projeto.services.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id) {

        Product product = service.find(id);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> productDTOS = list.map(obj -> new ProductDTO(obj)); //O Page já é Java8 Compliance, aceita direto
        return ResponseEntity.ok().body(productDTOS);
    }
}
