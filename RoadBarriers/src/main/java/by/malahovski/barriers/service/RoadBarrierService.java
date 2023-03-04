package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;


import java.util.List;


public interface RoadBarrierService {

        List<RoadBarrierParameters> getAllBarriers();

        List<RoadBarrierParameters> getBarriersByClass(EClassOfTheBarrier classOfTheBarrier);

        RoadBarrierParameters getBarriersByName(String name);

        List<RoadBarrierParameters> getRoadBarrierParametersByParameters (Integer holdingCapacity, Double workingWidth);

        RoadMetalBarrier calculateBarrierByParameters (Integer length, Integer holdingCapacity, Double workingWidth);

        Boolean updatePriceOnFile(String fileLocation);
}
