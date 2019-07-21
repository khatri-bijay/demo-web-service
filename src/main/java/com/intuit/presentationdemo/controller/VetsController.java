package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.VetValidator;
import com.intuit.presentationdemo.domain.Specialty;
import com.intuit.presentationdemo.dto.command.VetCommand;
import com.intuit.presentationdemo.dto.query.SpecialtyQuery;
import com.intuit.presentationdemo.dto.query.VetQuery;
import com.intuit.presentationdemo.service.contract.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("")
    public ResponseEntity<ApiResponse<Set<VetQuery>>> getVets(@RequestParam(required = false) String specialty) {
        Set<VetQuery> response = new HashSet<>();
        if(specialty == null || specialty.trim().isEmpty()){
            response = vetService.findAll();
        }else{
            response = vetService.findAllByType(specialty);
        }

        return ResponseEntity.ok(new ApiResponse<>(response));
    }

    @GetMapping("specialties")
    public ResponseEntity<ApiResponse<Set<SpecialtyQuery>>> getSpecialties() {
        Set<SpecialtyQuery> specialties = vetService.getSpecialties();
        return ResponseEntity.ok(new ApiResponse<>(specialties));

    }
}
