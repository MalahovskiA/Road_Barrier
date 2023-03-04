package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoadConsoleRepository extends JpaRepository<RoadConsole, Long> {
    @NonNull
    Optional<RoadConsole> findById(@NonNull Long id);
}
