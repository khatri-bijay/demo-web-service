package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.AppointmentValidator;
import com.intuit.presentationdemo.domain.Appointment;
import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;
import com.intuit.presentationdemo.service.contract.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentsController {
    private final AppointmentService appointmentService;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointments(){
        return ResponseEntity.ok(new ApiResponse<>(null,null));
    }


    @PostMapping("pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<AppointmentQuery>> scheduleAppointment(@PathVariable long petId,
                                                                        @RequestBody AppointmentCommand command) {
        if(AppointmentValidator.isInvalid(command)) {
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }

        AppointmentQuery appointment = appointmentService.scheduleAppointment(petId, command);
        return ResponseEntity.ok(new ApiResponse<>(appointment));
    }

    private ResponseEntity<ApiResponse<Appointment>> getApiResponseResponseEntity() {
        ApiResponse.Error clientError = new ApiResponse.Error(HttpStatus.BAD_REQUEST.value(),
                ApiConstant.CLIENT_INPUT_ERROR, ApiConstant.CLIENT_INPUT_ERROR_MESSAGE);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, clientError));
    }


}
