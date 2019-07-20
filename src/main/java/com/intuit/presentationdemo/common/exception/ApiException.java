package com.intuit.presentationdemo.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public final class ApiException extends RuntimeException{
    private int statusCode;
    private String errorCode;
    private String className;
    private String methodName;

    public ApiException(Builder builder) {
        super(builder.message);
        this.statusCode = builder.statusCode.value();
        this.methodName = builder.methodName;
        this.className = builder.className;
        this.errorCode = builder.errorCode;
    }

    public ApiException(HttpStatus statusCode, String errorCode, String message) {
        super(message);
        this.statusCode = statusCode.value();
        this.errorCode = errorCode;
    }

    public ApiException(HttpStatus statusCode, String errorCode) {
        this.statusCode = statusCode.value();
        this.errorCode = errorCode;
    }

    public ApiException(String className, String methodName, HttpStatus statusCode, String errorCode, String message) {
        this(statusCode, errorCode, message);
        this.className = className;
        this.methodName = methodName;

    }

    public static class Builder {
        private HttpStatus statusCode;
        private String errorCode;
        private String className;
        private String methodName;
        private String message;

        public Builder(String errorMessage) {
            this.message = errorMessage;
        }

        public Builder statusCode(HttpStatus statusCode){
            this.statusCode = statusCode;
            return this;
        }

        public Builder errorCode(String errorCode){
            this.errorCode = errorCode;
            return this;
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public Builder method(String methodName){
            this.methodName = methodName;
            return this;
        }


        public ApiException build() {
            return new ApiException(this);
        }
    }
}
