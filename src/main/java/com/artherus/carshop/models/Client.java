package com.artherus.carshop.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "client_id")
    private Integer client_id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name="orders", columnDefinition = "int[]")
    @Type(type = "com.baeldung.hibernate.arraymapping.CustomIntegerArrayType")
    private Integer[] orders;

}
