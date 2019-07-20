package com.intuit.presentationdemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ApiConstant {
    public static final String CLIENT_INPUT_ERROR = "CLIENT_ERROR_CODE";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND_ERROR_CODE";
    public static final String CONFLICT = "CONFLICT_CODE";

    public static final String CLIENT_INPUT_ERROR_MESSAGE = "ClientException: Payload is invalid.";
    public static final String CLIENT_INPUT_SPECIALTY_MESSAGE = "ClientException: Payload is invalid, not all specialties are valid.";

    public static enum APPOINTMENT_STATUS {
        BOOKED,COMPLETED, CANCELLED
    }

    //TODO: Think better
    public static <T> ResponseEntity<ApiResponse<T>> BAD_REQUEST_API_RESPONSE_FN() {
        ApiResponse.Error clientError = new ApiResponse.Error(HttpStatus.BAD_REQUEST.value(),
                ApiConstant.CLIENT_INPUT_ERROR, ApiConstant.CLIENT_INPUT_ERROR_MESSAGE);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, clientError));
    }


}
