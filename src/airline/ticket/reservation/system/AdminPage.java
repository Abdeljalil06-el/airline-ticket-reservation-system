package airline.ticket.reservation.system;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class AdminPage extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel, sidebar;
    private JTable flightsTable, bookingsTable;
    private DefaultTableModel flightsModel, bookingsModel;

    public AdminPage() {
        try { UIManager.setLookAndFeel(new FlatIntelliJLaf()); } catch (Exception ignored) {}
        setTitle("Admin Dashboard");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new JPanel(new GridLayout(0, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(25, 25, 60));
        sidebar.setBorder(new EmptyBorder(30, 10, 30, 10));

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        String[] menuItems = {"Manage Flights", "Bookings", "Book Ticket", "Logout"};
for (String item : menuItems) {
    JButton btn = createSidebarButton(item);
    sidebar.add(btn);
    switch (item) {
        case "Manage Flights" -> contentPanel.add(createFlightsPanel(), item);
        case "Bookings" -> contentPanel.add(createBookingsPanel(), item);
        case "Book Ticket" -> contentPanel.add(createBookTicketPanel(), item);
        // Do NOT add panel for Logout
    }
    btn.addActionListener(e -> {
        if(item.equals("Logout")) {
            logout();           // call logout directly on Logout button click
        } else {
            cardLayout.show(contentPanel, item);
            highlightActiveButton(btn);
        }
    });
}


        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);

        loadFlightData();
        loadBookingData();
    }

    private void highlightActiveButton(JButton active) {
        for (Component comp : sidebar.getComponents()) {
            if (comp instanceof JButton btn) btn.setBackground(new Color(50, 50, 120));
        }
        active.setBackground(new Color(100, 100, 180));
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 50, 120));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { button.setBackground(new Color(70, 70, 150)); }
            public void mouseExited(MouseEvent evt) {
                if (!button.getBackground().equals(new Color(100, 100, 180))) {
                    button.setBackground(new Color(50, 50, 120));
                }
            }
        });
        return button;
    }

    private JPanel createFlightsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        flightsModel = new DefaultTableModel();
        flightsModel.setColumnIdentifiers(new String[]{"Flight ID", "Airline", "From", "To", "Departure", "Price"});
        flightsTable = new JTable(flightsModel);
        JScrollPane scrollPane = new JScrollPane(flightsTable);

        JPanel btnPanel = new JPanel();
        btnPanel.add(createFlatButton("Add", this::addFlight));
        btnPanel.add(createFlatButton("Edit", this::editFlight));
        btnPanel.add(createFlatButton("Delete", this::deleteFlight));
        btnPanel.add(createFlatButton("Details", this::showFlightDetails));

        panel.add(new JLabel("Flights List", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        bookingsModel = new DefaultTableModel();
        bookingsModel.setColumnIdentifiers(new String[]{"Username", "Flight ID", "From", "To", "Departure", "Tickets", "Total Price", "Booked At"});
        bookingsTable = new JTable(bookingsModel);
        JScrollPane scrollPane = new JScrollPane(bookingsTable);

        JPanel btnPanel = new JPanel();
        btnPanel.add(createFlatButton("Cancel", this::deleteBooking));

        panel.add(new JLabel("All Bookings", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookTicketPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        panel.setBorder(new EmptyBorder(30, 150, 30, 150));

        JTextField userField = new JTextField();
        JTextField flightIdField = new JTextField();
        JTextField fromField = new JTextField(); fromField.setEditable(false);
        JTextField toField = new JTextField(); toField.setEditable(false);
        JTextField timeField = new JTextField(); timeField.setEditable(false);
        JTextField classField = new JTextField(); classField.setEditable(false);
        JTextField priceField = new JTextField(); priceField.setEditable(false);
        JComboBox<Integer> ticketCount = new JComboBox<>();
        JLabel totalPriceLabel = new JLabel();

        for (int i = 1; i <= 8; i++) ticketCount.addItem(i);

        flightIdField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try (Connection conn = getConnection();
                     PreparedStatement ps = conn.prepareStatement("SELECT Source, Destination, Departure_Time, Class, Price FROM flight_details WHERE Flight_ID=?")) {
                    ps.setInt(1, Integer.parseInt(flightIdField.getText()));
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        fromField.setText(rs.getString(1));
                        toField.setText(rs.getString(2));
                        timeField.setText(rs.getString(3));
                        classField.setText(rs.getString(4));
                        priceField.setText(rs.getString(5));
                        totalPriceLabel.setText("");
                    }
                } catch (Exception ignored) {}
            }
        });

        ticketCount.addActionListener(e -> {
            try {
                int price = Integer.parseInt(priceField.getText());
                int tickets = (int) ticketCount.getSelectedItem();
                double total = price * tickets * 1.03;
                totalPriceLabel.setText("Total: " + (int) total);
            } catch (Exception ignored) {}
        });

        JButton bookBtn = createFlatButton("Book Now", () -> {
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO bookings (Username, Flight_ID, No_of_tickets, Total_price) VALUES (?, ?, ?, ?);")) {
                int tickets = (int) ticketCount.getSelectedItem();
                int price = Integer.parseInt(priceField.getText());
                double total = tickets * price * 1.03;
                String username = userField.getText().trim();
                if (!usernameExists(username)) username += "(Admin)";
                ps.setString(1, username);
                ps.setInt(2, Integer.parseInt(flightIdField.getText()));
                ps.setInt(3, tickets);
                ps.setInt(4, (int) total);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Booking successful");
                loadBookingData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Booking failed");
            }
        });

        panel.add(new JLabel("Username:")); panel.add(userField);
        panel.add(new JLabel("Flight ID:")); panel.add(flightIdField);
        panel.add(new JLabel("From:")); panel.add(fromField);
        panel.add(new JLabel("To:")); panel.add(toField);
        panel.add(new JLabel("Departure Time:")); panel.add(timeField);
        panel.add(new JLabel("Class:")); panel.add(classField);
        panel.add(new JLabel("Price:")); panel.add(priceField);
        panel.add(new JLabel("Tickets:")); panel.add(ticketCount);
        panel.add(totalPriceLabel); panel.add(bookBtn);
        return panel;
    }
    
    private void logout() {
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        this.dispose(); // Close current AdminPage
        new LoginOption().setVisible(true); // Redirect to LoginOption
    }
}


    private JButton createFlatButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 130, 200));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> action.run());
        return button;
    }

    private void loadFlightData() {
        flightsModel.setRowCount(0);
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Flight_ID, Flight_Name, Source, Destination, CONCAT(Date, ' ', Departure_Time), Price FROM flight_details")) {
            while (rs.next()) {
                flightsModel.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadBookingData() {
        bookingsModel.setRowCount(0);
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT b.Username, b.Flight_ID, f.Source, f.Destination, CONCAT(f.Date, ' ', f.Departure_Time), b.No_of_tickets, b.Total_price, b.Date_Time FROM bookings b JOIN flight_details f ON b.Flight_ID = f.Flight_ID")) {
            while (rs.next()) {
                bookingsModel.addRow(new Object[]{rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8)});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deleteBooking() {
        int row = bookingsTable.getSelectedRow();
        if (row == -1) return;
        String username = (String) bookingsModel.getValueAt(row, 0);
        int flightId = (int) bookingsModel.getValueAt(row, 1);
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM bookings WHERE Username=? AND Flight_ID=?")) {
            ps.setString(1, username);
            ps.setInt(2, flightId);
            ps.executeUpdate();
            loadBookingData();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private boolean usernameExists(String username) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_details WHERE Username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
    }

    // Add Flight Dialog
    private void addFlight() {
        new FlightFormDialog(this, "Add Flight", null, this::loadFlightData);
    }

    private void editFlight() {
        int row = flightsTable.getSelectedRow();
        if (row == -1) return;
        int flightId = (int) flightsModel.getValueAt(row, 0);
        new FlightFormDialog(this, "Edit Flight", flightId, this::loadFlightData);
    }

    private void deleteFlight() {
        int row = flightsTable.getSelectedRow();
        if (row == -1) return;
        int flightId = (int) flightsModel.getValueAt(row, 0);
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM flight_details WHERE Flight_ID=?")) {
            ps.setInt(1, flightId);
            ps.executeUpdate();
            loadFlightData();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void showFlightDetails() {
        int row = flightsTable.getSelectedRow();
        if (row == -1) return;
        int flightId = (int) flightsModel.getValueAt(row, 0);
        new FlightFormDialog(this, "Flight Details", flightId, null, true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminPage::new);
    }
}
