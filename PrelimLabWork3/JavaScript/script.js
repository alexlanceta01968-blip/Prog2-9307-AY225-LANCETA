function calculateGrades() {
    // 1. Accept and validate numeric user inputs
    let attendance = parseFloat(prompt("Enter Attendance Score (0-100):"));
    let lw1 = parseFloat(prompt("Enter Lab Work 1 Grade:"));
    let lw2 = parseFloat(prompt("Enter Lab Work 2 Grade:"));
    let lw3 = parseFloat(prompt("Enter Lab Work 3 Grade:"));

    // Check if inputs are valid numbers
    if (isNaN(attendance) || isNaN(lw1) || isNaN(lw2) || isNaN(lw3)) {
        alert("Error: Please enter valid numbers for all fields.");
        return;
    }

    // 2. Apply weighted grading formulas accurately
    // Lab Work Average
    let lwAvg = (lw1 + lw2 + lw3) / 3;
    
    // Class Standing = (Attendance * 40%) + (Lab Average * 60%)
    let classStanding = (attendance * 0.40) + (lwAvg * 0.60);

    // 3. Determine required Prelim Exam score for targets
    // Formula: (Target - (Class Standing * 30%)) / 70%
    let reqPassing = (75 - (classStanding * 0.30)) / 0.70;
    let reqExcellent = (100 - (classStanding * 0.30)) / 0.70;

    // 4. Present clear and well-structured output results
    let report = "--- CALCULATION RESULTS ---\n\n" +
                 "Attendance Score: " + attendance.toFixed(2) + "\n" +
                 "Lab Work Average: " + lwAvg.toFixed(2) + "\n" +
                 "Class Standing: " + classStanding.toFixed(2) + "\n" +
                 "---------------------------\n" +
                 "Required Exam for Passing (75): " + Math.max(0, reqPassing).toFixed(2) + "\n" +
                 "Required Exam for Excellent (100): " + Math.max(0, reqExcellent).toFixed(2) + "\n\n";

    // Use conditional statements to evaluate academic standing
    if (reqPassing > 100) {
        report += "REMARK: It is impossible to pass this prelim period.";
    } else if (reqPassing <= 0) {
        report += "REMARK: You have already secured a passing grade!";
    } else {
        report += "REMARK: Stay focused, you can reach your target!";
    }

    alert(report);
}