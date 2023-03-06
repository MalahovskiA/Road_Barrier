package by.malahovski.barriers.models.barriers.roadBarrierKit;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "road_console")
public class RoadConsole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String designation;

    @Column
    private Integer length;

    @Column
    private Double thickness;

    @Column
    private Double weight;

    @Column
    private Double price;
}
