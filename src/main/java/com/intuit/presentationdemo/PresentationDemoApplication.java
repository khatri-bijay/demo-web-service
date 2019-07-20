package com.intuit.presentationdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
@Slf4j
@SpringBootApplication
public class PresentationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentationDemoApplication.class, args);
    }

    @PostConstruct
    public void setUp() {
        log.info("Setting timezone to UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
