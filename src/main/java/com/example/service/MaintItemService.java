package com.example.service;

import com.example.dataAccess.MaintItemDao;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
import com.example.model.MaintItem;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MaintItemService extends com.example.service.Service {

    @Autowired
    MaintItemDao maintItemDao;

    /**
     * Executes at 9:00 AM on the first Monday of every month.
     * Sends an email to each registered user with a list of previous and upcoming maintenance dates for each
     * maintenance event type.
     * */
    @Scheduled(cron = "0 0 9 1,2,3,4,5,6,7 * MON")
    private void sendMaintenanceRemindersTask() {
        System.out.println("This is a test!!");
    }

    public List<MaintItem> getAllMaintItems(String authToken, Integer vehicleId) throws UnauthorizedException, NotFoundException {
        validateRequest(authToken, vehicleId);
        return maintItemDao.retrieveMaintItems(vehicleId);
    }

    public MaintItem getMaintItem(String authToken, Integer vehicleId, Integer maintItemId) throws UnauthorizedException, NotFoundException {
        validateRequest(authToken, vehicleId);
        MaintItem maintItem = maintItemDao.retrieveMaintItem(maintItemId);
        if(maintItem == null) {
            throw new NotFoundException("MaintItem not found");
        }
        if (maintItem.getVehicleId() != vehicleId) {
            throw new UnauthorizedException();
        }
        return maintItem;
    }

    //TODO: should we make sure the maintItem is not null in any fields??
    public Integer addMaintItem(String authToken, Integer vehicleId, MaintItem maintItem) throws UnauthorizedException, NotFoundException {
        validateRequest(authToken, vehicleId);
        maintItem.setVehicleId(vehicleId);
        //TODO: check maintItem validity
        return maintItemDao.createMaintItem(maintItem);
    }

    /**
     * Updates the maint item tied to the maint item id with the information found in the provided
     * MaintItem object. If a field in the MaintItem object is null, the updated maint item will
     * keep that field's value from the old maint item.
     *
     * @param authToken The auth token of the user
     * @param vehicleId The vehicle id of the associated vehicle
     * @param maintItemId The maint item id of the maint item to be updated
     * @param maintItem The MaintItem object containing the information to update
     * @throws UnauthorizedException
     * @throws NotFoundException
     */
    public void updateMaintItem(String authToken, Integer vehicleId, Integer maintItemId, MaintItem maintItem) throws UnauthorizedException, NotFoundException {
        MaintItem oldMaintItem = this.getMaintItem(authToken, vehicleId, maintItemId);
        maintItem.setMaintItemId(maintItemId);
        maintItem.setVehicleId(vehicleId);
        if(Objects.isNull(maintItem.getFrequencyMonths())){
            maintItem.setFrequencyMonths(oldMaintItem.getFrequencyMonths());
        }
        if(Objects.isNull(maintItem.getFrequencyMiles())){
            maintItem.setFrequencyMiles(oldMaintItem.getFrequencyMiles());
        }
        if(StringUtils.isEmpty(maintItem.getDescription())){
            maintItem.setDescription(oldMaintItem.getDescription());
        }
        if(Objects.isNull(maintItem.getLastCompletedDate())){
            maintItem.setLastCompletedDate(oldMaintItem.getLastCompletedDate());
        }
        if(Objects.isNull(maintItem.getLastCompletedMileage())){
            maintItem.setLastCompletedMileage(oldMaintItem.getLastCompletedMileage());
        }
        maintItemDao.updateMaintItem(maintItem);
    }

    public void deleteMaintItem(String authToken, Integer vehicleId, Integer maintItemId) throws UnauthorizedException, NotFoundException {
        this.getMaintItem(authToken, vehicleId, maintItemId);
        maintItemDao.deleteMaintItem(maintItemId);
    }

    private void validateRequest(String authToken, Integer vehicleId) throws UnauthorizedException, NotFoundException {
        int userId = this.getUserIdFromAuthToken(authToken);
        this.checkValidVehicle(userId, vehicleId);
    }
}
