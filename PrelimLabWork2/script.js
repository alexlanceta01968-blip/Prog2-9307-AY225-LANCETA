// Predefined values for Step 2
const VALID_USER = "Officer_Lanceta";
const VALID_PASS = "11102010";

// Step 3: Audio Implementation
const siren = new Audio('siren.mp3'); 

// Global variables for Step 5
let capturedUser = "";
let capturedTime = "";

function handleLogin() {
    const userInp = document.getElementById('username').value;
    const passInp = document.getElementById('password').value;

    if (userInp === VALID_USER && passInp === VALID_PASS) {
        // Step 4: Capture Timestamp (MM/DD/YYYY HH:MM:SS)
        const now = new Date();
        capturedTime = formatDate(now);
        capturedUser = userInp;

        // Transition to Dashboard
        document.getElementById('login-container').style.display = 'none';
        document.getElementById('dashboard').style.display = 'block';
        document.getElementById('welcome-msg').innerText = `Welcome, ${capturedUser}!`;
        document.getElementById('timestamp-display').innerText = capturedTime;

        // Change Browser Tab Title for the Officer
        document.title = "🚨 OFFICER ON DUTY";
    } else {
        // Step 3: Play 4-second siren on failure with LOOPING
        siren.currentTime = 0;
        siren.loop = true; // Loop enabled while alert is open
        siren.play();
        
        alert("INCORRECT PASSWORD! SIREN ACTIVATED.");
        
        // The Reset Strategy
        siren.pause();     
        siren.loop = false; // Resetting to false ensures clean logic for next try
    }
}

// Helper to get the exact format required in Step 4
function formatDate(date) {
    const mm = String(date.getMonth() + 1).padStart(2, '0');
    const dd = String(date.getDate()).padStart(2, '0');
    const yyyy = date.getFullYear();
    const hh = String(date.getHours()).padStart(2, '0');
    const min = String(date.getMinutes()).padStart(2, '0');
    const ss = String(date.getSeconds()).padStart(2, '0');
    return `${mm}/${dd}/${yyyy} ${hh}:${min}:${ss}`;
}

// Step 5: Generating Attendance Summary File
function generateReport() {
    const attendanceData = `Attendance Summary\n------------------\nUsername: ${capturedUser}\nTimestamp: ${capturedTime}`;
    
    // Create the .txt file using Blob API
    const blob = new Blob([attendanceData], { type: 'text/plain' });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = 'attendance_summary.txt';
    link.click();
}

// Show/Hide Password Toggle Logic
function togglePassword() {
    const passField = document.getElementById("password");
    if (passField.type === "password") {
        passField.type = "text";
    } else {
        passField.type = "password";
    }
}