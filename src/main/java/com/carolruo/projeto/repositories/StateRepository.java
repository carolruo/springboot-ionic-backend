package com.carolruo.projeto.repositories;

import com.carolruo.projeto.domain.CustomerState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<CustomerState, Integer> {
}
