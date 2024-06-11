package com.ifruitcommerce.ifruit.config;

import com.ifruitcommerce.ifruit.entities.*;
import com.ifruitcommerce.ifruit.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Matheus", "matheus.avila@exemplo.com", "458742847238", "mat1234");
        User u2 = new User(null, "Joselino", "joselino@exemplo.com", "458742847234", "12345ADMIN");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Product banana = new Product(null,"Banana","banana da terra",2.65,"sadsadfas");
        Product manga = new Product(null,"Manga","manga verde",2.80,"sadsahdasu");

        productRepository.saveAll(Arrays.asList(banana, manga));

        Category fruit = new Category(null, "Fruta");

        categoryRepository.saveAll(Arrays.asList(fruit));

        banana.setCategories(Arrays.asList(fruit));
        manga.setCategories(Arrays.asList(fruit));

        productRepository.saveAll(Arrays.asList(banana, manga));

        Order o1 = new Order(null, new Date(), OrderStatus.WAITING_PAYMENT, u1);
        Order o2 = new Order(null, new Date(), OrderStatus.PAID, u2);

        orderRepository.saveAll(Arrays.asList(o1, o2));

        OrderItem oi1 = new OrderItem(null, 2, banana.getPrice(), o1, banana);
        OrderItem oi2 = new OrderItem(null, 1, manga.getPrice(), o1, manga);
        OrderItem oi3 = new OrderItem(null, 3, banana.getPrice(), o2, banana);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

        Payment pay1 = new Payment(null, new Date(), o2);
        o2.setPayment(pay1);

        orderRepository.save(o2);
    }
}
