package by.malahovski.barriers.repository;


import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRackRepository extends JpaRepository<RoadRack, Long> {

    RoadRack findByRoadRackProfile(String profile);
}
