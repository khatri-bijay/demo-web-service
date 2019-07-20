package com.intuit.presentationdemo.dto.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class VetQuery {
    private long id;
    private String name;
    private SpecialtyQuery specialty;
}
