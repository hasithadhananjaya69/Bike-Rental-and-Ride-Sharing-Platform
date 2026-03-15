package ridedrivermanagement.controller;

import ridedrivermanagement.model.Driver;
import ridedrivermanagement.model.DriverRequest;
import ridedrivermanagement.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // READ: View All Drivers
    @GetMapping("/dashboard")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // CREATE: Register a New Driver with full profile
    @PostMapping("/register")
    public String registerDriver(@RequestBody DriverRequest req) {
        Driver newDriver = new Driver(
                req.getId(),
                req.getName(),
                "Available",
                0.0,
                req.getLicenseNo(),
                req.getAddress(),
                req.getNic(),
                req.getContactNumber(),
                req.getFullName(),
                req.getProfilePhoto(),
                req.getEmail(),
                req.getBankAccountNumber(),
                req.getBankBranch(),
                req.getBankName()
        );

        driverService.registerDriver(newDriver);
        return "Driver registered successfully!";
    }

    // UPDATE: Assign a Ride
    @PostMapping("/assign-ride")
    public String assignRide(@RequestBody DriverRequest request) {
        driverService.assignRide(request.getDriverId());
        return "Ride assigned to driver: " + request.getDriverId();
    }

    // UPDATE: Process Payment
    @PostMapping("/add-payment")
    public String addPayment(@RequestBody DriverRequest request) {
        driverService.updateEarnings(request.getDriverId(), request.getAmount());
        return "Payment of " + request.getAmount() + " added to driver: " + request.getDriverId();
    }

    // DELETE: Remove a driver
    @PostMapping("/delete")
    public String deleteDriver(@RequestBody DriverRequest request) {
        driverService.deleteDriver(request.getDriverId());
        return "Driver " + request.getDriverId() + " deleted successfully.";
    }

    // UPDATED: Login check using Username (name) and Email
    @PostMapping("/login")
    public String login(@RequestBody DriverRequest request) {
        // Validation: Search the file data for matching Username and Email
        boolean isAuthenticated = driverService.getAllDrivers().stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(request.getName())
                        && d.getEmail().equalsIgnoreCase(request.getEmail()));

        if (isAuthenticated) {
            return "Login Successful! Welcome, " + request.getName();
        } else {
            return "Login Failed: Invalid Username or Email.";
        }
    }
}