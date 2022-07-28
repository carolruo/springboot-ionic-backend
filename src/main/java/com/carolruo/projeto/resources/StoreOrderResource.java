package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.domain.StoreOrder;
import com.carolruo.projeto.services.CategoryService;
import com.carolruo.projeto.services.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class StoreOrderResource {

    @Autowired
    private StoreOrderService storeOrderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StoreOrder> find(@PathVariable Integer id) {

        StoreOrder storeOrder = storeOrderService.find(id);
        return ResponseEntity.ok().body(storeOrder);
    }
}
