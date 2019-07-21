package com.intuit.presentationdemo;

import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.domain.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class TestUtils {
    public static Vet expectedVet() {
        Vet v = new Vet();
        v.setId(111);
        v.setName("Name");
        v.setSpecialty(expectedSpeciality());
        return v;
    }

    public static Specialty expectedSpeciality() {
        Specialty s = new Specialty();
        s.setId(1);
        s.setName("ONE");
        return s;
    }

    public static Set<Appointment> expectedAppointmentSet() {
        Appointment a1 = expectedAppointment();
        Appointment a2 = expectedAppointment(2);

        Set<Appointment> appointmentSet = new HashSet<>();
        appointmentSet.add(a1);
        appointmentSet.add(a2);
        return appointmentSet;
    }

    public static Appointment expectedAppointment(long id) {
        Appointment appointment = expectedAppointment();
        appointment.setId(id);
        return appointment;
    }

    public static Appointment expectedAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1);
        appointment.setVet(expectedVet());
        appointment.setPet(expectedPet());
        appointment.setDate(new Date("29-May-2016"));
        appointment.setAppointmentStatus(expectedAppointmentStatus());
        return appointment;
    }

    private static Pet expectedPet() {
        Pet p = new Pet();
        p.setName("Expected pet");
        p.setId(124);
        p.setType(expectedPetType());
        return p;
    }

    public static PetType expectedPetType() {
        PetType petType = new PetType();
        petType.setId(1);
        petType.setName("EXPECTED");
        return petType;
    }

    public static AppointmentStatus expectedAppointmentStatus() {
        AppointmentStatus s = new AppointmentStatus();
        s.setName("BOOKED");
        s.setId(1);
        return s;
    }

    public static AppointmentStatus expectedAppointmentStatus(Long id){
        AppointmentStatus appointmentStatus = expectedAppointmentStatus();
        appointmentStatus.setId(id);
        return appointmentStatus;
    }
}
