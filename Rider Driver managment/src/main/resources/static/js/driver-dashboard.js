window.onload = function() {
    // Get the name of the driver who logged in
    const loggedInName = localStorage.getItem('loggedInDriverName');

    if (!loggedInName) {
        window.location.href = "index.html"; // Security: Kick them out if not logged in
        return;
    }

    // Fetch all drivers, but filter to only show THIS driver
    fetch('/drivers/dashboard')
        .then(response => response.json())
        .then(drivers => {
            // Find the driver whose name matches the logged-in name
            const myProfile = drivers.find(d => d.name.toLowerCase() === loggedInName.toLowerCase());

            if (myProfile) {
                // Populate the HTML with their personal data
                document.getElementById('driverName').innerText = "Welcome, " + myProfile.fullName;
                document.getElementById('driverId').innerText = myProfile.id;
                document.getElementById('driverEarnings').innerText = myProfile.totalEarnings;
                document.getElementById('driverContact').innerText = myProfile.contactNumber;
                document.getElementById('driverEmail').innerText = myProfile.email;

                const statusSpan = document.getElementById('driverStatus');
                statusSpan.innerText = myProfile.status;
                if (myProfile.status === 'Available') {
                    statusSpan.className = 'badge bg-success';
                } else {
                    statusSpan.className = 'badge bg-warning text-dark';
                }
            } else {
                alert("Profile data not found!");
            }
        })
        .catch(error => console.error('Error fetching data:', error));
};