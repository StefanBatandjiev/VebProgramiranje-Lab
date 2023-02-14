package mk.finki.ukim.mk.wp.lab.repository.impl;

import mk.finki.ukim.mk.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.wp.lab.model.Order;
import mk.finki.ukim.mk.wp.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryOrderRepository {
    public List<Order> listByNameAndAddress(String clientName, String clientAddress) {
        return DataHolder.orders.stream()
                .filter(o -> o.getUser().getUsername().equals(clientName))
                .collect(Collectors.toList());
    }

    public Order addOrder(String balloonColor, User user) {
        Order order = new Order(balloonColor, balloonColor + " balloon", user);
        DataHolder.orders.add(order);
        return order;
    }
}
