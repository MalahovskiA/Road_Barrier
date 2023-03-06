package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.repository.RoadBarrierParametersRepository;
import by.malahovski.barriers.service.RoadBarrierParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadBarrierParameterServiceImpl implements RoadBarrierParameterService {

    private final RoadBarrierParametersRepository roadBarrierParametersRepository;

    @Autowired
    public RoadBarrierParameterServiceImpl(RoadBarrierParametersRepository roadBarrierParametersRepository) {
        this.roadBarrierParametersRepository = roadBarrierParametersRepository;
    }

    @Override
    public void deleteParametersById(Long id) {
        if(roadBarrierParametersRepository.existsRoadBarrierParametersById(id)) {
            roadBarrierParametersRepository.deleteById(id);
        }
    }

    @Override
    public List<RoadBarrierParameters> getAllBarriers() {
        return roadBarrierParametersRepository.findAll();
    }

    @Override
    public List<RoadBarrierParameters> getBarriersByClass(EClassOfTheBarrier classOfTheBarrier) {
        return roadBarrierParametersRepository.findAllByClassOfTheBarrier(classOfTheBarrier)
                .orElseThrow(() -> new RuntimeException("Barriers of class " + classOfTheBarrier + " is not found"));
    }

    @Override
    public RoadBarrierParameters getBarriersByName(String name) {
        return roadBarrierParametersRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Barriers with name: " + name + " is not found"));
    }

    @Override
    public List<RoadBarrierParameters> getRoadBarrierParametersByParameters(Integer holdingCapacity, Double workingWidth) {
        return roadBarrierParametersRepository.findAllByHoldingCapacityGreaterThanAndWorkingWidthIsLessThan(holdingCapacity, workingWidth)
                .orElseThrow(() -> new RuntimeException("Barriers on" + holdingCapacity + " and "
                        + workingWidth + " is not found"));
    }
}
