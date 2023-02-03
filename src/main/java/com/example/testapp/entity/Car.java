package com.example.testapp.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registerSign", nullable=false, unique = true)
    private String registerSign;

    @Column(name = "brand", nullable=false)
    private String brand;

    @Column(name = "color", nullable=false)
    private String color;

    @Column(name = "release", nullable = false)
    private Integer release;
}