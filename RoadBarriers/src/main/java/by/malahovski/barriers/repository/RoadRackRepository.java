package by.malahovski.barriers.repository;


import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadRackRepository extends JpaRepository<RoadRack, Long> {


    @NonNull
    Optional<RoadRack> findById(@NonNull Long id);

    Optional<RoadRack> findByName(String name);

    Optional<List<RoadRack>> findByRoadRackProfile(String profile);

    Optional<List<RoadRack>> findByHeight(Double height);

    Optional<List<RoadRack>> findByThickness(Double thickness);

    Optional<List<RoadRack>> findByWeight(Double weight);

    Optional<List<RoadRack>> findByPrice(Double price);
}
