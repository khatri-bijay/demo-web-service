package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.VetValidator;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.VetQuery;
import com.intuit.presentationdemo.service.contract.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vets")
public class VetsController {
    private final VetService vetService;

    public VetsController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VetQuery>> addVet(@RequestBody VetCommand command){
        if(VetValidator.isInvalid(command)){
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }
        VetQuery vet = vetService.addVet(command);
        return ResponseEntity.ok(new ApiResponse<>(vet));
    }
}
