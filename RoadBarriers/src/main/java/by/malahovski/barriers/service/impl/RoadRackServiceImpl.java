package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import by.malahovski.barriers.repository.RoadRackRepository;
import by.malahovski.barriers.service.RoadRackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadRackServiceImpl implements RoadRackService {

    private final RoadRackRepository roadRackRepository;

    @Autowired
    public RoadRackServiceImpl(RoadRackRepository roadRackRepository) {
        this.roadRackRepository = roadRackRepository;
    }

    @Override
    public Boolean deleteRackById(Long id) {
        if (roadRackRepository.existsById(id)) {
            roadRackRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveRack(Long id, RoadRack roadRack) {
        if (roadRackRepository.findById(id).isPresent()) {
            RoadRack roadRack1 = roadRackRepository.findById(id).get();
            roadRack1.setId(roadRack.getId());
            roadRack1.setName(roadRack.getName());
            roadRack1.setRoadRackProfile(roadRack.getRoadRackProfile());
            roadRack1.setHeight(roadRack.getHeight());
            roadRack1.setThickness(roadRack.getThickness());
            roadRack1.setWeight(roadRack.getWeight());
            roadRack1.setPrice(roadRack.getPrice());
            roadRackRepository.saveAndFlush(roadRack);
        } else {
            roadRackRepository.save(roadRack);
        }
    }

    @Override
    public RoadRack findById(Long id) {
        return null;
    }

    @Override
    public RoadRack findByName(String name) {
        return roadRackRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Rack  with name " + name + " is not found"));
    }

    @Override
    public List<RoadRack> findByRoadRackProfile(String profile) {
        return roadRackRepository.findByRoadRackProfile(profile)
                .orElseThrow(() -> new RuntimeException("Rack  with name " + profile + " is not found"));
    }

    @Override
    public List<RoadRack> findByHeight(Double height) {
        return roadRackRepository.findByHeight(height)
                .orElseThrow(() -> new RuntimeException("Rack with height " + height + " is not found"));
    }

    @Override
    public List<RoadRack> findByThickness(Double thickness) {
        return roadRackRepository.findByThickness(thickness)
                .orElseThrow(() -> new RuntimeException("Rack with thickness " + thickness + " is not found"));
    }

    @Override
    public List<RoadRack> findByWeight(Double weight) {
        return roadRackRepository.findByWeight(weight)
                .orElseThrow(() -> new RuntimeException("Console with weight " + weight + " is not found"));
    }

    @Override
    public List<RoadRack> findByPrice(Double price) {
        return roadRackRepository.findByPrice(price)
                .orElseThrow(() -> new RuntimeException("Console with price " + price + " is not found"));
    }
}
