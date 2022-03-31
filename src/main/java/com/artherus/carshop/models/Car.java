package com.artherus.carshop.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "cars")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "CAR_SEQ", allocationSize = 1)
    @Column(nullable = false, name = "reg_id")
    private Integer reg_id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @ToString.Exclude
    private CarModel model_id;

    @Column(name="embedded_systems")
    private String embedded_systems;

    @Column(name = "upholstery")
    private String upholstery;

    @Column(nullable = false, name = "color")
    @NonNull
    private String color;

    @Column(name="seat_type")
    @NonNull
    private String seat_type;

    @Column(name = "last_tech_inspection")
    private Timestamp last_tech_inspection;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private BigDecimal price;
}
