package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Tag;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TagRepository;
import mk.finki.ukim.mk.lab.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImplementation implements TagService {
    public final TagRepository tagRepository;

    public TagServiceImplementation(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> get_all_tags() {
        return tagRepository.findAll();
    }
}
