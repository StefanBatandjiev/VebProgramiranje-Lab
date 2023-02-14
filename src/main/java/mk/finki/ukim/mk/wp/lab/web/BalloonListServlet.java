package mk.finki.ukim.mk.wp.lab.web;

import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-servlet",urlPatterns = "")
public class BalloonListServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("balloons",this.balloonService.listAll());
        resp.setContentType("text/html;charset=UTF-8");
        springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String desc = req.getParameter("color");

        if (desc == null){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("balloons", balloonService.listAll());
            context.setVariable("hasError", true );
            context.setVariable("error", "Please select a balloon.");
            resp.setContentType("text/html;charset=UTF-8");
            springTemplateEngine.process("listBalloons.html", context, resp.getWriter());
            resp.sendRedirect("/balloon");
        }
        String name = req.getParameter("color").split(" ")[0];

        Balloon b = new Balloon(name, desc);
        req.getSession().setAttribute("balloon", b);
        resp.sendRedirect("/selectBalloon");
    }
}
