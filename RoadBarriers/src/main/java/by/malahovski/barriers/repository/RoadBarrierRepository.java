package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadBarrierRepository extends JpaRepository<RoadBarrierParameters, Long> {

    @Override
    List<RoadBarrierParameters> findAll();

    Optional<RoadBarrierParameters> findByName(String name);

    Optional<List<RoadBarrierParameters>> findAllByClassOfTheBarrier(EClassOfTheBarrier classOfTheBarrier);

//    List<RoadBarrierParameters> findAllByHoldingCapacityBeforeAndWorkingWidthBefore(Integer holdingCapacity, Double workingWidth);
}
