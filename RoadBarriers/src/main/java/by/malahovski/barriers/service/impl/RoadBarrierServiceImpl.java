package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.repository.RoadBarrierRepository;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class RoadBarrierServiceImpl implements RoadBarrierService {

    private final RoadBarrierRepository roadBarrierRepository;

    @Autowired
    public RoadBarrierServiceImpl(RoadBarrierRepository roadBarrierRepository) {
        this.roadBarrierRepository = roadBarrierRepository;
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
                prices = prices + scanner.nextLine();
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
        return roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthGreaterThan(holdingCapacity, workingWidth)
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
    }

    @Override
    public RoadMetalBarrier calculateBarrierByParameters(Integer length, Integer holdingCapacity, Double workingWidth) {
        if (roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthGreaterThan(holdingCapacity, workingWidth).isPresent()) {
            List<RoadBarrierParameters> roadBarrierParameters = roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthGreaterThan(holdingCapacity, workingWidth).get();
            RoadBarrierParameters parameters = roadBarrierParameters.stream().min(Comparator.comparing(RoadBarrierParameters::getRackPitch)).get();

            List<RoadBeam> roadBeams = new ArrayList<>();


            RoadMetalBarrier roadMetalBarrier = new RoadMetalBarrier();
            roadMetalBarrier.setLength(length);
            roadMetalBarrier.setHoldingCapacity(parameters.getHoldingCapacity());
            roadMetalBarrier.setWorkingWidth(parameters.getWorkingWidth());
            roadMetalBarrier.setRoadBarrierParameters(parameters);


        }
        return null;
    }
}
