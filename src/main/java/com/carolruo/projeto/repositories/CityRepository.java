package com.carolruo.projeto.repositories;

import com.carolruo.projeto.domain.CustomerCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CustomerCity, Integer> {
}
