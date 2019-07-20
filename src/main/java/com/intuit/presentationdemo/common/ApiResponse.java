package com.intuit.presentationdemo.common;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private Error error;

    public ApiResponse() {

    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Error error) {
        this.error = error;
    }

    public ApiResponse(T data, Error error) {
        this.data = data;
        this.error = error;
    }

    @Getter
    @Setter
    @ToString
    public static class Error {
        int statusCode;
        String errorCode;
        String errorMessage;

        public Error(int statusCode, String errorCode, String errorMessage) {
            this.statusCode = statusCode;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

    }
}
