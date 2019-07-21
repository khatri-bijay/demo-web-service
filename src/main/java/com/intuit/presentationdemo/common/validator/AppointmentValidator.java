package com.intuit.presentationdemo.common.validator;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.exception.ApiException;
import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentValidator{
    public static boolean isInvalid(AppointmentCommand command) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date clientDate = sdf.parse(sdf.format(command.getDate()));
            Date now = sdf.parse(sdf.format(new Date()));
            return command.getDate() == null || command.getVet() == null || clientDate.before(now) ||
                    command.getStart() == null || command.getStart().trim().isEmpty() ||
                    command.getEnd() == null || command.getEnd().trim().isEmpty();
        } catch (ParseException e) {
            throw new ApiException.Builder("Date parsing exception")
                    .errorCode(ApiConstant.CLIENT_INPUT_ERROR)
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
