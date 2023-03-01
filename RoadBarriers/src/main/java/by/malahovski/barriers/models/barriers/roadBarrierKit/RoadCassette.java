package by.malahovski.barriers.models.barriers.roadBarrierKit;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "road_cassette")
public class RoadCassette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer height;

    @Column
    private Double thickness;

    @Column
    private Double weight;

    @Column
    private Double price;
}
