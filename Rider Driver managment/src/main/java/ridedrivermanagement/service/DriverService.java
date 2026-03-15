package ridedrivermanagement.service;

import ridedrivermanagement.model.Driver;
import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();
    void registerDriver(Driver driver);
    void assignRide(String driverId);
    void updateEarnings(String driverId, double amount);
    void deleteDriver(String driverId);
}