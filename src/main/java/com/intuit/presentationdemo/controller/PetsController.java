package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.PetValidator;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.service.contract.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pets")
public class PetsController {
    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    private ResponseEntity<ApiResponse<PetQuery>> addPet(@RequestBody PetCommand petCommand) {
        if(PetValidator.isInValid(petCommand)) {
            return ApiConstant.BAD_REQUEST_API_RESPONSE_FN();
        }
        PetQuery pet = petService.addPet(petCommand);
        return ResponseEntity.ok(new ApiResponse<>(pet));
    }
}
