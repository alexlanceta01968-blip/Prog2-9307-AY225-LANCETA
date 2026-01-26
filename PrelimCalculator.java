import javax.swing.JOptionPane;

public class PrelimCalculator {
    public static void main(String[] args) {
        // 1. Accept and validate numeric user inputs via UI dialogs
        String attendanceInput = JOptionPane.showInputDialog("Enter Attendance Score (0-100):");
        double attendance = Double.parseDouble(attendanceInput);

        double lw1 = Double.parseDouble(JOptionPane.showInputDialog("Enter Lab Work 1 Grade:"));
        double lw2 = Double.parseDouble(JOptionPane.showInputDialog("Enter Lab Work 2 Grade:"));
        double lw3 = Double.parseDouble(JOptionPane.showInputDialog("Enter Lab Work 3 Grade:"));

        // 2. Apply weighted grading formulas
        double lwAvg = (lw1 + lw2 + lw3) / 3;
        double classStanding = (attendance * 0.40) + (lwAvg * 0.60);

        // 3. Compute required Exam scores
        double reqPassing = (75 - (classStanding * 0.30)) / 0.70;
        double reqExcellent = (100 - (classStanding * 0.30)) / 0.70;

        // 4. Build the output message
        String results = String.format(
            "--- CALCULATION RESULTS ---\n" +
            "Attendance: %.2f\n" +
            "Lab Work Average: %.2f\n" +
            "Class Standing: %.2f\n" +
            "---------------------------\n" +
            "To PASS (75), you need: %.2f\n" +
            "For EXCELLENT (100), you need: %.2f\n\n",
            attendance, lwAvg, classStanding, 
            Math.max(0, reqPassing), Math.max(0, reqExcellent)
        );

        // Add Remarks based on conditional logic
        if (reqPassing > 100) {
            results += "REMARK: Goal unreachable this period.";
        } else {
            results += "REMARK: Stay focused! You can do it.";
        }

        // 5. Display the final result in a UI Window
        JOptionPane.showMessageDialog(null, results, "Prelim Target Calculator", JOptionPane.INFORMATION_MESSAGE);
    }
}