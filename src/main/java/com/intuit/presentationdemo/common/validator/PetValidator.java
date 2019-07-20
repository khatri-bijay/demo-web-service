package com.intuit.presentationdemo.common.validator;

import com.intuit.presentationdemo.dto.command.PetCommand;

public class PetValidator{
    public static boolean isInValid(PetCommand command) {
        return command.getName() == null || command.getName().trim().isEmpty()
                || command.getType() == null || command.getType().getName() == null || command.getType().getName().trim().isEmpty();
    }

}
