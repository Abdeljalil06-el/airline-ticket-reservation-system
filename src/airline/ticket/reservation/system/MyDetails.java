package airline.ticket.reservation.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.regex.Pattern;

public class MyDetails extends JFrame {
    private JTextField txtFirstName, txtLastName, txtContactNo, txtPassportNo;
    private JRadioButton rbtnMale, rbtnFemale;
    private JComboBox<String> cmbCountry;
    private JPasswordField txtNewPassword, txtOldPassword;
    private JLabel lblFirstNameValue, lblLastNameValue, lblGenderValue, lblDOBValue, lblCountryValue, lblContactNoValue, lblPassportNoValue;
    private JLabel lblUsernameValue;
    private final String username;
    private JButton btnModify, btnSave, btnBack;
    private boolean isInModifyMode = false;

    private JDateChooser dateChooserDOB; // New date chooser for DOB
    private JLabel lblNewPassword, lblOldPassword; // Make labels accessible

    public MyDetails() {
        username = SessionManager.getUsername();
        setTitle("My Details");
        setSize(1000, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set a custom content pane with gradient background
        setContentPane(new GradientPanel());

        // Use GridBagLayout for main layout
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12); // Increased left/right padding
        gbc.anchor = GridBagConstraints.WEST;

        // Labels
        JLabel lblUsername = createHeaderLabel("Username:");
        JLabel lblFirstName = createHeaderLabel("First Name:");
        JLabel lblLastName = createHeaderLabel("Last Name:");
        JLabel lblGender = createHeaderLabel("Gender:");
        JLabel lblDOB = createHeaderLabel("Date of Birth:");
        JLabel lblCountry = createHeaderLabel("Country:");
        JLabel lblContactNo = createHeaderLabel("Contact No:");
        JLabel lblPassportNo = createHeaderLabel("Passport No:");
        lblNewPassword = createHeaderLabel("New Password:");
        lblOldPassword = createHeaderLabel("Current Password:");

        txtFirstName = new JTextField(20);
        txtLastName = new JTextField(20);
        txtContactNo = new JTextField(20);
        txtPassportNo = new JTextField(20);

        rbtnMale = new JRadioButton("Male");
        rbtnFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        String[] countries = {"Australia", "Bangladesh", "Canada", "India", "Indonesia", "Italy", "Japan", "Malaysia", "Maldives", "Myanmar", "Nepal", "Netherlands", "Oman", "Pakistan", "Qatar", "Singapore", "Srilanka", "Thailand", "Turkey", "United Arab Emirates(UAE)", "United Kingdom(UK)", "United States(US)"};
        cmbCountry = new JComboBox<>(countries);

        txtNewPassword = new JPasswordField(20);
        txtOldPassword = new JPasswordField(20);

        lblUsernameValue = createValueLabel();
        lblFirstNameValue = createValueLabel();
        lblLastNameValue = createValueLabel();
        lblGenderValue = createValueLabel();
        lblDOBValue = createValueLabel();
        lblCountryValue = createValueLabel();
        lblContactNoValue = createValueLabel();
        lblPassportNoValue = createValueLabel();

        dateChooserDOB = new JDateChooser();
        dateChooserDOB.setDateFormatString("yyyy-MM-dd");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; add(lblUsername, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; add(wrapInBox(lblUsernameValue), gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblFirstName, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblFirstNameValue), gbc);
        add(txtFirstName, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblLastName, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblLastNameValue), gbc);
        add(txtLastName, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblGender, gbc);
        gbc.gridx = 1;
        add(wrapInBox(lblGenderValue), gbc);
        add(rbtnMale, gbc);
        gbc.gridx = 2;
        add(rbtnFemale, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblDOB, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblDOBValue), gbc);
        add(dateChooserDOB, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblCountry, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblCountryValue), gbc);
        add(cmbCountry, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblContactNo, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblContactNoValue), gbc);
        add(txtContactNo, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblPassportNo, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        add(wrapInBox(lblPassportNoValue), gbc);
        add(txtPassportNo, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblNewPassword, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; add(txtNewPassword, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(lblOldPassword, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; add(txtOldPassword, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; add(btnModify = createStyledButton("Modify"), gbc);
        gbc.gridx = 1; add(btnSave = createStyledButton("Save"), gbc);
        gbc.gridx = 2; add(btnBack = createStyledButton("Back"), gbc);

        setModifyMode(false);
        loadUserDetails();

        btnModify.addActionListener(e -> setModifyMode(true));

        btnSave.addActionListener(e -> {
            if (!validateInputs()) return;

            String oldPassword = new String(txtOldPassword.getPassword());
            if (!verifyPassword(oldPassword)) {
                JOptionPane.showMessageDialog(this, "Incorrect current password.");
                return;
            }

            String newPass = new String(txtNewPassword.getPassword()).trim();
            updateDetails(newPass.isEmpty() ? null : newPass);
            loadUserDetails();
            setModifyMode(false);
        });

        btnBack.addActionListener(e -> {
            if (isInModifyMode) {
                loadUserDetails();
                setModifyMode(false);
            } else {
                dispose();
                new MainPage().setVisible(true);
            }
        });

        setVisible(true);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // left padding 10px
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 14f));
        return lbl;
    }
    private JLabel createValueLabel() {
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8)); // padding inside the box
        return lbl;
    }
    private JPanel wrapInBox(JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panel.setOpaque(false);
        return panel;
    }
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 120, 215)); // nice blue
        btn.setFont(btn.getFont().deriveFont(Font.BOLD, 14f));
        btn.setBorder(new RoundedBorder(8));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(30, 144, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0, 120, 215));
            }
        });
        return btn;
    }
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius / 2;
            return insets;
        }
    }
    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(200, 220, 255);
            Color color2 = new Color(100, 150, 255);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
    private void setModifyMode(boolean modify) {
        isInModifyMode = modify;

        txtFirstName.setVisible(modify);
        txtLastName.setVisible(modify);
        dateChooserDOB.setVisible(modify);
        txtContactNo.setVisible(modify);
        txtPassportNo.setVisible(modify);
        rbtnMale.setVisible(modify);
        rbtnFemale.setVisible(modify);
        cmbCountry.setVisible(modify);

        txtNewPassword.setVisible(modify);
        txtOldPassword.setVisible(modify);
        lblNewPassword.setVisible(modify);
        lblOldPassword.setVisible(modify);

        lblFirstNameValue.setVisible(!modify);
        lblLastNameValue.setVisible(!modify);
        lblGenderValue.setVisible(!modify);
        lblDOBValue.setVisible(!modify);
        lblCountryValue.setVisible(!modify);
        lblContactNoValue.setVisible(!modify);
        lblPassportNoValue.setVisible(!modify);

        btnModify.setVisible(!modify);
        btnSave.setVisible(modify);
        btnBack.setText(modify ? "Cancel" : "Back");
    }

    private void loadUserDetails() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_details WHERE Username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lblUsernameValue.setText(rs.getString("Username"));

                String firstName = rs.getString("FirstName");
                lblFirstNameValue.setText(firstName);
                txtFirstName.setText(firstName);

                String lastName = rs.getString("LastName");
                lblLastNameValue.setText(lastName);
                txtLastName.setText(lastName);

                String gender = rs.getString("Gender");
                lblGenderValue.setText(gender);
                rbtnMale.setSelected("Male".equalsIgnoreCase(gender));
                rbtnFemale.setSelected("Female".equalsIgnoreCase(gender));

                String dob = rs.getString("DateOfBirth");
                lblDOBValue.setText(dob);
                try {
                    Date parsedDate = java.sql.Date.valueOf(dob);
                    dateChooserDOB.setDate(parsedDate);
                } catch (Exception ex) {
                    dateChooserDOB.setDate(null);
                }

                String country = rs.getString("Country");
                lblCountryValue.setText(country);
                cmbCountry.setSelectedItem(country);

                String contact = rs.getString("ContactNo");
                lblContactNoValue.setText(contact);
                txtContactNo.setText(contact);

                String passport = rs.getString("PassportNo");
                lblPassportNoValue.setText(passport);
                txtPassportNo.setText(passport);

                clearPasswords();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading details: " + ex.getMessage());
        }
    }

    private void clearPasswords() {
        txtOldPassword.setText("");
        txtNewPassword.setText("");
    }

    private boolean verifyPassword(String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT Password FROM user_details WHERE Username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return password.equals(rs.getString("Password"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error verifying password: " + ex.getMessage());
        }
        return false;
    }

    private boolean validateInputs() {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        Date dob = dateChooserDOB.getDate();
        String country = (String) cmbCountry.getSelectedItem();
        String contactNo = txtContactNo.getText().trim();
        String passportNo = txtPassportNo.getText().trim();

        if (firstName.isEmpty() || !Pattern.matches("[a-zA-Z]+", firstName)) {
            JOptionPane.showMessageDialog(this, "Invalid first name.");
            return false;
        }
        if (lastName.isEmpty() || !Pattern.matches("[a-zA-Z]+", lastName)) {
            JOptionPane.showMessageDialog(this, "Invalid last name.");
            return false;
        }
        if (dob == null || Period.between(LocalDate.of(dob.getYear() + 1900, dob.getMonth() + 1, dob.getDate()), LocalDate.now()).getYears() < 18) {
            JOptionPane.showMessageDialog(this, "You must be at least 18 years old.");
            return false;
        }
        if (country == null || country.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a country.");
            return false;
        }
        if (!Pattern.matches("\\d{10}", contactNo)) {
            JOptionPane.showMessageDialog(this, "Contact number must be exactly 10 digits.");
            return false;
        }
        if (!passportNo.matches("^[a-zA-Z0-9]{6,9}$")) {
            JOptionPane.showMessageDialog(this, "Invalid passport number.");
            return false;
        }

        String newPass = new String(txtNewPassword.getPassword()).trim();
        if (!newPass.isEmpty()) {
            if (newPass.length() < 6) {
                JOptionPane.showMessageDialog(this, "New password must be at least 6 characters.");
                return false;
            }
        }
        String oldPass = new String(txtOldPassword.getPassword()).trim();
        if (oldPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter current password to save changes.");
            return false;
        }
        return true;
    }

    private void updateDetails(String newPassword) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
             PreparedStatement stmt = conn.prepareStatement("UPDATE user_details SET FirstName=?, LastName=?, Gender=?, DateOfBirth=?, Country=?, ContactNo=?, PassportNo=?, Password=IFNULL(?, Password) WHERE Username=?")) {

            stmt.setString(1, txtFirstName.getText().trim());
            stmt.setString(2, txtLastName.getText().trim());
            stmt.setString(3, rbtnMale.isSelected() ? "Male" : "Female");
            stmt.setDate(4, new java.sql.Date(dateChooserDOB.getDate().getTime()));
            stmt.setString(5, (String) cmbCountry.getSelectedItem());
            stmt.setString(6, txtContactNo.getText().trim());
            stmt.setString(7, txtPassportNo.getText().trim());
            stmt.setString(8, newPassword);
            stmt.setString(9, username);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Details updated successfully!");
                clearPasswords();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating details: " + ex.getMessage());
        }
    }

    // For testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyDetails());
    }
}
