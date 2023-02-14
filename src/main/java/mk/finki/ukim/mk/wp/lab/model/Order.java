package mk.finki.ukim.mk.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shop_orders")
public class Order {

    private String balloonColor;
    private String balloonSize;

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(String balloonColor, String balloonSize, User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
    }

    public Order(String balloonColor, String balloonSize, User user, LocalDateTime dateTime) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
        this.dateTime = dateTime;
    }
}
