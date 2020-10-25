package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.dataAccess.IMaintItemDao;
import com.example.dataAccess.IVehicleDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.*;

//TODO: Finish services
//
@Service
public class MaintenanceItemService extends com.example.service.Service {

    /**
     * Executes at 9:00 AM on the first Monday of every month. Sends an email to
     * each registered user with a list of previous and upcoming maintenance dates
     * for each maintenance event type.
     */
    @Scheduled(cron = "0 0 9 1,2,3,4,5,6,7 * MON")
    private void sendMaintenanceRemindersTask() {
        System.out.println("This is a test!!");
    }

    private void checkAuthorization(String sessionKey, String vehicleId) 
        throws NotFoundException, UnauthorizedException {

        this.checkValidSessionKey(sessionKey);
        String userId = this.getUserFromSessionKey(sessionKey);

        IVehicleDao vehicleDao; // = new VehicleDao(); TODO: instantiate interface
        Vehicle vehicle = vehicleDao.retrieveVehicle(vehicleId);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found");
        }
        if (!vehicle.getUserId().equals(userId)) {
            throw new UnauthorizedException();
        }
    }

    public List<MaintItem> getAllItems(String sessionKey, String vehicleId)
        throws NotFoundException, UnauthorizedException {
        
        this.checkAuthorization(sessionKey, vehicleId);
        IMaintItemDao maintItemDao; // = new MaintItemDao(); TODO: instantiate interface
        return maintItemDao.retrieveMaintItems(vehicleId);
    }
    
    public MaintItem getItem(String sessionKey, String vehicleId, String itemId) 
        throws NotFoundException, UnauthorizedException {

        this.checkAuthorization(sessionKey, vehicleId);
        IMaintItemDao maintItemDao; // = new MaintItemDao(); TODO: instantiate interface
        return maintItemDao.retrieveMaintItems(itemId);
    }
    
    public String addItem(String sessionKey, String vehicleId, MaintItem newItem)
        throws NotFoundException, UnauthorizedException {

        this.checkAuthorization(sessionKey, vehicleId);
        IMaintItemDao maintItemDao; // = new MaintItemDao(); TODO: instantiate interface
        maintItemDao.createMaintItem(newItem);
        return "success";
    }
    
    public String updateItem(String sessionKey, String vehicleId, String itemId, MaintItem updatedItem)
        throws NotFoundException, UnauthorizedException {
            
        this.checkAuthorization(sessionKey, vehicleId);
        IMaintItemDao maintItemDao; // = new MaintItemDao(); TODO: instantiate interface
        updatedItem.setId(itemId);
        maintItemDao.updateMaintItem(updatedItem);
        return "success";
    }
    
    public String deleteItem(String sessionKey, String vehicleId, String itemId)
        throws NotFoundException, UnauthorizedException {
            
        this.checkAuthorization(sessionKey, vehicleId);
        IMaintItemDao maintItemDao; // = new MaintItemDao(); TODO: instantiate interface
        maintItemDao.deleteMaintItem(itemId);
        return "success";
    }

}
