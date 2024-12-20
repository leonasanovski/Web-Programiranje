package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    @GetMapping
    public String confirmReservation(HttpServletRequest request, Model model) {
        model.addAttribute("ip_address", request.getRemoteAddr());
        return "bookingConfirmation";
    }
}
