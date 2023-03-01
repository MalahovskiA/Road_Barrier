package by.malahovski.barriers.models.barriers.roadBarrierKit;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "road_rope")
public class RoadRope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer diameter;

    @Column(name = "number_of_lays")
    private Integer numberOfLays;

    @Column(name = "number_of_strands")
    private Integer numberOfStrands;

    @Column
    private Double weight;

    @Column
    private Double price;
}
