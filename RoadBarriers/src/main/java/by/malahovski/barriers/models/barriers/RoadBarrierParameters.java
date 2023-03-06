package by.malahovski.barriers.models.barriers;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "main_parameters")
public  class RoadBarrierParameters implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;

        @Column
        private String name;

        @Column(name = "group_barrier")
        private Integer group;

        @Column
        private Integer type;

        @Column(name = "class")
        @Enumerated(EnumType.STRING)
        EClassOfTheBarrier classOfTheBarrier;

        @Column(name = "holding_capacity")
        private Integer holdingCapacity;

        @Column
        private Double height;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="road_beam_id")
        private RoadBeam roadBeam;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="road_rack_id")
        private RoadRack roadRack;

        @Column(name = "rack_pitch")
        private Double rackPitch;

        @Column(name = "dynamic_deflection")
        private Double dynamicDeflection;

        @Column(name = "working_width")
        private Double workingWidth;

        @Column
        private String normative;
}
