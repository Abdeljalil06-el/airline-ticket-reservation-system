package airline.ticket.reservation.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MyBookings extends JFrame {

    private JPanel bookingsPanel;
    private JScrollPane scrollPane;
    private String username;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/horizon";
    private static final String USER = "root";
    private static final String PASS = "";

    public MyBookings() {
        username = SessionManager.getUsername();
        setTitle("My Bookings");
        setSize(1000, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        bookingsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bookingsPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(bookingsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Fixed height panel for scrollPane (1/4 of frame height)
        int fixedHeight = getHeight() / 4;
        JPanel fixedHeightPanel = new JPanel(new BorderLayout());
        fixedHeightPanel.setPreferredSize(new Dimension(getWidth() - 40, fixedHeight));
        fixedHeightPanel.add(scrollPane, BorderLayout.CENTER);

        loadBookings();

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(100, 100, 100));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(100, 35));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(backButton);

        JPanel container = new JPanel(new BorderLayout());
        container.add(fixedHeightPanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(container);

        backButton.addActionListener(e -> {
            dispose();
            new MainPage().setVisible(true);
        });

        setVisible(true);
    }

    private void loadBookings() {
        bookingsPanel.removeAll();
        ArrayList<Booking> bookings = fetchBookingsFromDB();

        if (bookings.isEmpty()) {
            JLabel emptyLabel = new JLabel("No bookings found.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            emptyLabel.setForeground(Color.GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bookingsPanel.setLayout(new BorderLayout());
            bookingsPanel.add(emptyLabel, BorderLayout.CENTER);
        } else {
            bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
            for (Booking booking : bookings) {
                bookingsPanel.add(createBookingPanel(booking));
                bookingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            // Fill remaining space to keep fixed height if less bookings
            int minBookings = 4; // assuming 4 bookings fill the area nicely
            int toAdd = minBookings - bookings.size();
            for (int i = 0; i < toAdd; i++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setOpaque(false);
                emptyPanel.setPreferredSize(new Dimension(0, 80)); // approx booking panel height + margin
                bookingsPanel.add(emptyPanel);
            }
        }

        bookingsPanel.revalidate();
        bookingsPanel.repaint();
    }

    private ArrayList<Booking> fetchBookingsFromDB() {
        ArrayList<Booking> bookings = new ArrayList<>();

        String sql = "SELECT b.Flight_ID, b.No_of_tickets, b.Total_price, b.Date_Time, " +
                "f.Flight_Name, f.Flight_Model, f.Source, f.DepartAirportName, f.Source_Country, " +
                "f.Destination, f.ArrivalAirportName, f.Destination_Country, f.Class, f.Date, f.Departure_Time, " +
                "f.Arrival_Date, f.Arrival_Time, f.Journey_Time, f.No_of_Stops, f.Transitional_Point, " +
                "f.Baggage_Policy, f.Refundability, f.Price " +
                "FROM bookings b JOIN flight_details f ON b.Flight_ID = f.Flight_ID " +
                "WHERE b.Username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.flightID = rs.getInt("Flight_ID");
                booking.noOfTickets = rs.getInt("No_of_tickets");
                booking.totalPrice = rs.getDouble("Total_price");
                booking.dateTime = rs.getTimestamp("Date_Time");

                booking.flightName = rs.getString("Flight_Name");
                booking.flightModel = rs.getString("Flight_Model");
                booking.source = rs.getString("Source");
                booking.departAirportName = rs.getString("DepartAirportName");
                booking.sourceCountry = rs.getString("Source_Country");
                booking.destination = rs.getString("Destination");
                booking.arrivalAirportName = rs.getString("ArrivalAirportName");
                booking.destinationCountry = rs.getString("Destination_Country");
                booking.flightClass = rs.getString("Class");
                booking.flightDate = rs.getDate("Date");
                booking.departureTime = rs.getTime("Departure_Time").toString();
                booking.arrivalDate = rs.getDate("Arrival_Date");
                booking.arrivalTime = rs.getTime("Arrival_Time").toString();
                booking.journeyTimeTxt = rs.getString("Journey_Time");
                booking.noOfStops = rs.getInt("No_of_Stops");
                booking.transitionalPoint = rs.getString("Transitional_Point");
                booking.baggagePolicy = rs.getString("Baggage_Policy");
                booking.refundability = rs.getString("Refundability");
                booking.pricePerTicket = rs.getDouble("Price");

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return bookings;
    }

    private JPanel createBookingPanel(Booking booking) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(10, 10, 10, 10)));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        // Added departure date and time here
        String basicInfo = String.format(
                "<html><b>%s → %s</b> &nbsp;&nbsp;&nbsp; Departure: %s %s &nbsp;&nbsp;&nbsp; Tickets: %d &nbsp;&nbsp;&nbsp; Total: $%.2f</html>",
                booking.source, booking.destination, booking.flightDate.toString(), booking.departureTime, booking.noOfTickets, booking.totalPrice);
        JLabel basicInfoLabel = new JLabel(basicInfo);
        basicInfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JButton toggleButton = new JButton("Details ▼");
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(new Color(70, 130, 180));
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        headerPanel.add(basicInfoLabel, BorderLayout.WEST);
        headerPanel.add(toggleButton, BorderLayout.EAST);

        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 8));
        detailsPanel.setOpaque(false);
        detailsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        detailsPanel.add(new JLabel("Flight Name:"));
        detailsPanel.add(new JLabel(booking.flightName));
        detailsPanel.add(new JLabel("Flight Model:"));
        detailsPanel.add(new JLabel(booking.flightModel));
        detailsPanel.add(new JLabel("Departure Airport:"));
        detailsPanel.add(new JLabel(booking.departAirportName + " (" + booking.sourceCountry + ")"));
        detailsPanel.add(new JLabel("Arrival Airport:"));
        detailsPanel.add(new JLabel(booking.arrivalAirportName + " (" + booking.destinationCountry + ")"));
        detailsPanel.add(new JLabel("Class:"));
        detailsPanel.add(new JLabel(booking.flightClass));
        detailsPanel.add(new JLabel("Flight Date:"));
        detailsPanel.add(new JLabel(booking.flightDate.toString()));
        detailsPanel.add(new JLabel("Departure Time:"));
        detailsPanel.add(new JLabel(booking.departureTime));
        detailsPanel.add(new JLabel("Arrival Date:"));
        detailsPanel.add(new JLabel(booking.arrivalDate.toString()));
        detailsPanel.add(new JLabel("Arrival Time:"));
        detailsPanel.add(new JLabel(booking.arrivalTime));
        detailsPanel.add(new JLabel("Journey Time:"));
        detailsPanel.add(new JLabel(booking.journeyTimeTxt + "(hrs)"));
        detailsPanel.add(new JLabel("Number of Stops:"));
        detailsPanel.add(new JLabel(String.valueOf(booking.noOfStops)));
        detailsPanel.add(new JLabel("Transitional Points:"));
        detailsPanel.add(new JLabel(booking.transitionalPoint == null || booking.transitionalPoint.isEmpty() ? "-" : booking.transitionalPoint));
        detailsPanel.add(new JLabel("Baggage Policy:"));
        detailsPanel.add(new JLabel(booking.baggagePolicy));
        detailsPanel.add(new JLabel("Refundability:"));
        detailsPanel.add(new JLabel(booking.refundability));
        detailsPanel.add(new JLabel("Price per Ticket:"));
        detailsPanel.add(new JLabel("$" + booking.pricePerTicket));
        detailsPanel.add(new JLabel("Booking Purchased On:"));
        detailsPanel.add(new JLabel(booking.dateTime.toString()));

        detailsPanel.setVisible(false);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottomPanel.setOpaque(false);
        bottomPanel.add(cancelButton);

        JPanel detailsContainer = new JPanel(new BorderLayout());
        detailsContainer.setOpaque(false);
        detailsContainer.add(detailsPanel, BorderLayout.CENTER);
        detailsContainer.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(detailsContainer, BorderLayout.CENTER);

        toggleButton.addActionListener(e -> {
            boolean visible = detailsPanel.isVisible();
            detailsPanel.setVisible(!visible);
            toggleButton.setText(visible ? "Details ▼" : "Details ▲");
            SwingUtilities.getWindowAncestor(mainPanel).revalidate();
        });

        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancel", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cancelBooking(booking.flightID);
                loadBookings();
            }
        });

        return mainPanel;
    }

    private void cancelBooking(int flightID) {
        String sql = "DELETE FROM bookings WHERE Username = ? AND Flight_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setInt(2, flightID);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to cancel booking.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static class Booking {
        int flightID;
        int noOfTickets;
        double totalPrice;
        Timestamp dateTime;

        String flightName;
        String flightModel;
        String source;
        String departAirportName;
        String sourceCountry;
        String destination;
        String arrivalAirportName;
        String destinationCountry;
        String flightClass;
        Date flightDate;
        String departureTime;
        Date arrivalDate;
        String arrivalTime;
        String journeyTimeTxt;
        int noOfStops;
        String transitionalPoint;
        String baggagePolicy;
        String refundability;
        double pricePerTicket;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyBookings());
    }
}
