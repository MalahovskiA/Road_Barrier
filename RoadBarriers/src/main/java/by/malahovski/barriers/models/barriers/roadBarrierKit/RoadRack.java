package by.malahovski.barriers.models.barriers.roadBarrierKit;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "road_rack")
public class RoadRack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column (name = "road_rack_profile")
    private String roadRackProfile;

    @Column
    private Double height;

    @Column
    private Double thickness;

    @Column
    private Double weight;

    @Column
    private Double price;
}
