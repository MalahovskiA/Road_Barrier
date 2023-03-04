package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import by.malahovski.barriers.repository.RoadBarrierRepository;
import by.malahovski.barriers.repository.RoadRackRepository;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class RoadBarrierServiceImpl implements RoadBarrierService {

    private final RoadBarrierRepository roadBarrierRepository;

    private final RoadRackRepository roadRackRepository;

    @Autowired
    public RoadBarrierServiceImpl(RoadBarrierRepository roadBarrierRepository, RoadRackRepository roadRackRepository) {
        this.roadBarrierRepository = roadBarrierRepository;
        this.roadRackRepository = roadRackRepository;
    }

    @Override
    public List<RoadBarrierParameters> getAllBarriers() {
        return roadBarrierRepository.findAll();
    }

    @Override
    public List<RoadBarrierParameters> getBarriersByClass(EClassOfTheBarrier classOfTheBarrier) {
        return roadBarrierRepository.findAllByClassOfTheBarrier(classOfTheBarrier)
                .orElseThrow(() -> new RuntimeException("Barriers of class " + classOfTheBarrier + " is not found"));
    }

    @Override
    public RoadBarrierParameters getBarriersByName(String name) {
        return roadBarrierRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Barriers with name: " + name + " is not found"));
    }

    @Override
    public Boolean readPrice(String fileName) {
        try {
            String prices = null;
            File src = new File(fileName);
            FileReader reader = new FileReader(src);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                prices = scanner.nextLine();
            }
            System.out.println("Чтение файла: " + fileName);
            System.out.println(prices);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public List<RoadBarrierParameters> getRoadBarrierParametersByParameters(Integer holdingCapacity, Double workingWidth) {
        return roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth)
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
    }

    @Override
    public RoadMetalBarrier calculateBarrierByParameters(Integer length, Integer holdingCapacity, Double workingWidth) {
        if (roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth).isPresent()) {
            RoadMetalBarrier roadMetalBarrier = new RoadMetalBarrier();
            List<RoadBarrierParameters> roadBarrierParameters = roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth).get();

            if (roadBarrierParameters.stream().max(Comparator.comparing(RoadBarrierParameters::getRackPitch)).isPresent()) {
                RoadBarrierParameters parameters = roadBarrierParameters.stream().max(Comparator.comparing(RoadBarrierParameters::getRackPitch)).get();


                List<RoadBeam> roadBeams = new ArrayList<>();
                for (int i = 0; i < length / parameters.getRoadBeam().getLength() / 1000; i++) {
                    roadBeams.add(parameters.getRoadBeam());
                }

                List<RoadRack> roadRacks = new ArrayList<>();
                for (int i = 0; i < length / parameters.getRackPitch() + 1; i++) {
                    roadRacks.add(roadRackRepository.findByRoadRackProfile(parameters.getRackProfile()));
                }

                List<RoadConsole> roadConsoles = new ArrayList<>();
                for (int i = 0; i < length / parameters.getRackPitch() + 1; i++) {
                    roadConsoles.add(roadRackRepository.findByRoadRackProfile(parameters.getRackProfile()).getRoadConsole());
                }

                roadMetalBarrier.setLength(length);
                roadMetalBarrier.setHoldingCapacity(parameters.getHoldingCapacity());
                roadMetalBarrier.setWorkingWidth(parameters.getWorkingWidth());
                roadMetalBarrier.setRoadBarrierParameters(parameters);
                roadMetalBarrier.setRoadBeams(roadBeams);
                roadMetalBarrier.setRoadRacks(roadRacks);
                roadMetalBarrier.setRoadConsoles(roadConsoles);
            }
            return roadMetalBarrier;
        }
        return null;
    }
}
