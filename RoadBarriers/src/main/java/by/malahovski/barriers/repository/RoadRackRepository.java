package by.malahovski.barriers.repository;


import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoadRackRepository extends JpaRepository<RoadRack, Long> {

    @NonNull
    Optional<RoadRack> findByRoadRackProfile(@NonNull String profile);

    @NonNull
    Optional<RoadRack> findById(@NonNull Long id);
}
