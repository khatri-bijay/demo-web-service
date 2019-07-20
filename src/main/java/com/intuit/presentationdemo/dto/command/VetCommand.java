package com.intuit.presentationdemo.dto.command;

import lombok.*;

@Getter
@Setter
@ToString
public class VetCommand {
    private long id;
    private String name;
    private SpecialtyCommand specialty;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecialtyCommand {
        String name;
    }
}
