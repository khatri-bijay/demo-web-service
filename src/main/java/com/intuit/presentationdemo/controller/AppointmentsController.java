package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.AppointmentValidator;
import com.intuit.presentationdemo.dto.command.AppointmentCommand;
import com.intuit.presentationdemo.dto.query.AppointmentQuery;
import com.intuit.presentationdemo.dto.query.SlotQuery;
import com.intuit.presentationdemo.service.contract.AppointmentService;
import com.intuit.presentationdemo.service.contract.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AppointmentsController {
    private final AppointmentService appointmentService;
    private final SlotService slotService;

    public AppointmentsController(AppointmentService appointmentService, SlotService slotService) {
        this.appointmentService = appointmentService;
        this.slotService = slotService;
    }

    @GetMapping("pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<List<AppointmentQuery>>> getPetsAppointments(
            @PathVariable long petId,
            @RequestParam(required = false) Date date){
        List<AppointmentQuery> appointments;
        if(date == null) {
            appointments = appointmentService.getAppointmentsForPet(petId);
        }else {
            appointments = appointmentService.getAppointmentsForPet(petId, date);
        }
        return ResponseEntity.ok(new ApiResponse<>(appointments));
    }

    @GetMapping("vets/{vetId}/appointments")
    public ResponseEntity<ApiResponse<List<AppointmentQuery>>> getVetsAppointments(
            @PathVariable long vetId,
            @RequestParam(required = false) Date date){
        List<AppointmentQuery> appointments;
        if(date == null) {
            appointments = appointmentService.getAppointmentsForVet(vetId);
        }else{
            appointments = appointmentService.getAppointmentsForVet(vetId,date);

        }
        return ResponseEntity.ok(new ApiResponse<>(appointments));
    }

    @GetMapping("vets/{vetId}/appointments/slots")
    public ResponseEntity<ApiResponse<SlotQuery>> getSlots(
            @PathVariable long vetId,
            @RequestParam Date date){
        SlotQuery slots = slotService.getSlots(vetId, date);
        return ResponseEntity.ok(new ApiResponse<>(slots));
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

    @PutMapping("pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<AppointmentQuery>> replaceAppointment(@PathVariable long petId,
                                                                             @RequestBody AppointmentCommand command) {
        //TODO: IMPLEMENT
        if(AppointmentValidator.isInvalid(command)) {
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }

        AppointmentQuery appointment = appointmentService.scheduleAppointment(petId, command);
        return ResponseEntity.ok(new ApiResponse<>(appointment));
    }

    @PatchMapping("pets/{petId}/appointments")
    public ResponseEntity<ApiResponse<AppointmentQuery>> updateAppointment(@PathVariable long petId,
                                                     @RequestBody AppointmentCommand command) {
        //TODO: IMPLEMENT
        if(AppointmentValidator.isInvalid(command)) {
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }

        AppointmentQuery appointment = appointmentService.scheduleAppointment(petId, command);
        return ResponseEntity.ok(new ApiResponse<>(appointment));
    }

}
