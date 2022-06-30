package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.repositories.CustomerRepository;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer find(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + Customer.class.getName()
        ));
    }
}
