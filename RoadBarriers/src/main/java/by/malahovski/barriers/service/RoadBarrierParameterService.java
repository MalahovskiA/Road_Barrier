package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;

import java.util.List;

public interface RoadBarrierParameterService {

    void deleteParametersById(Long id);

    List<RoadBarrierParameters> getAllBarriers();

    List<RoadBarrierParameters> getBarriersByClass(EClassOfTheBarrier classOfTheBarrier);

    RoadBarrierParameters getBarriersByName(String name);

    List<RoadBarrierParameters> getRoadBarrierParametersByParameters (Integer holdingCapacity, Double workingWidth);
}
