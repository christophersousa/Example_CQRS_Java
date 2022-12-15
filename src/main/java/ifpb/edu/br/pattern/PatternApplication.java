package ifpb.edu.br.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ifpb.edu.br.pattern.model.Product;
import ifpb.edu.br.pattern.model.User;
import ifpb.edu.br.pattern.repository.ProductRepository;
import ifpb.edu.br.pattern.repository.UserRepository;

@SpringBootApplication
public class PatternApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// User user = new User();
		// user.setFirstname("Christopher");
		// user.setLastname("Silva");
		// user.setState("PB");

		// Product product = new Product();
		// product.setPrice(8000);
		// product.setName("Iphone 13 Pro max");

		// User user2 = new User();
		// user2.setFirstname("João");
		// user2.setLastname("Jorge");
		// user2.setState("PB");

		// Product product2 = new Product();
		// product2.setPrice(2500);
		// product2.setName("Notebook Dell");

		// userRepository.save(user);
		// userRepository.save(user2);

		// productRepository.save(product);
		// productRepository.save(product2);

		// User user3 = new User();
		// user3.setFirstname("Gustavo");
		// user3.setLastname("Wagner");
		// user3.setState("RJ");

		// Product product3 = new Product();
		// product3.setPrice(2500);
		// product3.setName("Xiaomi note 12");

		// User user4 = new User();
		// user4.setFirstname("Maycon");
		// user4.setLastname("Andrades");
		// user4.setState("MG");

		// Product product4 = new Product();
		// product4.setPrice(6600);
		// product4.setName("Macboock Air Apple");

		// userRepository.save(user3);
		// userRepository.save(user4);

		// productRepository.save(product3);
		// productRepository.save(product4);
	}
}
