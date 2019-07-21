package com.intuit.presentationdemo.service;

import com.intuit.presentationdemo.dto.query.SlotQuery;
import com.intuit.presentationdemo.service.contract.SlotService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
public class StaticSlotService implements SlotService {
    @Override
    public SlotQuery getSlots(long vetId, Date date) {
        SlotQuery s = new SlotQuery();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return s;
        }

        SlotQuery.TimeSlot t1 = new SlotQuery.TimeSlot("8AM", "9AM");
        SlotQuery.TimeSlot t2 = new SlotQuery.TimeSlot("9AM", "10AM");
        SlotQuery.TimeSlot t3 = new SlotQuery.TimeSlot("11AM", "12PM");
        SlotQuery.TimeSlot t4 = new SlotQuery.TimeSlot("1PM", "2PM");
        SlotQuery.TimeSlot t5 = new SlotQuery.TimeSlot("2PM", "3PM");
        SlotQuery.TimeSlot t6 = new SlotQuery.TimeSlot("3PM", "4PM");
        SlotQuery.TimeSlot t7 = new SlotQuery.TimeSlot("4PM", "5PM");

        s.setSlots(Arrays.asList(t1, t2, t3, t4, t5, t6, t7));

        return s;
    }
}
