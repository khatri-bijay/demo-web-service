package com.intuit.presentationdemo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APPOINTMENTS")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private Vet vet;
    @ManyToOne
    private Pet pet;
    @OneToOne
    private AppointmentStatus appointmentStatus;
}
