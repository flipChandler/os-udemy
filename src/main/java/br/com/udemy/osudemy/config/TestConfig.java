package br.com.udemy.osudemy.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.udemy.osudemy.entities.Category;
import br.com.udemy.osudemy.entities.Order;
import br.com.udemy.osudemy.entities.OrderItem;
import br.com.udemy.osudemy.entities.Payment;
import br.com.udemy.osudemy.entities.Product;
import br.com.udemy.osudemy.entities.User;
import br.com.udemy.osudemy.entities.enums.OrderStatus;
import br.com.udemy.osudemy.repositories.CategoryRepository;
import br.com.udemy.osudemy.repositories.OrderItemRepository;
import br.com.udemy.osudemy.repositories.OrderRepository;
import br.com.udemy.osudemy.repositories.ProductRepository;
import br.com.udemy.osudemy.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	// SEED DO BANCO DE DADOS
	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet", 90.5, "");
		Product product2 = new Product(null, "The Matrix", "Null eu imperdiet purus. Maecenas ante", 2190.0, "");
		Product product3 = new Product(null, "MacBook Pro", "Nam eleifen maximus tortor, at mollis", 1250.0, "");
		Product product4 = new Product(null, "Java for Dummies", "Donec aliquet odio ac rhoncus", 1200.0, "");
		Product product5 = new Product(null, "American Beauty", "Cras fringilla convallis sem vel faucibus", 100.99, "");
		
		product1.getCategories().add(category2);
		product2.getCategories().add(category1);
		product2.getCategories().add(category3); 
		product3.getCategories().add(category3);
		product4.getCategories().add(category3);
		product5.getCategories().add(category2);
		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "9888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "97777777", "123456");
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), user1, OrderStatus.PAID);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), user2, OrderStatus.WAITING_PAYMENT);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), user1, OrderStatus.WAITING_PAYMENT);		
		orderRepository.saveAll(Arrays.asList(order1,order2,order3));
		
		OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
		OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
		
		// PAGAMENTO FEITO 2 HORAS DEPOIS DE EMITIDO O PEDIDO
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), order1);
		order1.setPayment(pay1);
		
		/*
		 *  One-to-one, a entidade não-dependente que atualiza o seu registro 
			e o JPA se encarrega de gravar payment em sua tabela
		 */
		orderRepository.save(order1);
		
		
	}
	
	
	
}
