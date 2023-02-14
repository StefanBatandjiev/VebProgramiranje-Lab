package mk.finki.ukim.mk.wp.lab.web.controller;

import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.model.Order;
import mk.finki.ukim.mk.wp.lab.service.BalloonService;
import mk.finki.ukim.mk.wp.lab.service.ManufacturerService;
import mk.finki.ukim.mk.wp.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("balloons", this.balloonService.listAll());
        return "listBalloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            model.addAttribute("balloon", balloon);
            model.addAttribute("manufacturers", this.manufacturerService.findAll());
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonWithThisIdNotFound";
    }

    @PostMapping("/save")
    public String saveBalloonForNextPage(@RequestParam Long id, HttpServletRequest req) {
        Balloon b = null;
        if(this.balloonService.findById(id).isPresent())
            b = this.balloonService.findById(id).get();
        req.getSession().setAttribute("balloon", b);
        return "selectBalloonSize";
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        return "add-balloon";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name, @RequestParam String desc,@RequestParam Long manufacturer) {
        this.balloonService.saveBalloon(name, desc, manufacturer);
        return "redirect:/balloons";
    }

    @PostMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/orders")
    public String showOrders(HttpServletRequest request, Model model) {
        String name = request.getParameter("clientName");
        String address = request.getParameter("clientAddress");
        List<Order> orders = this.orderService.searchByClient(name, address);
        model.addAttribute("orders", orders);
        return "userOrders";
    }
}
