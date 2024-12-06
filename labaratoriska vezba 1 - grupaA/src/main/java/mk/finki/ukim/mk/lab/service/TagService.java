package mk.finki.ukim.mk.lab.service;

import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> get_all_tags();
}
