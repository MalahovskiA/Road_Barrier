package by.malahovski.barriers.models.barriers;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoadMetalBarrier implements Serializable {

    private Integer length;

    private Integer holdingCapacity;

    private Double workingWidth;

    private RoadBarrierParameters roadBarrierParameters;

    private List<RoadBeam> roadBeams;

    private List<RoadRack> roadRacks;

    private Double totalWeight;

    private Double totalCost;
}
