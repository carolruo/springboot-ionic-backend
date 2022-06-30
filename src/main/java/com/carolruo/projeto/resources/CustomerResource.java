package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class CustomerResource {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> listar(@PathVariable Integer id) {

        Customer customer = customerService.find(id);
        return ResponseEntity.ok().body(customer);
    }
}
