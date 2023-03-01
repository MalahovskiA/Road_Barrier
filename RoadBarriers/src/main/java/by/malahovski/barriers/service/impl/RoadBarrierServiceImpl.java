package by.malahovski.barriers.service.impl;

import by.malahovski.barriers.models.barriers.RoadBarrier;
import by.malahovski.barriers.repository.RoadBarrierRepository;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadBarrierServiceImpl  implements RoadBarrierService {

    final  private RoadBarrierRepository roadBarrierRepository;

    @Autowired
    public RoadBarrierServiceImpl(RoadBarrierRepository roadBarrierRepository) {
        this.roadBarrierRepository = roadBarrierRepository;
    }

    @Override
    public List<RoadBarrier> getAllBarriers() {
        return roadBarrierRepository.findAll();
    }
}
