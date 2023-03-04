package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import by.malahovski.barriers.repository.RoadBarrierRepository;
import by.malahovski.barriers.service.RoadBarrierService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    public List<RoadBarrierParameters> getRoadBarrierParametersByParameters(Integer holdingCapacity, Double workingWidth) {
        return roadBarrierRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth)
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
    }

    @Override
    public RoadMetalBarrier calculateBarrierByParameters(Integer length, Integer holdingCapacity, Double workingWidth) {

        RoadMetalBarrier roadMetalBarrier = new RoadMetalBarrier();
        List<RoadBarrierParameters> roadBarrierParameters = getRoadBarrierParametersByParameters(holdingCapacity, workingWidth);
        roadBarrierParameters.forEach(System.out::println);
        System.out.println();

        RoadBarrierParameters parameters = roadBarrierParameters.stream()
                .max(Comparator.comparing(RoadBarrierParameters::getRackPitch))
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
        System.out.println(parameters);

        List<RoadBeam> roadBeams = new ArrayList<>();
        for (int i = 0; i < length / 6; i++) {
            roadBeams.add(parameters.getRoadBeam());
        }

        List<RoadRack> roadRacks = new ArrayList<>();
        for (int i = 0; i < length / parameters.getRackPitch() + 1; i++) {
            roadRacks.add(parameters.getRoadRack());
        }

        roadMetalBarrier.setLength(length);
        roadMetalBarrier.setHoldingCapacity(parameters.getHoldingCapacity());
        roadMetalBarrier.setWorkingWidth(parameters.getWorkingWidth());
        roadMetalBarrier.setRoadBarrierParameters(parameters);
        roadMetalBarrier.setRoadBeams(roadBeams);
        roadMetalBarrier.setRoadRacks(roadRacks);
        roadMetalBarrier.setTotalWeight(roadBeams.stream()
                .map(RoadBeam::getWeight)
                .reduce(Double::sum)
                .orElseThrow(() -> new RuntimeException("West is not installed")));
        roadMetalBarrier.setTotalCost(roadBeams.stream()
                .map(RoadBeam::getPrice)
                .reduce(Double::sum)
                .orElseThrow(() -> new RuntimeException("Price is not installed")));

        return roadMetalBarrier;
    }

    @Override
    public Boolean updatePriceOnFile(String fileLocation) {
        FileInputStream file;
        try {
            file = new FileInputStream(fileLocation);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "|");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "|");
                            break;
                    }
                }
                System.out.println();
            }
            file.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
