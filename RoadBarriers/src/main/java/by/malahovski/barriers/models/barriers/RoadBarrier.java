package by.malahovski.barriers.models.barriers;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "main_parameters")
public  class RoadBarrier {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Long id;

        @Column
        private String name;

        @Column (name = "group_barrier")
        private Integer group;

        @Column
        private Integer type;

        @Column (name = "class")
        @Enumerated(EnumType.STRING)
        EClassOfTheBarrier classOfTheBarrier;

        @Column
        private Integer holdingCapacity;

        @Column
        private Double height;

        @Column
        private Double rackPitch;

        @Column
        private Double dynamicDeflection;

        @Column
        private Double workingWidth;

        @Column
        private String normative;
}
