package com.carolruo.projeto.services;

import com.carolruo.projeto.domain.ItemOrder;
import com.carolruo.projeto.domain.PaymentSlip;
import com.carolruo.projeto.domain.StoreOrder;
import com.carolruo.projeto.domain.enums.PaymentStatus;
import com.carolruo.projeto.repositories.ItemOrderRepository;
import com.carolruo.projeto.repositories.PaymentRepository;
import com.carolruo.projeto.repositories.ProductRepository;
import com.carolruo.projeto.repositories.StoreOrderRepository;
import com.carolruo.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carolruo.projeto.domain.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class StoreOrderService {

    @Autowired
    private StoreOrderRepository storeOrderRepository;
    @Autowired
    private SlipService slipService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public StoreOrder find(Integer id) {
        Optional<StoreOrder> storeOrder = storeOrderRepository.findById(id);
        return storeOrder.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + StoreOrder.class.getName()
        ));
    }

    @Transactional
    public StoreOrder insert(StoreOrder storeOrder) {
        storeOrder.setId(null); //garantir que ta inserindo um novo pedido
        storeOrder.setInstant(new Date());
        storeOrder.getPayment().setStatus(PaymentStatus.PENDING);
        storeOrder.getPayment().setStoreOrder(storeOrder);
        if (storeOrder.getPayment() instanceof PaymentSlip) {
            PaymentSlip slip = (PaymentSlip) storeOrder.getPayment();
            slipService.fillInSlipDate(slip, storeOrder.getInstant());

        }
        storeOrderRepository.save(storeOrder);
        paymentRepository.save(storeOrder.getPayment());

        for (ItemOrder ip : storeOrder.getItemOrders()) {
            ip.setDiscount(0.0);
            ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
            ip.setStoreOrder(storeOrder);
        }
        itemOrderRepository.saveAll(storeOrder.getItemOrders());
        return storeOrder;
    }
}
