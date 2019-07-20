package com.intuit.presentationdemo.common.exception;

import com.intuit.presentationdemo.common.ApiResponse;
import com.intuit.presentationdemo.service.contract.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    private static final LoggerService<ApiResponse.Error> centralLogger = payload -> log.info("will log payload {}", payload);
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(ApiException ex){
        ApiResponse.Error error = new ApiResponse.Error(ex.getStatusCode(), ex.getErrorCode(), ex.getMessage());
        centralLogger.log(error);
        return ResponseEntity.status(ex.getStatusCode()).body(new ApiResponse<>(null, error));

    }
}
