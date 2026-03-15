package ridedrivermanagement.controller;

import ridedrivermanagement.model.Driver;
import ridedrivermanagement.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver-dashboard";
    }

    @PostMapping("/register")
    public String registerDriver(@RequestParam String id, @RequestParam String name) {
        Driver newDriver = new Driver(id, name, "Available", 0.0);
        driverService.registerDriver(newDriver);
        return "redirect:/drivers/dashboard";
    }

    @PostMapping("/assign-ride")
    public String assignRide(@RequestParam String driverId) {
        driverService.assignRide(driverId);
        return "redirect:/drivers/dashboard";
    }

    @PostMapping("/add-payment")
    public String addPayment(@RequestParam String driverId, @RequestParam double amount) {
        driverService.updateEarnings(driverId, amount);
        return "redirect:/drivers/dashboard";
    }

    @PostMapping("/delete")
    public String deleteDriver(@RequestParam String driverId) {
        driverService.deleteDriver(driverId);
        return "redirect:/drivers/dashboard";
    }
}