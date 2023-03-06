package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;

import java.util.List;


public interface RoadBeamService {

    Boolean deleteBeamById(Long id);

    void saveBeam (Long id, RoadBeam beam);

    RoadBeam getByName(String name);

    List<RoadBeam> getByLength(Integer length);

    List<RoadBeam> getByHolePitch(Integer pitch);

    List<RoadBeam> getByThickness(Double thickness);

    List<RoadBeam> getByWeight(Double weight);

    List<RoadBeam> getByPrice(Double price);

    List<RoadBeam> getByLengthAndThicknessLessThan(Integer length, Double thickness);
}
