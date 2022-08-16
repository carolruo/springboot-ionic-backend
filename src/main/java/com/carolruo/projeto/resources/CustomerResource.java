package com.carolruo.projeto.resources;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.dto.CategoryDTO;
import com.carolruo.projeto.dto.CustomerDTO;
import com.carolruo.projeto.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class CustomerResource {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> find(@PathVariable Integer id) {

        Customer customer = customerService.find(id);
        return ResponseEntity.ok().body(customer);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<Customer> customers = customerService.findAll();
        //COnverter uma lista em outra lista
        List<CustomerDTO> customerDTOS = customers.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDTOS);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.fromDTO(customerDTO);
        customer = customerService.insert(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customer.getId()).toUri(); //chamada que pega a URI do recurso inserido
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        Customer customer = customerService.fromDTO(customerDTO);
        customer.setId(id);
        customer = customerService.update(customer);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Customer> customers = customerService.findPage(page, linesPerPage, orderBy, direction);
        Page<CustomerDTO> customerDTOS = customers.map(obj -> new CustomerDTO(obj)); //O Page já é Java8 Compliance, aceita direto
        return ResponseEntity.ok().body(customerDTOS);
    }
}
