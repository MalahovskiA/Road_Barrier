package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.repository.RoadBeamRepository;
import by.malahovski.barriers.service.RoadBeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadBeamServiceImpl implements RoadBeamService {

    private final RoadBeamRepository roadBeamRepository;

    @Autowired
    public RoadBeamServiceImpl(RoadBeamRepository roadBeamRepository) {
        this.roadBeamRepository = roadBeamRepository;
    }

    @Override
    public Boolean deleteBeamById(Long id) {
        if (roadBeamRepository.existsById(id)) {
            roadBeamRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveBeam(Long id, RoadBeam beam) {
        if (roadBeamRepository.findById(id).isPresent()) {
            RoadBeam beam1 = roadBeamRepository.findById(id).get();
            beam1.setId(beam.getId());
            beam1.setName(beam.getName());
            beam1.setLength(beam.getLength());
            beam1.setHolePitch(beam.getHolePitch());
            beam1.setThickness(beam.getThickness());
            beam1.setWeight(beam.getWeight());
            beam1.setPrice(beam.getPrice());
            roadBeamRepository.saveAndFlush(beam);
        } else {
            roadBeamRepository.save(beam);
        }
    }


    @Override
    public RoadBeam getByName(String name) {
        return roadBeamRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Beam with name " + name + " is not found"));
    }

    @Override
    public List<RoadBeam> getByLength(Integer length) {
        return roadBeamRepository.findByLength(length)
                .orElseThrow(() -> new RuntimeException("Beam with length " + length + " is not found"));
    }

    @Override
    public List<RoadBeam> getByHolePitch(Integer pitch) {
        return roadBeamRepository.findByHolePitch(pitch)
                .orElseThrow(() -> new RuntimeException("Beam with hole pitch " + pitch + " is not found"));
    }

    @Override
    public List<RoadBeam> getByThickness(Double thickness) {
        return roadBeamRepository.findByThickness(thickness)
                .orElseThrow(() -> new RuntimeException("Beam with thickness " + thickness + " is not found"));
    }

    @Override
    public List<RoadBeam> getByWeight(Double weight) {
        return roadBeamRepository.findByWeight(weight)
                .orElseThrow(() -> new RuntimeException("Beam with weight " + weight + " is not found"));
    }

    @Override
    public List<RoadBeam> getByPrice(Double price) {
        return roadBeamRepository.findByPrice(price)
                .orElseThrow(() -> new RuntimeException("Beam with price " + price + " is not found"));
    }

    @Override
    public List<RoadBeam> getByLengthAndThicknessLessThan(Integer length, Double thickness) {
        return roadBeamRepository.findByLengthIsLessThanAndThicknessIsLessThan(length, thickness)
                .orElseThrow(() -> new RuntimeException("Beam less than length " + length + " and less than thickness "
                        + thickness + " is not found"));
    }
}
