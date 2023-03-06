package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadBeamRepository extends JpaRepository<RoadBeam, Long> {
    Optional<RoadBeam> findByName(String name);

    Optional<List<RoadBeam>> findByLength(Integer length);

    Optional<List<RoadBeam>> findByHolePitch(Integer pitch);

    Optional<List<RoadBeam>> findByThickness(Double thickness);

    Optional<List<RoadBeam>> findByWeight(Double weight);

    Optional<List<RoadBeam>> findByPrice(Double price);

    Optional<List<RoadBeam>> findByLengthIsLessThanAndThicknessIsLessThan(Integer length, Double thickness);

}
