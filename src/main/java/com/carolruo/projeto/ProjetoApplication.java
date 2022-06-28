package com.carolruo.projeto;

import com.carolruo.projeto.domain.*;
import com.carolruo.projeto.domain.enums.CustomerType;
import com.carolruo.projeto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
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

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlandia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

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
	}
}
