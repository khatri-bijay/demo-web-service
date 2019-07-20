package com.intuit.presentationdemo.dto.query;

import com.intuit.presentationdemo.domain.PetType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetQuery {
    private long id;
    private String name;
    private PetType type;
}
