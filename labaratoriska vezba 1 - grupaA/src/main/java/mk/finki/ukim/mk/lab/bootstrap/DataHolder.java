package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();

    @PostConstruct
    public void init() {
        events.add(new Event("Tech Conference", "Annual conference covering latest tech trends and innovations.", 7.8));
        events.add(new Event("Art Exhibition", "Showcasing modern art from local and international artists.", 6.4));
        events.add(new Event("Food Carnival", "A variety of food stalls offering cuisines from around the world.", 8.1));
        events.add(new Event("Film Screening", "An outdoor movie night featuring classic films.", 5.7));
        events.add(new Event("Book Fair", "A gathering of book lovers with book signings and readings.", 6.9));
        events.add(new Event("Science Workshop", "Interactive science sessions for all ages.", 7.3));
        events.add(new Event("Charity Run", "A 5K run to raise funds for local charities.", 7.0));
        events.add(new Event("Dance Performance", "Live dance performance by well-known choreographers.", 6.6));
        events.add(new Event("Historical Tour", "Guided tour of local historical landmarks.", 5.5));
    }

}
