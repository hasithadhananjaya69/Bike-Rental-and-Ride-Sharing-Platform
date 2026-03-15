// Load data as soon as the page opens
window.onload = function() {
    loadDrivers();
};

// READ: Fetch all drivers from backend
function loadDrivers() {
    fetch('/drivers/dashboard')
        .then(response => response.json()) // Dashboard returns JSON list
        .then(drivers => {
            const tableBody = document.getElementById('driverTableBody');
            tableBody.innerHTML = ''; // Clear table

            drivers.forEach(driver => {
                const row = `<tr>
                <td>${driver.id}</td>
                <td>${driver.fullName}</td>
                <td><span class="badge ${driver.status === 'Available' ? 'bg-success' : 'bg-warning'}">${driver.status}</span></td>
                <td>Rs. ${driver.totalEarnings}</td>
                <td>${driver.contactNumber}</td>
                <td>${driver.email}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="assignRide('${driver.id}')">Assign Ride</button>
                    <button class="btn btn-sm btn-success" onclick="addPayment('${driver.id}')">Add Pay</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteDriver('${driver.id}')">Delete</button>
                </td>
            </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}

// UPDATE: Assign a ride
function assignRide(id) {
    fetch('/drivers/assign-ride', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ driverId: id })
    })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            loadDrivers(); // Refresh table
        });
}

// UPDATE: Add payment
function addPayment(id) {
    const amountStr = prompt("Enter payment amount to add:");
    if (!amountStr) return; // Cancelled

    const amount = parseFloat(amountStr);
    if (isNaN(amount) || amount <= 0) {
        alert("Please enter a valid amount.");
        return;
    }

    fetch('/drivers/add-payment', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ driverId: id, amount: amount })
    })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            loadDrivers(); // Refresh table
        });
}

// DELETE: Remove driver
function deleteDriver(id) {
    if (confirm("Are you sure you want to delete driver " + id + "?")) {
        fetch('/drivers/delete', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ driverId: id })
        })
            .then(res => res.text())
            .then(msg => {
                alert(msg);
                loadDrivers(); // Refresh table
            });
    }
}