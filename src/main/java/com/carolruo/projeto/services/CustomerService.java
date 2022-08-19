package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.Address;
import com.carolruo.projeto.domain.Customer;
import com.carolruo.projeto.domain.CustomerCity;
import com.carolruo.projeto.domain.enums.CustomerType;
import com.carolruo.projeto.dto.CustomerDTO;
import com.carolruo.projeto.dto.CustomerNewDTO;
import com.carolruo.projeto.repositories.AddressRepository;
import com.carolruo.projeto.repositories.CustomerRepository;
import com.carolruo.projeto.services.exceptions.DataIntegrityException;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;
    @Autowired
    private AddressRepository addressRepository;

    public Customer find(Integer id) {
        Optional<Customer> customer = repo.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + Customer.class.getName()
        ));
    }

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public Customer insert(Customer customer) {
        customer.setId(null);
        customer = repo.save(customer);
        addressRepository.saveAll(customer.getAddresses());
        return customer;
    }

    public Customer update(Customer customer) {
        Customer customerDB = find(customer.getId());
        updateData(customerDB, customer);
        return repo.save(customerDB);
    }

    private void updateData(Customer customerDB, Customer customer) {
        customerDB.setName(customer.getName());
        customerDB.setEmail(customer.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos!");
        }
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null);
    }

    public Customer fromDTO(CustomerNewDTO customerDTO) {
        Customer customer = new Customer(null, customerDTO.getName(), customerDTO.getEmail(), customerDTO.getCpfOrCnpj(), CustomerType.toEnum(customerDTO.getCustomerTypeId()));
        CustomerCity city = new CustomerCity(customerDTO.getCityId(), null, null);
        Address address = new Address(null,
                customerDTO.getStreet(),
                customerDTO.getNumber(),
                customerDTO.getAddressContinued(),
                customerDTO.getNeighborhood(),
                customerDTO.getZipCode(),
                customer,
                city);
        customer.getAddresses().add(address);
        customer.getContactNumbers().add(customerDTO.getContactNumber1());
        if (customerDTO.getContactNumber2()!=null) {
            customer.getContactNumbers().add(customerDTO.getContactNumber2());

        }
        if (customerDTO.getContactNumber3()!=null) {
            customer.getContactNumbers().add(customerDTO.getContactNumber3());

        }
        return customer;
    }
}
