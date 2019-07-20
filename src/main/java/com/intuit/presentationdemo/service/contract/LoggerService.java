package com.intuit.presentationdemo.service.contract;

@FunctionalInterface
public interface LoggerService<T> {
    void log(T logData);
}
