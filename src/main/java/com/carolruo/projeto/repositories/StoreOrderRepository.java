package com.carolruo.projeto.repositories;

import com.carolruo.projeto.domain.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOrderRepository extends JpaRepository<StoreOrder, Integer> {
}
