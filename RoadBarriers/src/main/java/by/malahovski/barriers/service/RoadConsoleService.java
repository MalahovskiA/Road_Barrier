package by.malahovski.barriers.service;


import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;


import java.util.List;

public interface RoadConsoleService {

    Boolean deleteConsoleById(Long id);

    void saveConsole(Long id, RoadConsole roadConsole);

    List<RoadConsole> findByName(String name);

    RoadConsole findByDesignation(String designation);

    List<RoadConsole> findByLength(Integer length);

    List<RoadConsole> findByThickness(Double thickness);

    List<RoadConsole> findByWeight(Double weight);

    List<RoadConsole> findByPrice(Double price);
}
