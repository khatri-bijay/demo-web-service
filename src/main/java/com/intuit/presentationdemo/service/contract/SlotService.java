package com.intuit.presentationdemo.service.contract;

import com.intuit.presentationdemo.dto.query.SlotQuery;

import java.util.Date;
import java.util.List;

public interface SlotService {
    SlotQuery getSlots(long vetId, Date date);
}
