package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImplementation implements LocationService {
    public final LocationRepository locationRepository;

    public LocationServiceImplementation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {return locationRepository.findAll();}

    @Override
    public void delete(Long id) {
        this.locationRepository.delete_location(id);
    }

    @Override
    public Optional<Location> save_location(String name, String address, String capacity, String description) {
        return this.locationRepository.save_new_location(name,address,capacity,description);
    }
}
