package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.StoreOrder;
import com.carolruo.projeto.services.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody StoreOrder storeOrder) {
        storeOrder = storeOrderService.insert(storeOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(storeOrder.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
