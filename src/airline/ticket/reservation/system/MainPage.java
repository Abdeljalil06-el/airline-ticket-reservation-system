package airline.ticket.reservation.system;

import airline.ticket.reservation.system.LoginPage;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MainPage extends JFrame {

    private JComboBox<String> fromCB, toCB, classCB;
    private JButton searchBtn, myDetailsBtn, bookingsBtn, logoutBtn;
    private JDateChooser calendar;
    private JLabel welcomeLabel;
    private JToggleButton themeToggle;
    private boolean isDarkMode = false;
    private JPanel topPanel, formPanel;
    private String username;

    public MainPage() {
        String username = SessionManager.getUsername();
        initLookAndFeel(false);
        initComponents();
        setTitle("Airline Ticket Reservation");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Calendar cal = Calendar.getInstance();
        calendar.setMinSelectableDate(cal.getTime());
        cal.add(Calendar.MONTH, 2);
        calendar.setMaxSelectableDate(cal.getTime());

        welcomeLabel.setText("Welcome, " + username);
        updateColors();
    }

    private void initLookAndFeel(boolean dark) {
        try {
            if (dark) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        initLookAndFeel(isDarkMode);
        SwingUtilities.updateComponentTreeUI(this);
        updateColors();
        this.revalidate();
        this.repaint();
    }

    private void updateColors() {
        Color bgColor = isDarkMode ? new Color(30, 30, 40) : new Color(235, 240, 255);
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;

        getContentPane().setBackground(bgColor);
        topPanel.setBackground(bgColor);
        formPanel.setBackground(bgColor);

        welcomeLabel.setForeground(textColor);
        themeToggle.setForeground(textColor);

        Component[] components = formPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel label) {
                label.setForeground(textColor);
            }
        }
    }

    private void initComponents() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        navPanel.setOpaque(false);

        myDetailsBtn = createRoundedButton("My Details", "resources/icons/user.png");
        bookingsBtn = createRoundedButton("My Bookings", "resources/icons/bookings.png");
        logoutBtn = createRoundedButton("Logout", "resources/icons/logout.png");

        JButton[] navButtons = {myDetailsBtn, bookingsBtn,  logoutBtn};
        for (JButton b : navButtons) navPanel.add(b);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        rightPanel.setOpaque(false);
        welcomeLabel = new JLabel();

        themeToggle = new JToggleButton("Dark Mode");
        themeToggle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        themeToggle.addActionListener(e -> {
            toggleTheme();
            themeToggle.setText(isDarkMode ? "Light Mode" : "Dark Mode");
        });

        rightPanel.add(welcomeLabel);
        rightPanel.add(themeToggle);

        topPanel.add(navPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 30, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
        Dimension fieldSize = new Dimension(180, 30);

        fromCB = new JComboBox<>(new String[]{"Dhaka", "Chattogram", "Cox's Bazar", "Sylhet", "Comilla", "Barishal"});
        toCB = new JComboBox<>(new String[]{"Cox's Bazar", "Chattogram", "Sylhet", "Comilla", "Barishal", "Dhaka"});
        classCB = new JComboBox<>(new String[]{"Economy Class", "Premium Class", "Business Class", "First Class"});
        calendar = new JDateChooser();

        fromCB.setFont(fieldFont);
        fromCB.setPreferredSize(fieldSize);
        toCB.setFont(fieldFont);
        toCB.setPreferredSize(fieldSize);
        classCB.setFont(fieldFont);
        classCB.setPreferredSize(fieldSize);
        calendar.setFont(fieldFont);
        calendar.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(labelFont);
        formPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(fromCB, gbc);

        gbc.gridx = 2;
        JButton swapBtn = new RoundedButton("⇆");
        swapBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        swapBtn.setPreferredSize(new Dimension(45, 30));
        swapBtn.addActionListener(e -> {
            String from = (String) fromCB.getSelectedItem();
            String to = (String) toCB.getSelectedItem();
            fromCB.setSelectedItem(to);
            toCB.setSelectedItem(from);
        });
        formPanel.add(swapBtn, gbc);

        gbc.gridx = 3;
        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(labelFont);
        formPanel.add(toLabel, gbc);

        gbc.gridx = 4;
        formPanel.add(toCB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);
        formPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(classCB, gbc);

        gbc.gridx = 3;
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(labelFont);
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 4;
        formPanel.add(calendar, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchBtn = new RoundedButton("Search");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchBtn.setPreferredSize(new Dimension(120, 32));
        searchBtn.setIcon(new ImageIcon("resources/icons/search.png"));
        searchBtn.addActionListener(e -> searchFlights());
        formPanel.add(searchBtn, gbc);

        bookingsBtn.addActionListener(e -> {
            this.setVisible(false);
            MyBookings mb = new MyBookings();
            mb.setVisible(true);
        });
        
        myDetailsBtn.addActionListener(e -> {
            this.setVisible(false);
            MyDetails md = new MyDetails();
            md.setVisible(true);
        });
        
        logoutBtn.addActionListener(e -> logout());


        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private JButton createRoundedButton(String text, String iconPath) {
        RoundedButton button = new RoundedButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setIcon(new ImageIcon(iconPath));
        button.setPreferredSize(new Dimension(120, 32));
        return button;
    }

    private void searchFlights() {
        String from = (String) fromCB.getSelectedItem();
        String to = (String) toCB.getSelectedItem();
        String travelClass = (String) classCB.getSelectedItem();
        Date date = calendar.getDate();

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(this, "Source and destination cannot be the same.");
            return;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(this, "Please select a valid date.");
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "")) {
            String query = "SELECT * FROM flight_details WHERE Source = ? AND Destination = ? AND Class = ? AND Date = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, from);
            ps.setString(2, to);
            ps.setString(3, travelClass);
            ps.setDate(4, new java.sql.Date(date.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FlightBooking fb = new FlightBooking();
                fb.labelSource.setText(from);
                fb.labelDestination.setText(to);
                fb.labelClass.setText(travelClass);
                fb.labelDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
                fb.source = from;
                fb.destination = to;
                fb.fClass = travelClass;
                fb.fDate = new java.sql.Date(date.getTime());
                fb.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No flights found for the selected options.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error searching flights: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void logout() {
    try {
        Properties props = new Properties();
        props.setProperty("username", "");
        props.setProperty("password", "");
        props.setProperty("remember", "false");

        try (FileOutputStream out = new FileOutputStream("login.properties")) {
            props.store(out, "Cleared on logout");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Failed to clear login data.", "Logout Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return;
    }

    this.dispose();  // Close current window

    LoginPage sp = new LoginPage(); // Create login page
    sp.clearLoginFields();            // Clear fields
    sp.setVisible(true);              // Show login page
}

    static class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(new RoundedBorder(15));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius + 1;
            return insets;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        });
    }
}
