package airline.ticket.reservation.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightFormDialog extends JDialog {
    private final JTextField flightNameField = new JTextField();
    private final JTextField sourceField = new JTextField();
    private final JTextField destinationField = new JTextField();
    private final JTextField classField = new JTextField();
    private final JTextField dateField = new JTextField();
    private final JTextField departureTimeField = new JTextField();
    private final JTextField arrivalTimeField = new JTextField();
    private final JTextField arrivalDateField = new JTextField();
    private final JTextField stopsField = new JTextField();
    private final JTextField transitionalPointField = new JTextField();
    private final JTextField baggageField = new JTextField();
    private final JTextField refundField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JTextField journeyHrField = new JTextField();
    private final JTextField sourceCountryField = new JTextField();
    private final JTextField destinationCountryField = new JTextField();
    private final JTextField journeyTextField = new JTextField();
    private final JTextField flightModelField = new JTextField();
    private final JTextField departAirportField = new JTextField();
    private final JTextField departTerminalField = new JTextField();
    private final JTextField arrivalAirportField = new JTextField();
    private final JTextField arrivalTerminalField = new JTextField();

    private final JButton saveButton = new JButton("Save");
    private final Integer flightId;

    public FlightFormDialog(JFrame parent, String title, Integer flightId, Runnable onSave) {
        this(parent, title, flightId, onSave, false);
    }

    public FlightFormDialog(JFrame parent, String title, Integer flightId, Runnable onSave, boolean viewOnly) {
        super(parent, title, true);
        this.flightId = flightId;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(23, 2, 10, 5));
        addField(formPanel, "Flight Name", flightNameField, viewOnly);
        addField(formPanel, "Source", sourceField, viewOnly);
        addField(formPanel, "Destination", destinationField, viewOnly);
        addField(formPanel, "Class", classField, viewOnly);
        addField(formPanel, "Date (YYYY-MM-DD)", dateField, viewOnly);
        addField(formPanel, "Departure Time (HH:MM)", departureTimeField, viewOnly);
        addField(formPanel, "Arrival Time (HH:MM)", arrivalTimeField, viewOnly);
        addField(formPanel, "Arrival Date (YYYY-MM-DD)", arrivalDateField, viewOnly);
        addField(formPanel, "No of Stops", stopsField, viewOnly);
        addField(formPanel, "Transitional Point", transitionalPointField, viewOnly);
        addField(formPanel, "Baggage Policy", baggageField, viewOnly);
        addField(formPanel, "Refundability", refundField, viewOnly);
        addField(formPanel, "Price", priceField, viewOnly);
        addField(formPanel, "Journey Time (hrs)", journeyHrField, viewOnly);
        addField(formPanel, "Source Country", sourceCountryField, viewOnly);
        addField(formPanel, "Destination Country", destinationCountryField, viewOnly);
        addField(formPanel, "Journey Time (text)", journeyTextField, viewOnly);
        addField(formPanel, "Flight Model", flightModelField, viewOnly);
        addField(formPanel, "Depart Airport Name", departAirportField, viewOnly);
        addField(formPanel, "Depart Terminal", departTerminalField, viewOnly);
        addField(formPanel, "Arrival Airport Name", arrivalAirportField, viewOnly);
        addField(formPanel, "Arrival Terminal", arrivalTerminalField, viewOnly);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        if (flightId != null) loadFlightDetails(flightId);

        JPanel buttonPanel = new JPanel();
        if (!viewOnly) {
            saveButton.addActionListener(e -> saveFlight(onSave));
            buttonPanel.add(saveButton);
        } else {
            JButton close = new JButton("Close");
            close.addActionListener(e -> dispose());
            buttonPanel.add(close);
        }

        add(buttonPanel, BorderLayout.SOUTH);
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void addField(JPanel panel, String label, JTextField field, boolean viewOnly) {
        panel.add(new JLabel(label + ":"));
        field.setEditable(!viewOnly);
        panel.add(field);
    }
    
private boolean validateForm() {
    StringBuilder errors = new StringBuilder();

    // Required fields
    JTextField[] requiredFields = {
        flightNameField, sourceField, destinationField, classField, dateField,
        departureTimeField, arrivalTimeField, arrivalDateField, stopsField,
        transitionalPointField, baggageField, refundField, priceField,
        journeyHrField, sourceCountryField, destinationCountryField,
        journeyTextField, flightModelField, departAirportField, departTerminalField,
        arrivalAirportField, arrivalTerminalField
    };

    for (JTextField field : requiredFields) {
        if (field.getText().trim().isEmpty()) {
            errors.append("- ").append(field.getToolTipText() != null ? field.getToolTipText() : "Missing field").append("\n");
        }
    }

    // Parse dates
    LocalDate flightDate = null, arrivalDate = null;
    try {
        flightDate = LocalDate.parse(dateField.getText().trim());
        if (flightDate.isBefore(LocalDate.now())) {
            errors.append("- Flight date cannot be in the past\n");
        }
    } catch (Exception e) {
        errors.append("- Invalid flight date format (YYYY-MM-DD)\n");
    }

    try {
        arrivalDate = LocalDate.parse(arrivalDateField.getText().trim());
    } catch (Exception e) {
        errors.append("- Invalid arrival date format (YYYY-MM-DD)\n");
    }

    // Time validation
    LocalTime departureTime = null, arrivalTime = null;
    try {
        departureTime = LocalTime.parse(departureTimeField.getText().trim());
    } catch (Exception e) {
        errors.append("- Invalid departure time format (HH:MM)\n");
    }

    try {
        arrivalTime = LocalTime.parse(arrivalTimeField.getText().trim());
    } catch (Exception e) {
        errors.append("- Invalid arrival time format (HH:MM)\n");
    }

    // Validate stop count
    try {
        int stops = Integer.parseInt(stopsField.getText().trim());
        if (stops < 0) errors.append("- Stops cannot be negative\n");
    } catch (Exception e) {
        errors.append("- Stops must be an integer\n");
    }

    // Price must be positive
    try {
        double price = Double.parseDouble(priceField.getText().trim());
        if (price < 0) errors.append("- Price cannot be negative\n");
    } catch (Exception e) {
        errors.append("- Invalid price format\n");
    }

    // Journey hour
    try {
        double hrs = Double.parseDouble(journeyHrField.getText().trim());
        if (hrs <= 0) errors.append("- Journey time must be greater than 0\n");
    } catch (Exception e) {
        errors.append("- Invalid journey hours\n");
    }

    // Country mismatch (basic realism)
    if (sourceCountryField.getText().equalsIgnoreCase(destinationCountryField.getText()) &&
        !sourceField.getText().equalsIgnoreCase(destinationField.getText())) {
        errors.append("- Domestic flights must have same city and country\n");
    }

    // Refund validation
    String refund = refundField.getText().trim().toLowerCase();
    if (!refund.equals("refundable") && !refund.equals("non-refundable")) {
        errors.append("- Refundability must be 'Refundable' or 'Non-Refundable'\n");
    }

    // Class validation
    String cls = classField.getText().trim().toLowerCase();
    if (!(cls.equals("economy") || cls.equals("business") || cls.equals("first"))) {
        errors.append("- Class must be 'Economy', 'Business', 'Premium' or 'First'\n");
    }

    // Arrival must not be before departure (same-day logic)
    if (flightDate != null && arrivalDate != null && departureTime != null && arrivalTime != null) {
        LocalDateTime dep = LocalDateTime.of(flightDate, departureTime);
        LocalDateTime arr = LocalDateTime.of(arrivalDate, arrivalTime);
        if (arr.isBefore(dep)) {
            errors.append("- Arrival time cannot be before departure time\n");
        }
    }

    // Show final errors
    if (errors.length() > 0) {
        JOptionPane.showMessageDialog(this, "Please fix the following issues:\n\n" + errors, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}



    private void loadFlightDetails(int id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM flight_details WHERE Flight_ID=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flightNameField.setText(rs.getString("Flight_Name"));
                sourceField.setText(rs.getString("Source"));
                destinationField.setText(rs.getString("Destination"));
                classField.setText(rs.getString("Class"));
                dateField.setText(rs.getString("Date"));
                departureTimeField.setText(rs.getString("Departure_Time"));
                arrivalTimeField.setText(rs.getString("Arrival_Time"));
                arrivalDateField.setText(rs.getString("Arrival_Date"));
                stopsField.setText(rs.getString("No_of_Stops"));
                transitionalPointField.setText(rs.getString("Transitional_Point"));
                baggageField.setText(rs.getString("Baggage_Policy"));
                refundField.setText(rs.getString("Refundability"));
                priceField.setText(rs.getString("Price"));
                journeyHrField.setText(rs.getString("Journey_Time"));
                sourceCountryField.setText(rs.getString("Source_Country"));
                destinationCountryField.setText(rs.getString("Destination_Country"));
                journeyTextField.setText(rs.getString("Journey_Time(txt)"));
                flightModelField.setText(rs.getString("Flight_Model"));
                departAirportField.setText(rs.getString("DepartAirportName"));
                departTerminalField.setText(rs.getString("DepartTerminal"));
                arrivalAirportField.setText(rs.getString("ArrivalAirportName"));
                arrivalTerminalField.setText(rs.getString("ArrivalTerminal"));
            } else {
                JOptionPane.showMessageDialog(this, "No flight found with ID: " + id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load flight details: " + ex.getMessage());
        }
    }

    private void saveFlight(Runnable onSave) {
        if (!validateForm()) return;
        try (Connection conn = getConnection()) {
            String sql;
            boolean isInsert = (flightId == null);
            if (isInsert) {
                sql = "INSERT INTO flight_details (Flight_Name, Source, Destination, Class, Date, Departure_Time, Arrival_Time, Arrival_Date, No_of_Stops, Transitional_Point, Baggage_Policy, Refundability, Price, Journey_Time, Source_Country, Destination_Country, `Journey_Time(txt)`, Flight_Model, DepartAirportName, DepartTerminal, ArrivalAirportName, ArrivalTerminal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE flight_details SET Flight_Name=?, Source=?, Destination=?, Class=?, Date=?, Departure_Time=?, Arrival_Time=?, Arrival_Date=?, No_of_Stops=?, Transitional_Point=?, Baggage_Policy=?, Refundability=?, Price=?, Journey_Time=?, Source_Country=?, Destination_Country=?, `Journey_Time(txt)`=?, Flight_Model=?, DepartAirportName=?, DepartTerminal=?, ArrivalAirportName=?, ArrivalTerminal=? WHERE Flight_ID=?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, flightNameField.getText().trim());
            ps.setString(2, sourceField.getText().trim());
            ps.setString(3, destinationField.getText().trim());
            ps.setString(4, classField.getText().trim());
            ps.setString(5, dateField.getText().trim());
            ps.setString(6, departureTimeField.getText().trim());
            ps.setString(7, arrivalTimeField.getText().trim());
            ps.setString(8, arrivalDateField.getText().trim());
            ps.setString(9, stopsField.getText().trim());
            ps.setString(10, transitionalPointField.getText().trim());
            ps.setString(11, baggageField.getText().trim());
            ps.setString(12, refundField.getText().trim());
            ps.setString(13, priceField.getText().trim());
            ps.setString(14, journeyHrField.getText().trim());
            ps.setString(15, sourceCountryField.getText().trim());
            ps.setString(16, destinationCountryField.getText().trim());
            ps.setString(17, journeyTextField.getText().trim());
            ps.setString(18, flightModelField.getText().trim());
            ps.setString(19, departAirportField.getText().trim());
            ps.setString(20, departTerminalField.getText().trim());
            ps.setString(21, arrivalAirportField.getText().trim());
            ps.setString(22, arrivalTerminalField.getText().trim());

            if (!isInsert) {
                ps.setInt(23, flightId);
            }

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Flight saved successfully.");
            dispose();
            if (onSave != null) onSave.run();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save flight: " + ex.getMessage());
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
    }
}
