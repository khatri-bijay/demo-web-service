package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.PetValidator;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.service.contract.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("pets")
public class PetsController {
    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PetQuery>> savePet(@RequestBody PetCommand command) {
        if(PetValidator.isInValid(command)) {
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }
        PetQuery pet = petService.addPet(command);
        return ResponseEntity.ok(new ApiResponse<>(pet));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Set<PetQuery>>> getPets() {
        Set<PetQuery> allPets = petService.getAllPets();
        return ResponseEntity.ok(new ApiResponse<>(allPets));
    }
}