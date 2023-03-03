package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoadBeamRepository extends JpaRepository<RoadBarrierParameters, Long> {
    Optional<RoadBeam> findByName(String name);
}
