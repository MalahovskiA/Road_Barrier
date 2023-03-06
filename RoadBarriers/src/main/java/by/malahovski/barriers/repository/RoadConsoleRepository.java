package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadConsoleRepository extends JpaRepository<RoadConsole, Long> {
    @NonNull
    Optional<RoadConsole> findById(@NonNull Long id);

    Optional<List<RoadConsole>> findByName(String name);

    Optional <RoadConsole> findByDesignation(String designation);

    Optional<List<RoadConsole>> findByLength(Integer length);

    Optional<List<RoadConsole>> findByThickness(Double thickness);

    Optional<List<RoadConsole>> findByWeight(Double weight);

    Optional<List<RoadConsole>> findByPrice(Double price);
}
