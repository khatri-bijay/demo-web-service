package com.intuit.presentationdemo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="APPOINTMENT_STATUS")
@Getter
@Setter
public class AppointmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
