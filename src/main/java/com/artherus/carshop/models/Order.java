package com.artherus.carshop.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "order_id")
    private Integer order_id;

    @Column(nullable = false, name = "date_time")
    @NonNull
    private Timestamp date_time;

    @ManyToOne
    @JoinColumn(name = "client")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @ToString.Exclude
    private Car car_id;

    @Column(nullable = false, name = "is_test_drive_neccesary")
    @NonNull
    private Boolean is_test_drive_neccesary;

}
