/*
 * PROJECT: Attendance Ledger (UPHSD Student Edition)
 * THEME: "As I've Written" (Parchment & Charcoal)
 * JAVA VERSION: JDK 25 (LTS)
 * STUDENT ID: 23-1269-840
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random; 

public class AttendanceTracker {

    public static void main(String[] args) {
        // Use System Look and Feel for clean window borders
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}

        // --- WINDOW SETUP ---
        JFrame frame = new JFrame("Official Attendance Ledger");
        frame.setSize(500, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // --- THEME DESIGN ---
        Color paperColor = new Color(250, 248, 240); 
        Color inkColor = new Color(40, 40, 40);      
        Font serifFont = new Font("Serif", Font.PLAIN, 16);
        Font titleFont = new Font("Serif", Font.BOLD | Font.ITALIC, 24);

        // --- MAIN PANEL ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(paperColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Header
        JLabel header = new JLabel("Attendance Record", JLabel.CENTER);
        header.setFont(titleFont);
        header.setForeground(inkColor);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, inkColor));
        mainPanel.add(header, BorderLayout.NORTH);

        // --- FORM AREA ---
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 15));
        formPanel.setBackground(paperColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Create Styled Fields
        JTextField nameField = createStyledField(serifFont, paperColor, inkColor);
        JTextField courseField = createStyledField(serifFont, paperColor, inkColor);
        JTextField timeInField = createStyledField(serifFont, paperColor, inkColor);
        JTextField eSigField = createStyledField(serifFont, paperColor, inkColor);

        // System Time Logic
        LocalDateTime now = LocalDateTime.now();
        timeInField.setText(now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy | HH:mm:ss")));
        timeInField.setEditable(false);

        // UPHSD Student ID Generation (Format: 23-1269-XXX)
        Random rand = new Random();
        int randomSuffix = rand.nextInt(900) + 100; 
        String uphsdID = "23-1269-" + randomSuffix; 
        eSigField.setText(uphsdID);
        eSigField.setEditable(false);

        // Add to layout with Titled Borders
        formPanel.add(wrapInTitledBorder(nameField, "Attendance Name", inkColor, serifFont));
        formPanel.add(wrapInTitledBorder(courseField, "Course / Year", inkColor, serifFont));
        formPanel.add(wrapInTitledBorder(timeInField, "Time In (System Generated)", inkColor, serifFont));
        formPanel.add(wrapInTitledBorder(eSigField, "E-Signature (UPHSD ID Format)", inkColor, serifFont));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- SUBMIT BUTTON ---
        JButton submitBtn = new JButton("SIGN & RECORD ENTRY");
        submitBtn.setFont(serifFont);
        submitBtn.setBackground(new Color(230, 225, 210));
        
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please complete the entry before signing.");
                return;
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter("attendance_log.txt", true))) {
                pw.println("--- UPHSD REGISTER ENTRY ---");
                pw.println("TIMESTAMP: " + timeInField.getText());
                pw.println("NAME:      " + name);
                pw.println("COURSE:    " + course);
                pw.println("STUDENT ID: " + uphsdID);
                pw.println("----------------------------\n");

                JOptionPane.showMessageDialog(frame, "Ledger signed! ID used: " + uphsdID);
                nameField.setText("");
                courseField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error writing to ledger.");
            }
        });

        mainPanel.add(submitBtn, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // --- HELPER METHODS ---

    private static JTextField createStyledField(Font font, Color bg, Color fg) {
        JTextField f = new JTextField();
        f.setFont(font); f.setBackground(bg); f.setForeground(fg);
        f.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return f;
    }

    private static JPanel wrapInTitledBorder(JTextField field, String title, Color color, Font font) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color, 1), title);
        tb.setTitleFont(font.deriveFont(12f));
        tb.setTitleColor(color);
        p.setBorder(tb);
        p.add(field);
        return p;
    }
}