document.getElementById('registerForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Map inputs to the DriverRequest JSON structure
    const driverData = {
        id: document.getElementById('id').value,
        name: document.getElementById('name').value,
        fullName: document.getElementById('fullName').value,
        email: document.getElementById('email').value,
        nic: document.getElementById('nic').value,
        contactNumber: document.getElementById('contactNumber').value,
        licenseNo: document.getElementById('licenseNo').value,
        address: document.getElementById('address').value,
        profilePhoto: document.getElementById('profilePhoto').value || "default.jpg",
        bankAccountNumber: document.getElementById('bankAccountNumber').value,
        bankBranch: document.getElementById('bankBranch').value,
        bankName: document.getElementById('bankName').value
    };

    fetch('/drivers/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(driverData)
    })
        .then(response => response.text())
        .then(data => {
            alert(data); // Show success message
            window.location.href = "index.html"; // Go to login page
        })
        .catch(error => console.error('Error:', error));
});