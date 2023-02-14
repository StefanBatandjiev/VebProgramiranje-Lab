package mk.finki.ukim.mk.wp.lab.repository.jpa;

import mk.finki.ukim.mk.wp.lab.model.Order;
import mk.finki.ukim.mk.wp.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser(User user);

    default List<Order> filterOrders(LocalDateTime from, LocalDateTime to){
        return this.findAll().stream().filter(order -> order.getDateTime().isAfter(from) && order.getDateTime().isBefore(to)).collect(Collectors.toList());
    }
}
