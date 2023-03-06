package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import by.malahovski.barriers.repository.RoadBarrierParametersRepository;
import by.malahovski.barriers.repository.RoadBeamRepository;
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

    private final RoadBarrierParametersRepository roadBarrierParametersRepository;

    private final RoadBeamRepository roadBeamRepository;

    @Autowired
    public RoadBarrierServiceImpl(RoadBarrierParametersRepository roadBarrierParametersRepository, RoadBeamRepository roadBeamRepository) {
        this.roadBarrierParametersRepository = roadBarrierParametersRepository;
        this.roadBeamRepository = roadBeamRepository;
    }

    @Override
    public RoadMetalBarrier calculateBarrierByParameters(Integer length, Integer holdingCapacity, Double workingWidth) {

        RoadMetalBarrier roadMetalBarrier = new RoadMetalBarrier();
        List<RoadBarrierParameters> roadBarrierParameters = roadBarrierParametersRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth)
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
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
                Long id = (long) (row.getRowNum() + 1);
                System.out.println(id);
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            RoadBeam roadBeam = roadBeamRepository.getOne(id);
                            roadBeam.setPrice(cell.getNumericCellValue());
                            roadBeamRepository.save(roadBeam);
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

    @Override
    public void serializeBarrierParameters(String filePath, Integer holdingCapacity, Double workingWidth) {
        final File src = new File(filePath);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(src))) {
            objectOutputStream.writeObject(roadBarrierParametersRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth)
                    .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                            + workingWidth + " is not found")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
