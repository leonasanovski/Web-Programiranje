package mk.finki.ukim.mk.lab.web.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements HttpSessionListener {
    private static int active_session_counter = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        synchronized (this){
            active_session_counter++;
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        synchronized (this){
            active_session_counter--;
        }
    }

    public static int getActiveSessions() {
        return active_session_counter;
    }
}
