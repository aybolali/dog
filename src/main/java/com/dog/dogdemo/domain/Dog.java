package com.dog.dogdemo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId(mutable = true)
    @Column(name = "name_of_owner")
    private String nameOfOwner;
    @Column(name = "name_of_dog")
    private String nameOfDog;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private int age;
    @Column(name = "color")
    private String color;
}
