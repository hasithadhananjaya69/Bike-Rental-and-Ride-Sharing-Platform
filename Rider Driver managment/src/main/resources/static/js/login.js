document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent page reload

    // Get the values from the inputs
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    // 1. HARDCODED ADMIN CHECK
    if (name === "admin" && email === "admin@system.com") {
        alert("Admin Login Successful!");
        // FIXED: Pointing to your correctly named admin file
        window.location.href = "admin-dashboard.html";
        return;
    }

    // 2. NORMAL DRIVER LOGIN
    fetch('/drivers/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name, email: email })
    })
        .then(response => response.text())
        .then(data => {
            if (data.includes("Successful")) {
                alert(data);
                // Save the driver's name in the browser so the dashboard knows who to show
                localStorage.setItem('loggedInDriverName', name);
                window.location.href = "driver-dashboard.html";
            } else {
                alert(data); // Shows "Login Failed" message
            }
        })
        .catch(error => console.error('Error:', error));
});