package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;

import java.util.List;
import java.util.Set;

public interface RoadBarrierService {

        List<RoadBarrierParameters> getAllBarriers();

        List<RoadBarrierParameters> getBarriersByClass(EClassOfTheBarrier classOfTheBarrier);

        RoadBarrierParameters getBarriersByName(String name);

        Boolean readPrice(String fileName);

        List<RoadBarrierParameters> getRoadBarrierParametersByParameters (
                                                            Integer holdingCapacity,
                                                            Double workingWidth);
}
