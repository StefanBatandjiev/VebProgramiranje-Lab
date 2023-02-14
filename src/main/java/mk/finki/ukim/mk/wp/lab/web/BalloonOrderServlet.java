package mk.finki.ukim.mk.wp.lab.web;


import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "balloon-order-servlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final OrderService orderService;

    public BalloonOrderServlet(SpringTemplateEngine templateEngine, OrderService orderService) {
        this.templateEngine = templateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context =  new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String size = req.getParameter("size");
        String name = req.getParameter("clientName");
        String address = req.getParameter("clientAddress");
        String date = req.getParameter("date");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date);

        if (name.isEmpty() || address.isEmpty()){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", "Please enter your information");
            resp.setContentType("text/html;charset=UTF-8");
            templateEngine.process("deliveryInfo.html", context, resp.getWriter());
        }

        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("address", address);
        req.getSession().setAttribute("date", dateTime);

        Balloon b = (Balloon) req.getSession().getAttribute("balloon");
        orderService.placeOrder(b.getName(),size,name, address, dateTime);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
