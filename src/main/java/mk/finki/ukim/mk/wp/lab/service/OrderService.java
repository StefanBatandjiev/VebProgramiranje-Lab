package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void placeOrder(String balloonColor, String size, String clientName, String address, LocalDateTime dateTime);

    List<Order> searchByClient(String name, String address);

    List<Order> filterOrders(LocalDateTime from, LocalDateTime to);
}
