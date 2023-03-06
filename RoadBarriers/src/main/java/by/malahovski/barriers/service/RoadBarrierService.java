package by.malahovski.barriers.service;

import by.malahovski.barriers.models.barriers.RoadMetalBarrier;


public interface RoadBarrierService {

    RoadMetalBarrier calculateBarrierByParameters(Integer length, Integer holdingCapacity, Double workingWidth);

    Boolean updatePriceOnFile(String fileLocation);

    void serializeBarrierParameters(String namePath, Integer holdingCapacity, Double workingWidth);
}
