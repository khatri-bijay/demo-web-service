package com.intuit.presentationdemo.dto.command;

import com.intuit.presentationdemo.domain.Specialty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class VetCommand {
    private long id;
    private String name;
    private Set<SpecialtyCommand> specialties = new HashSet<>();

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecialtyCommand {
        String name;
    }
}
