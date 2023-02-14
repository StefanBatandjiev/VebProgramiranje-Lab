package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.Order;
import mk.finki.ukim.mk.wp.lab.model.User;
import mk.finki.ukim.mk.wp.lab.model.exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.wp.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void placeOrder(String balloonColor, String size, String clientName, String address, LocalDateTime dateTime) {
        User user = userRepository.findByUsername(clientName).orElseThrow(WrongCredentialsException::new);
        orderRepository.save(new Order(balloonColor, size, user, dateTime));
    }

    @Override
    public List<Order> searchByClient(String name, String address) {
        User user = userRepository.findAllByUsernameLike(name).stream().findFirst().orElseThrow(WrongCredentialsException::new);
        return this.orderRepository.findAllByUser(user);
    }

    @Override
    public List<Order> filterOrders(LocalDateTime from, LocalDateTime to) {
        return orderRepository.filterOrders(from, to);
    }
}
