package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;
import by.malahovski.barriers.repository.RoadConsoleRepository;
import by.malahovski.barriers.service.RoadConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadConsoleServiceImpl implements RoadConsoleService {

    private final RoadConsoleRepository roadConsoleRepository;

    @Autowired
    public RoadConsoleServiceImpl(RoadConsoleRepository roadConsoleRepository) {
        this.roadConsoleRepository = roadConsoleRepository;
    }

    @Override
    public Boolean deleteConsoleById(Long id) {
        if (roadConsoleRepository.existsById(id)) {
            roadConsoleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveConsole(Long id, RoadConsole console) {
        if (roadConsoleRepository.findById(id).isPresent()) {
            RoadConsole console1 = roadConsoleRepository.findById(id).get();
            console1.setId(console.getId());
            console1.setName(console.getName());
            console1.setDesignation(console.getDesignation());
            console1.setLength(console.getLength());
            console1.setThickness(console.getThickness());
            console1.setWeight(console.getWeight());
            console1.setPrice(console.getPrice());
            roadConsoleRepository.saveAndFlush(console);
        } else {
            roadConsoleRepository.save(console);
        }

    }

    @Override
    public List<RoadConsole> findByName(String name) {
        return roadConsoleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Console with name " + name + " is not found"));
    }

    @Override
    public RoadConsole findByDesignation(String designation) {
        return roadConsoleRepository.findByDesignation(designation)
                .orElseThrow(() -> new RuntimeException("Console with designation " + designation + " is not found"));
    }

    @Override
    public List<RoadConsole> findByLength(Integer length) {
        return roadConsoleRepository.findByLength(length)
                .orElseThrow(() -> new RuntimeException("Console with length " + length + " is not found"));
    }

    @Override
    public List<RoadConsole> findByThickness(Double thickness) {
        return roadConsoleRepository.findByThickness(thickness)
                .orElseThrow(() -> new RuntimeException("Console with thickness " + thickness + " is not found"));
    }

    @Override
    public List<RoadConsole> findByWeight(Double weight) {
        return roadConsoleRepository.findByWeight(weight)
                .orElseThrow(() -> new RuntimeException("Console with weight " + weight + " is not found"));
    }

    @Override
    public List<RoadConsole> findByPrice(Double price) {
        return roadConsoleRepository.findByPrice(price)
                .orElseThrow(() -> new RuntimeException("Console with price " + price + " is not found"));
    }
}
