// Programmer Identifier: Lanceta, Alexander D. 23-1269-840

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class StudentRecordSystem extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField f1, f2, f3;

    public StudentRecordSystem() {
        // Requirement: Identity in Title
        this.setTitle("Records - Lanceta, Alexander D. 23-1269-840");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Table Setup
        model = new DefaultTableModel(new String[]{"ID", "Full Name", "Prelim Grade"}, 0);
        table = new JTable(model);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        // Input Panel
        JPanel p = new JPanel(new FlowLayout());
        f1 = new JTextField(5);  // ID
        f2 = new JTextField(12); // Name
        f3 = new JTextField(5);  // Grade
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");

        p.add(new JLabel("ID:")); p.add(f1);
        p.add(new JLabel("Name:")); p.add(f2);
        p.add(new JLabel("Grade:")); p.add(f3);
        p.add(addBtn); p.add(delBtn);
        this.add(p, BorderLayout.SOUTH);

        // CRUD: Create (Add row)
        addBtn.addActionListener(e -> {
            if (!f1.getText().trim().isEmpty() && !f2.getText().trim().isEmpty()) {
                model.addRow(new Object[]{f1.getText(), f2.getText(), f3.getText()});
                // Clear fields after adding
                f1.setText(""); f2.setText(""); f3.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "ID and Name are required!");
            }
        });

        // CRUD: Delete (Remove row)
        delBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        });

        // Load initial data
        loadCSVData();
    }

    private void loadCSVData() {
        // Requirement: Try-catch for File I/O
        // This looks for the file in the current working directory (the folder you are in)
        try (BufferedReader br = new BufferedReader(new FileReader("class_records.csv"))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Basic validation to prevent ArrayIndexOutOfBounds
                if (parts.length >= 7) {
                    // Mapping based on MOCK_DATA: ID(0), First(1) + Last(2), Prelim(6)
                    model.addRow(new Object[]{parts[0], parts[1] + " " + parts[2], parts[6]});
                }
            }
        } catch (IOException e) {
            System.out.println("Notice: class_records.csv not found in the current directory.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystem().setVisible(true));
    }
}