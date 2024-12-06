package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.Tag;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import mk.finki.ukim.mk.lab.service.TagService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final TagService tagService;
    public EventController(EventService eventService, LocationService locationService, TagService tagService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.tagService = tagService;
    }

    @GetMapping()
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        List<Event> eventList = this.eventService.listAll();
        List<Tag> tag_list = this.tagService.get_all_tags();
        model.addAttribute("event_list", eventList);
        model.addAttribute("tags",tag_list);
        return "listEvents";
    }
    @PostMapping("/filter_events_by_tags")
    public String filterList(@RequestParam(required = false) List<Long> tagId,
                             Model model){
        List<Tag> tag_list = this.tagService.get_all_tags();
        if (tagId.isEmpty()){
            model.addAttribute("event_list",this.eventService.listAll());
            model.addAttribute("tags",tag_list);
        }else{
            List<Event> events_filtered ;
            events_filtered = this.eventService.listAll()
                    .stream()
                    .filter(event -> event.getTags_of_event().stream()
                            .anyMatch(tag -> tagId.contains(tag.getId())))
                    .toList();
            model.addAttribute("event_list",events_filtered);
            model.addAttribute("tags",tag_list);
        }
       return "listEvents";

    }
    @PostMapping("/filter_events")
    public String filterList(@RequestParam(required = false) String txt,
                             @RequestParam(required = false) Double rating,
                             Model model){
        String t = (txt != null) ? txt.trim() : "";
        Double r = (rating != null) ? rating : 0.0;

        if(!t.isEmpty() && !r.isNaN()){
            List<Event> events_filtered = this.eventService.listAll()
                    .stream()
                    .filter(k -> k.getName().contains(t) && k.getPopularityScore() >= r)
                    .toList();
            model.addAttribute("event_list",events_filtered);
        }else{
            model.addAttribute("event_list", this.eventService.listAll());
        }
        return "listEvents";

    }
    @GetMapping("/add-form")
    public String addNewEvent(Model model){
        List<Location> locations = locationService.findAll();
        model.addAttribute("all_locations", locations);
        return "add-event-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteEventFromList(@PathVariable Long id){
        this.eventService.delete(id);
        return "redirect:/events";
    }
    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long id, // Optional for new events
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        eventService.save_event(id, name, description, popularityScore, locationId);
        return "redirect:/events"; // Redirect to the events list
    }

    @PostMapping("/book_event")
    public String BookEvent(@RequestParam String selectedEvent,
                            @RequestParam Integer numTickets,
                            @RequestParam String username, HttpSession session){
        session.setAttribute("username",username);
        session.setAttribute("numTickets",numTickets);
        session.setAttribute("selectedEvent",selectedEvent);
        return "redirect:/eventBooking";
    }
    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id,Model model)
    {
        Event event_to_edit = eventService.findEvent(id).get();
        List<Location> locations = locationService.findAll();
        model.addAttribute("all_locations", locations);
        model.addAttribute("event",event_to_edit);
        return "add-event-form";
    }

}
