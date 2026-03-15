package ridedrivermanagement.repository;

import ridedrivermanagement.model.Driver;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepository {
    private final String FILE_PATH = "drivers.txt";

    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return drivers;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    drivers.add(new Driver(data[0], data[1], data[2], Double.parseDouble(data[3])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return drivers;
    }

    public void saveAll(List<Driver> drivers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Driver driver : drivers) {
                writer.write(driver.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public Optional<Driver> findById(String id) {
        return findAll().stream().filter(d -> d.getId().equals(id)).findFirst();
    }
}