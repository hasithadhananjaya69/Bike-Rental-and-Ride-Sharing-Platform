document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent page reload

    // Get the values from the inputs
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;

    // Send POST request to your Spring Boot API
    fetch('/drivers/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name, email: email })
    })
        .then(response => response.text()) // Backend returns plain text
        .then(data => {
            if (data.includes("Successful")) {
                alert(data);
                window.location.href = "dashboard.html"; // Redirect to dashboard
            } else {
                alert(data); // Shows "Login Failed" message
            }
        })
        .catch(error => console.error('Error:', error));
});