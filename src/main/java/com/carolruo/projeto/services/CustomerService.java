package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Category;
import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.dto.CategoryDTO;
import com.carolruo.projeto.dto.CustomerDTO;
import com.carolruo.projeto.repositories.CustomerRepository;
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
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer find(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + Customer.class.getName()
        ));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return customerRepository.findAll(pageRequest);
    }

    public Customer insert(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        Customer customerDB = find(customer.getId());
        updateData(customerDB, customer);
        return customerRepository.save(customerDB);
    }

    private void updateData(Customer customerDB, Customer customer) {
        customerDB.setName(customer.getName());
        customerDB.setEmail(customer.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui entidades relacionadas!");
        }
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null);
    }
}
