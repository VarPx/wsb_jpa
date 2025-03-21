package com.jpacourse.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ANIMAL")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 30, nullable = false, unique = true)
    private String name;

    @Transient
    private int age;

    public AnimalEntity() { } // Pusty konstruktor wymagany przez Hibernate

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
