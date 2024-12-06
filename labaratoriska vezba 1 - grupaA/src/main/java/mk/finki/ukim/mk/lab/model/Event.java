package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double popularityScore;
    @ManyToOne
    private Location location;
    @ManyToMany
    private List<Tag> tags_of_event;

    public Event(String name, String description, double popularityScore, Location location,List<Tag> tags_of_event) {
//        this.id = (long) (Math.random() * 1000);
        this.tags_of_event = tags_of_event;
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
    public Event(String name, String description, double popularityScore, Location location) {
//        this.id = (long) (Math.random() * 1000);
        this.tags_of_event = tags_of_event;
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
}
