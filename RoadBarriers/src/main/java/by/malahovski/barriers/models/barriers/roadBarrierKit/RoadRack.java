package by.malahovski.barriers.models.barriers.roadBarrierKit;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "road_rack")
public class RoadRack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column (name = "road_rack_profile")
    private String roadRackProfile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="road_console_id")
    private RoadConsole roadConsole;

    @Column
    private Double height;

    @Column
    private Double thickness;

    @Column
    private Double weight;

    @Column
    private Double price;
}
