package mk.finki.ukim.mk.wp.lab.bootstrap;

import lombok.Getter;
import lombok.Setter;
import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.model.Manufacturer;
import mk.finki.ukim.mk.wp.lab.model.Order;
import mk.finki.ukim.mk.wp.lab.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    @PostConstruct
    public void init(){

        Manufacturer manufacturer1 = new Manufacturer("BalloonFactory1","USA","Address");
        Manufacturer manufacturer2 = new Manufacturer("BalloonFactory2","USA","Address");
        Manufacturer manufacturer3 = new Manufacturer("BalloonFactory3","USA","Address");
        Manufacturer manufacturer4 = new Manufacturer("BalloonFactory4","USA","Address");
        Manufacturer manufacturer5 = new Manufacturer("BalloonFactory5","USA","Address");
        manufacturers.add(manufacturer1);
        manufacturers.add(manufacturer2);
        manufacturers.add(manufacturer3);
        manufacturers.add(manufacturer4);
        manufacturers.add(manufacturer5);


        balloons.add(new Balloon("Balloon1", "Red Balloon",manufacturer1));
        balloons.add(new Balloon("Balloon2", "Green Balloon",manufacturer2));
        balloons.add(new Balloon("Balloon3", "Purple Balloon",manufacturer3));
        balloons.add(new Balloon("Balloon4", "Yellow Balloon",manufacturer4));
        balloons.add(new Balloon("Balloon5", "White Balloon",manufacturer5));
        balloons.add(new Balloon("Balloon6", "Black Balloon",manufacturer1));
        balloons.add(new Balloon("Balloon7", "Blue Balloon",manufacturer2));
        balloons.add(new Balloon("Balloon8", "Pink Balloon",manufacturer3));
        balloons.add(new Balloon("Balloon9", "Cyan Balloon",manufacturer4));
        balloons.add(new Balloon("Balloon10", "Magenta Balloon",manufacturer5));

        users.add(new User("user1", "user1"));
        users.add(new User("user2", "user2"));
    }
}
