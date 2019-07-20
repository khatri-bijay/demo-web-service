package com.intuit.presentationdemo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="VETS")
@Getter
@Setter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany
    private Set<Specialty> specialties = new HashSet<>();
}
