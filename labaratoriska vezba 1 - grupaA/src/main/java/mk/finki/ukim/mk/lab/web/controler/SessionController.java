package mk.finki.ukim.mk.lab.web.controler;

import mk.finki.ukim.mk.lab.web.listener.SessionListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/active-sessions")
    public int getActivatedSessions(){
        return SessionListener.getActiveSessions();
    }
}
