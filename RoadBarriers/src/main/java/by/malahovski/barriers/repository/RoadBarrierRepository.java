package by.malahovski.barriers.repository;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadBarrierRepository extends JpaRepository<RoadBarrier, Long> {

    @Override
    List<RoadBarrier> findAll();

    Optional<RoadBarrier> findByName(String name);

    Optional<List<RoadBarrier>> findAllByClassOfTheBarrier(EClassOfTheBarrier classOfTheBarrier);

}
