package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;

import java.util.List;


public interface RoadRackService {

    Boolean deleteRackById(Long id);

    void saveRack(Long id, RoadRack roadRack);

    RoadRack findById(Long id);

    RoadRack findByName(String name);

    List<RoadRack> findByRoadRackProfile(String profile);

    List<RoadRack> findByHeight(Double height);

    List<RoadRack> findByThickness(Double thickness);

    List<RoadRack> findByWeight(Double weight);

    List<RoadRack> findByPrice(Double price);
}
