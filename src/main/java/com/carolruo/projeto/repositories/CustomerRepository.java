package com.carolruo.projeto.repositories;

import com.carolruo.projeto.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional(readOnly = true) //Mais rapida, menos locking no gerenciamento de transacoes no db
    Customer findByEmail(String email);
}
