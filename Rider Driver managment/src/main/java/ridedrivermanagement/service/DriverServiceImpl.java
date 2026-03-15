package ridedrivermanagement.service;

import ridedrivermanagement.model.Driver;
import ridedrivermanagement.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;

    public DriverServiceImpl(DriverRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return repository.findAll();
    }

    @Override
    public void registerDriver(Driver driver) {
        List<Driver> drivers = repository.findAll();
        if (drivers.stream().noneMatch(d -> d.getId().equals(driver.getId()))) {
            driver.setStatus("Available");
            driver.setTotalEarnings(0.0);
            drivers.add(driver);
            repository.saveAll(drivers);
        }
    }

    @Override
    public void assignRide(String driverId) {
        List<Driver> drivers = repository.findAll();
        for (Driver driver : drivers) {
            if (driver.getId().equals(driverId) && driver.getStatus().equals("Available")) {
                driver.setStatus("On Ride");
                break;
            }
        }
        repository.saveAll(drivers);
    }

    @Override
    public void updateEarnings(String driverId, double amount) {
        List<Driver> drivers = repository.findAll();
        for (Driver driver : drivers) {
            if (driver.getId().equals(driverId)) {
                driver.setTotalEarnings(driver.getTotalEarnings() + amount);
                driver.setStatus("Available");
                break;
            }
        }
        repository.saveAll(drivers);
    }

    @Override
    public void deleteDriver(String driverId) {
        List<Driver> drivers = repository.findAll();
        drivers.removeIf(driver -> driver.getId().equals(driverId));
        repository.saveAll(drivers);
    }
}