/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airline.ticket.reservation.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLoginDialog extends JDialog {
    private JTextField empIdField;
    private JPasswordField passwordField;
    private boolean succeeded;

    public AdminLoginDialog(Frame parent) {
        super(parent, "Admin Login", true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Employee ID:"));
        empIdField = new JTextField(15);
        panel.add(empIdField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        panel.add(passwordField);

        add(panel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");

        btnPanel.add(loginBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            if (authenticate(empIdField.getText().trim(), new String(passwordField.getPassword()))) {
                succeeded = true;
               // JOptionPane.showMessageDialog(AdminLoginDialog.this, "Login successful!");
                dispose();
            } else {
                succeeded = false;
                JOptionPane.showMessageDialog(AdminLoginDialog.this, "Invalid Employee ID or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                empIdField.setText("");
                passwordField.setText("");
                empIdField.requestFocus();
            }
        });

        cancelBtn.addActionListener(e -> {
            succeeded = false;
            dispose();
        });

        pack();
        setLocationRelativeTo(parent);
    }

    private boolean authenticate(String empId, String password) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee_login WHERE Emp_ID = ? AND Password = ?")) {
            ps.setString(1, empId);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
