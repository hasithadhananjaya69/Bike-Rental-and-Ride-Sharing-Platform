package ridedrivermanagement.model;

public class Driver extends Person {
    private String status;
    private double totalEarnings;
    private String licenseNo;
    private String address;
    private String nic;
    private String contactNumber;
    private String fullName;
    private String profilePhoto;
    private String email;
    private String bankAccountNumber;
    private String bankBranch;
    private String bankName;

    public Driver(String id, String name, String status, double totalEarnings, String licenseNo, String address,
                  String nic, String contactNumber, String fullName, String profilePhoto, String email,
                  String bankAccountNumber, String bankBranch, String bankName) {
        super(id, name);
        this.status = status;
        this.totalEarnings = totalEarnings;
        this.licenseNo = licenseNo;
        this.address = address;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.fullName = fullName;
        this.profilePhoto = profilePhoto;
        this.email = email;
        this.bankAccountNumber = bankAccountNumber;
        this.bankBranch = bankBranch;
        this.bankName = bankName;
    }

    // Standard Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalEarnings() { return totalEarnings; }
    public void setTotalEarnings(double totalEarnings) { this.totalEarnings = totalEarnings; }
    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
    public String getBankBranch() { return bankBranch; }
    public void setBankBranch(String bankBranch) { this.bankBranch = bankBranch; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    // UPDATED: Format for writing to the text file using a pipe '|' to safely handle commas in addresses
    @Override
    public String toString() {
        return id + "|" + name + "|" + status + "|" + totalEarnings + "|" + licenseNo + "|" + address + "|" +
                nic + "|" + contactNumber + "|" + fullName + "|" + profilePhoto + "|" + email + "|" +
                bankAccountNumber + "|" + bankBranch + "|" + bankName;
    }
}