package com.carolruo.projeto;

import com.carolruo.projeto.domain.*;
import com.carolruo.projeto.domain.enums.CustomerType;
import com.carolruo.projeto.domain.enums.PaymentStatus;
import com.carolruo.projeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private StoreOrderRepository storeOrderRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", new BigDecimal("2000.00"));
		Product p2 = new Product(null, "Impressora", new BigDecimal("800.00"));
		Product p3 = new Product(null, "Mouse", new BigDecimal("80.00"));

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().add(cat1);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		CustomerState est1 = new CustomerState(null, "Minas Gerais");
		CustomerState est2 = new CustomerState(null, "São Paulo");

		CustomerCity c1 = new CustomerCity(null, "Uberlandia", est1);
		CustomerCity c2 = new CustomerCity(null, "São Paulo", est2);
		CustomerCity c3 = new CustomerCity(null, "Campinas", est2);

		est1.getCities().add(c1);
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer cli1 = new Customer(null, "Maria Silva", "maria@gmail.com", "34534545676", CustomerType.INDIVIDUAL_PERSON);
		cli1.getContactNumbers().addAll(Arrays.asList("1232423", "32454564"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 50", "Jardim", "24324", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "32432", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		customerRepository.save(cli1);
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StoreOrder ped1 = new StoreOrder(null, sdf.parse("30/12/2012 14:00"), cli1, e1);
		StoreOrder ped2 = new StoreOrder(null, sdf.parse("12/11/2007 08:00"), cli1, e2);
		Payment pagto1 = new PaymentCard(null, PaymentStatus.DISCHARGED, ped1, 6);
		ped1.setPayment(pagto1);
		Payment pagto2 = new PaymentSlip(null, PaymentStatus.PENDING, ped2, sdf.parse("12/02/1992 00:00"), null);
		cli1.getStoreOrders().addAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1,pagto2));
		storeOrderRepository.saveAll(Arrays.asList(ped1, ped2));

		ItemOrder ip1 = new ItemOrder(ped1, p1, 0.00, 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ped1, p3, 0.00, 2, 80.00);
		ItemOrder ip3 = new ItemOrder(ped2, p2, 100.00, 1, 800.00);
		ped1.getItemOrders().addAll(Arrays.asList(ip1, ip2));
		ped2.getItemOrders().addAll(Arrays.asList(ip3));
		p1.getItemOrders().addAll(Arrays.asList(ip1));
		p2.getItemOrders().addAll(Arrays.asList(ip3));
		p3.getItemOrders().addAll(Arrays.asList(ip2));
		itemOrderRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
