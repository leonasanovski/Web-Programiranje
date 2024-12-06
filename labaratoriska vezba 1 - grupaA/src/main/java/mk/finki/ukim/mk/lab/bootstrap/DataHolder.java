package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.Tag;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TagRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();
    public static List<Tag> tags = new ArrayList<>();
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;

    public DataHolder(LocationRepository locationRepository, EventRepository eventRepository, TagRepository tagRepository) {
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
        this.tagRepository = tagRepository;
    }

    @PostConstruct
    public void init() {
        Tag tag1 = new Tag("Tag1");
        Tag tag2 = new Tag("Tag2");
        Tag tag3 = new Tag("Tag3");
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        List<Tag> tags_1 = new ArrayList<>();
        tags_1.add(tag1);
        tags_1.add(tag2);
        List<Tag> tags_2 = new ArrayList<>();
        tags_2.add(tag2);
        tags_2.add(tag3);
        tagRepository.saveAll(tags);

        locations.add(new Location("Capitol Mall","Jane Sandanski","1000 lugje","Mall za shopping"));
        locations.add(new Location("Kej na vardar","Reka Vardar", "inf","Za uzivanje"));
        locations.add(new Location("FINKI kampus","Ul:Rugjer Boskovikj","2000","Studeiranje"));
        locations.add(new Location("Krug Kafe","Jane Sandanski","40","Zabava so prijatelite"));
        locationRepository.saveAll(locations);

        events.add(new Event("Tech Conference", "Annual conference covering latest tech trends and innovations.", 7.8,locations.get(2),tags_1));
        events.add(new Event("Art Exhibition", "Showcasing modern art from local and international artists.", 6.4,locations.get(3),tags_1));
        events.add(new Event("Food Carnival", "A variety of food stalls offering cuisines from around the world.", 8.1,locations.get(0),tags_2));
        events.add(new Event("Film Screening", "An outdoor movie night featuring classic films.", 5.7,locations.get(0),tags_2));
        eventRepository.saveAll(events);
    }

}
