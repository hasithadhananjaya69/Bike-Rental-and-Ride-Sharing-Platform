package ridedrivermanagement.repository;

import ridedrivermanagement.model.Driver;
import org.springframework.stereotype.Repository;
import java.io.*;
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
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // UPDATED: Split using the pipe symbol. Double backslash is required in Java for regex.
                String[] d = line.split("\\|");

                // Verification for the 14 fields
                if (d.length >= 14) {
                    drivers.add(new Driver(
                            d[0].trim(), d[1].trim(), d[2].trim(), Double.parseDouble(d[3].trim()),
                            d[4].trim(), d[5].trim(), d[6].trim(), d[7].trim(), d[8].trim(),
                            d[9].trim(), d[10].trim(), d[11].trim(), d[12].trim(), d[13].trim()
                    ));
                }
            }
        } catch (Exception e) {
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