package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.web.listener.SessionListener;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "event_service")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iwe = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                        .buildExchange(req,resp);
        WebContext wc = new WebContext(iwe);
        HttpSession session = req.getSession();
        session.setAttribute("sessionStatus","active");
        String text = req.getParameter("txt") != null && !req.getParameter("txt").isEmpty()
                ? req.getParameter("txt")
                : "o";

        double rating = req.getParameter("rating") != null
                        ? Double.parseDouble(req.getParameter("rating"))
                        : 0.0;
        wc.setVariable("event_list",eventService.listAll()
                .stream()
                .filter(event -> event.getName().contains(text) && event.getPopularityScore() >= rating)
                .toList());
        wc.setVariable("activeSessions", SessionListener.getActiveSessions());
        templateEngine.process("listEvents.html",wc,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("selected_event",req.getParameter("selectedEvent"));
        req.getSession().setAttribute("num_of_tickets",req.getParameter("numTickets"));
        req.getSession().setAttribute("user_name",req.getParameter("username"));

        resp.sendRedirect("/eventBooking");
    }
}
