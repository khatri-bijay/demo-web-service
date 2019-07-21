package com.intuit.presentationdemo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlotQuery {
    private int id;
    private List<TimeSlot> slots = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TimeSlot {
        String start;
        String end;
    }
}
