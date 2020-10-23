package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//TODO: Finish services
//
@Service
public class MaintenanceItemService {

    /**
     * Executes at 9:00 AM on the first Monday of every month.
     * Sends an email to each registered user with a list of previous and upcoming maintenance dates for each
     * maintenance event type.
     * */
    @Scheduled(cron = "0 0 9 1,2,3,4,5,6,7 * MON")
    private void sendMaintenanceRemindersTask() {
        System.out.println("This is a test!!");
    }

}
