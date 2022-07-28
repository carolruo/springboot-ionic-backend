package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.StoreOrder;
import com.carolruo.projeto.repositories.StoreOrderRepository;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreOrderService {

    @Autowired
    StoreOrderRepository storeOrderRepository;

    public StoreOrder find(Integer id) {
        Optional<StoreOrder> storeOrder = storeOrderRepository.findById(id);
        return storeOrder.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + StoreOrder.class.getName()
        ));
    }
}
