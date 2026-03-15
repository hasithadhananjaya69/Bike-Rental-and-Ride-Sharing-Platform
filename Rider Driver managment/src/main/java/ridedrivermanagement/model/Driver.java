package ridedrivermanagement.model;

public class Driver extends Person {
    private String status; // e.g., "Available", "On Ride"
    private double totalEarnings;

    public Driver(String id, String name, String status, double totalEarnings) {
        super(id, name); // Calls parent constructor
        this.status = status;
        this.totalEarnings = totalEarnings;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalEarnings() { return totalEarnings; }
    public void setTotalEarnings(double totalEarnings) { this.totalEarnings = totalEarnings; }

    // Format for writing to the text file
    @Override
    public String toString() {
        return id + "," + name + "," + status + "," + totalEarnings;
    }
}
