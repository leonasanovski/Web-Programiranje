package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Event {
    private String name;
    private String description;
    private Double popularityScore;
    private Long id;
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
}
