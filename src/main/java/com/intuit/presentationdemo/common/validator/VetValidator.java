package com.intuit.presentationdemo.common.validator;

import com.intuit.presentationdemo.dto.command.VetCommand;
import org.springframework.stereotype.Component;

@Component
public class VetValidator{
    public static boolean isInvalid(VetCommand command) {
        return command.getName() == null || command.getName().trim().isEmpty() ||
                command.getSpecialty() == null || command.getSpecialty().getName() == null ||
                command.getSpecialty().getName().trim().isEmpty();
    }
}
