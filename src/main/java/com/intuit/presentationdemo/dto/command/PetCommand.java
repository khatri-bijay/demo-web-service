package com.intuit.presentationdemo.dto.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetCommand {
    private String name;
    private PetTypeCommand type;

    @Getter
    @Setter
    public static class PetTypeCommand {
        private long petTypeId;
        private String name;

        public PetTypeCommand() {
        }

        public PetTypeCommand(String name) {
            this.name = name;
        }
    }
}
