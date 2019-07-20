package com.intuit.presentationdemo.controller;

import com.intuit.presentationdemo.common.ApiConstant;
import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.common.validator.PetValidator;
import com.intuit.presentationdemo.dto.command.PetCommand;
import com.intuit.presentationdemo.dto.query.PetQuery;
import com.intuit.presentationdemo.dto.query.VetQuery;
import com.intuit.presentationdemo.service.contract.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<PetQuery>>> getPets() {
        PetQuery p1 = new PetQuery();
        p1.setName("DOG1");
        p1.setId(1);

        PetQuery p2 = new PetQuery();
        p2.setName("CAT1");
        p2.setId(2);

        List<PetQuery> res = new ArrayList<>();
        res.add(p1);
        res.add(p2);

        return ResponseEntity.ok(new ApiResponse<>(res));
    }
}
