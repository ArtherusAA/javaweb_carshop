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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "reg_id")
    private Integer reg_id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @ToString.Exclude
    private CarModel model_id;

    @Column(name="embedded_systems", columnDefinition = "text[]")
    @Type(type = "com.baeldung.hibernate.arraymapping.CustomStringArrayType")
    private String[] embedded_systems;

    @Column(name = "upholstery")
    private String upholstery;

    @Column(nullable = false, name = "color")
    @NonNull
    private String color;

    @Column(name="seat_type")
    @NonNull
    private String seat_type;

    @Column(name="extra_interior_components", columnDefinition = "text[]")
    @Type(type = "com.baeldung.hibernate.arraymapping.CustomStringArrayType")
    private String[] extra_interior_components;

    @Column(name = "last_tech_inspection")
    private Timestamp last_tech_inspection;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="test_drive_clients", columnDefinition = "int[]")
    @Type(type = "com.baeldung.hibernate.arraymapping.CustomIntegerArrayType")
    private Integer[] test_drive_clients;
}
