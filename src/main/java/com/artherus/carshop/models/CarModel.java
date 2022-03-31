package com.artherus.carshop.models;

import lombok.*;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;

@Entity
@Table(name = "car_models")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "CAR_MODEL_SEQ", allocationSize = 1)
    @Column(nullable = false, name = "model_id")
    private Integer model_id;

    @Column(nullable = false, name = "model")
    @NonNull
    private String model;

    @Column(nullable = false, name = "manufacturer")
    @NonNull
    private String manufacturer;

}
