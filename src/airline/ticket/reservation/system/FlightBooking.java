package airline.ticket.reservation.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.awt.HeadlessException;
import java.awt.Insets;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import org.json.JSONObject;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class FlightBooking extends javax.swing.JFrame {

    private static final int MAX_TICKETS = 10;
    String source;
    String source0;
    String destination;
    String destination0;

    String fClass;
    private String class0;
    Date fDate;
    private Date date0;
    
    private final String username;

    MainPage mp = new MainPage();
    
    int[] flightID = new int[10]; // OK inside a method

    JPanel[] tp = new JPanel[10];
    JLabel[] id = new JLabel[10];    
    JLabel[] nam = new JLabel[10];
    JLabel[] frm = new JLabel[10];
    JLabel[] to = new JLabel[10];
    JLabel[] cl = new JLabel[10];
    JLabel[] dl = new JLabel[10];
    JLabel[] dt = new JLabel[10];
    JLabel[] at = new JLabel[10];
    JLabel[] ad = new JLabel[10];
    JLabel[] st = new JLabel[10];
    JLabel[] sNam = new JLabel[10];
    JLabel[] bag = new JLabel[10];
    JLabel[] ref = new JLabel[10];
    JLabel[] tk = new JLabel[10];
    JLabel[] advice = new JLabel[10];
    javax.swing.JButton[] select = new javax.swing.JButton[10];
    
    private HolidaySeasonManager holidaySeasonManager = new HolidaySeasonManager();

    public FlightBooking() {
        username = SessionManager.getUsername();
        initComponents();
        initializeTicketPanels();
        noFlight.setVisible(false);
        setLocationRelativeTo(null);
        
        try {
            holidaySeasonManager.loadFromCSV("E:\\\\Java\\\\Holidays\\\\Holiday_Calendar.csv");
        } catch (Exception e) {
            e.printStackTrace();
            // handle loading error gracefully
        }

    }

    private void initializeTicketPanels() {
        tp[0] = ticketPanel;   tp[1] = ticketPanel1; tp[2] = ticketPanel2; tp[3] = ticketPanel3; tp[4] = ticketPanel4;
        tp[5] = ticketPanel5;  tp[6] = ticketPanel6; tp[7] = ticketPanel7; tp[8] = ticketPanel8; tp[9] = ticketPanel9;

        id[0] = fID; id[1] = fID1; id[2] = fID2; id[3] = fID3; id[4] = fID4;
        id[5] = fID5; id[6] = fID6; id[7] = fID7; id[8] = fID8; id[9] = fID9;

        nam[0] = fName; nam[1] = fName1; nam[2] = fName2; nam[3] = fName3; nam[4] = fName4;
        nam[5] = fName5; nam[6] = fName6; nam[7] = fName7; nam[8] = fName8; nam[9] = fName9;

        frm[0] = fromLabel; frm[1] = fromLabel1; frm[2] = fromLabel2; frm[3] = fromLabel3; frm[4] = fromLabel4;
        frm[5] = fromLabel5; frm[6] = fromLabel6; frm[7] = fromLabel7; frm[8] = fromLabel8; frm[9] = fromLabel9;

        to[0] = toLabel; to[1] = toLabel1; to[2] = toLabel2; to[3] = toLabel3; to[4] = toLabel4;
        to[5] = toLabel5; to[6] = toLabel6; to[7] = toLabel7; to[8] = toLabel8; to[9] = toLabel9;

        cl[0] = classLabel; cl[1] = classLabel1; cl[2] = classLabel2; cl[3] = classLabel3; cl[4] = classLabel4;
        cl[5] = classLabel5; cl[6] = classLabel6; cl[7] = classLabel7; cl[8] = classLabel8; cl[9] = classLabel9;

        dl[0] = dateLavel; dl[1] = dateLavel1; dl[2] = dateLavel2; dl[3] = dateLavel3; dl[4] = dateLavel4;
        dl[5] = dateLavel5; dl[6] = dateLavel6; dl[7] = dateLavel7; dl[8] = dateLavel8; dl[9] = dateLavel9;

        dt[0] = dTime; dt[1] = dTime1; dt[2] = dTime2; dt[3] = dTime3; dt[4] = dTime4;
        dt[5] = dTime5; dt[6] = dTime6; dt[7] = dTime7; dt[8] = dTime8; dt[9] = dTime9;

        at[0] = aTime; at[1] = aTime1; at[2] = aTime2; at[3] = aTime3; at[4] = aTime4;
        at[5] = aTime5; at[6] = aTime6; at[7] = aTime7; at[8] = aTime8; at[9] = aTime9;

        ad[0] = aDate; ad[1] = aDate1; ad[2] = aDate2; ad[3] = aDate3; ad[4] = aDate4;
        ad[5] = aDate5; ad[6] = aDate6; ad[7] = aDate7; ad[8] = aDate8; ad[9] = aDate9;

        st[0] = stopNo; st[1] = stopNo1; st[2] = stopNo2; st[3] = stopNo3; st[4] = stopNo4;
        st[5] = stopNo5; st[6] = stopNo6; st[7] = stopNo7; st[8] = stopNo8; st[9] = stopNo9;

        sNam[0] = stopName; sNam[1] = stopName1; sNam[2] = stopName2; sNam[3] = stopName3; sNam[4] = stopName4;
        sNam[5] = stopName5; sNam[6] = stopName6; sNam[7] = stopName7; sNam[8] = stopName8; sNam[9] = stopName9;

        bag[0] = baggage; bag[1] = baggage1; bag[2] = baggage2; bag[3] = baggage3; bag[4] = baggage4;
        bag[5] = baggage5; bag[6] = baggage6; bag[7] = baggage7; bag[8] = baggage8; bag[9] = baggage9;

        ref[0] = refund; ref[1] = refund1; ref[2] = refund2; ref[3] = refund3; ref[4] = refund4;
        ref[5] = refund5; ref[6] = refund6; ref[7] = refund7; ref[8] = refund8; ref[9] = refund9;

        tk[0] = price; tk[1] = price1; tk[2] = price2; tk[3] = price3; tk[4] = price4;
        tk[5] = price5; tk[6] = price6; tk[7] = price7; tk[8] = price8; tk[9] = price9;
        select[0] = selectBtn;
        select[1] = selectBtn1;
        select[2] = selectBtn2;
        select[3] = selectBtn3;
        select[4] = selectBtn4;
        select[5] = selectBtn5;
        select[6] = selectBtn6;
        select[7] = selectBtn7;
        select[8] = selectBtn8;
        select[9] = selectBtn9;

        advice[0] = adviceLabel0;
        advice[1] = adviceLabel1;
        advice[2] = adviceLabel2;
        advice[3] = adviceLabel3;
        advice[4] = adviceLabel4;
        advice[5] = adviceLabel5;
        advice[6] = adviceLabel6;
        advice[7] = adviceLabel7;
        advice[8] = adviceLabel8;
        advice[9] = adviceLabel9;

        for (int i = 0; i < MAX_TICKETS; i++) {
            tp[i].setVisible(false);
        }
    }

    public void populateTicketPanel(int j, java.sql.ResultSet rs) throws java.sql.SQLException {
        tp[j].setVisible(true);
        id[j].setText(rs.getString(1));
        nam[j].setText(rs.getString(2));
        frm[j].setText(rs.getString(3));
        to[j].setText(rs.getString(4));
        cl[j].setText(rs.getString(5));
        dl[j].setText(rs.getString(6));
        dt[j].setText(rs.getString(7));
        at[j].setText(rs.getString(8));
        ad[j].setText(rs.getString(9));
        st[j].setText(rs.getString(10));
        sNam[j].setText(rs.getString(11));
        bag[j].setText(rs.getString(12));
        ref[j].setText(rs.getString(13));
        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        adviceLabel0 = new javax.swing.JLabel();
        adviceLabel0.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel0.setText("");
        adviceLabel1 = new javax.swing.JLabel();
        adviceLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel1.setText("");
        adviceLabel2 = new javax.swing.JLabel();
        adviceLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel2.setText("");
        adviceLabel3 = new javax.swing.JLabel();
        adviceLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel3.setText("");
        adviceLabel4 = new javax.swing.JLabel();
        adviceLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel4.setText("");
        adviceLabel5 = new javax.swing.JLabel();
        adviceLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel5.setText("");
        adviceLabel6 = new javax.swing.JLabel();
        adviceLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel6.setText("");
        adviceLabel7 = new javax.swing.JLabel();
        adviceLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel7.setText("");
        adviceLabel8 = new javax.swing.JLabel();
        adviceLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel8.setText("");
        adviceLabel9 = new javax.swing.JLabel();
        adviceLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        adviceLabel9.setText("");

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        no = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        showBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelSource = new javax.swing.JLabel();
        labelDestination = new javax.swing.JLabel();
        labelClass = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nonRefundCB = new javax.swing.JCheckBox();
        refundCB = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        nonStopCB = new javax.swing.JCheckBox();
        stopCB = new javax.swing.JCheckBox();
        clearBtn = new javax.swing.JButton();
        backBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        ticketPanel = new javax.swing.JPanel();
        fromLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dTime = new javax.swing.JLabel();
        aTime = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        stopNo = new javax.swing.JLabel();
        stopName = new javax.swing.JLabel();
        dateLavel = new javax.swing.JLabel();
        aDate = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        fID = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        baggage = new javax.swing.JLabel();
        refund = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        selectBtn = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        classLabel = new javax.swing.JLabel();
        fName = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        ticketPanel1 = new javax.swing.JPanel();
        fromLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dTime1 = new javax.swing.JLabel();
        aTime1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        stopNo1 = new javax.swing.JLabel();
        stopName1 = new javax.swing.JLabel();
        dateLavel1 = new javax.swing.JLabel();
        aDate1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fID1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        baggage1 = new javax.swing.JLabel();
        refund1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        price1 = new javax.swing.JLabel();
        selectBtn1 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        classLabel1 = new javax.swing.JLabel();
        fName1 = new javax.swing.JLabel();
        toLabel1 = new javax.swing.JLabel();
        ticketPanel2 = new javax.swing.JPanel();
        fromLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dTime2 = new javax.swing.JLabel();
        aTime2 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        stopNo2 = new javax.swing.JLabel();
        stopName2 = new javax.swing.JLabel();
        dateLavel2 = new javax.swing.JLabel();
        aDate2 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        fID2 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        baggage2 = new javax.swing.JLabel();
        refund2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        price2 = new javax.swing.JLabel();
        selectBtn2 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        classLabel2 = new javax.swing.JLabel();
        fName2 = new javax.swing.JLabel();
        toLabel2 = new javax.swing.JLabel();
        ticketPanel3 = new javax.swing.JPanel();
        fromLabel3 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        dTime3 = new javax.swing.JLabel();
        aTime3 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        stopNo3 = new javax.swing.JLabel();
        stopName3 = new javax.swing.JLabel();
        dateLavel3 = new javax.swing.JLabel();
        aDate3 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        fID3 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        baggage3 = new javax.swing.JLabel();
        refund3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        price3 = new javax.swing.JLabel();
        selectBtn3 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        classLabel3 = new javax.swing.JLabel();
        fName3 = new javax.swing.JLabel();
        toLabel3 = new javax.swing.JLabel();
        noFlight = new javax.swing.JLabel();
        ticketPanel4 = new javax.swing.JPanel();
        fromLabel4 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        dTime4 = new javax.swing.JLabel();
        aTime4 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        stopNo4 = new javax.swing.JLabel();
        stopName4 = new javax.swing.JLabel();
        dateLavel4 = new javax.swing.JLabel();
        aDate4 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        fID4 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        baggage4 = new javax.swing.JLabel();
        refund4 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        price4 = new javax.swing.JLabel();
        selectBtn4 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        classLabel4 = new javax.swing.JLabel();
        fName4 = new javax.swing.JLabel();
        toLabel4 = new javax.swing.JLabel();
        ticketPanel5 = new javax.swing.JPanel();
        fromLabel5 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        dTime5 = new javax.swing.JLabel();
        aTime5 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        stopNo5 = new javax.swing.JLabel();
        stopName5 = new javax.swing.JLabel();
        dateLavel5 = new javax.swing.JLabel();
        aDate5 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        fID5 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        baggage5 = new javax.swing.JLabel();
        refund5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        price5 = new javax.swing.JLabel();
        selectBtn5 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        classLabel5 = new javax.swing.JLabel();
        fName5 = new javax.swing.JLabel();
        toLabel5 = new javax.swing.JLabel();
        ticketPanel6 = new javax.swing.JPanel();
        fromLabel6 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        dTime6 = new javax.swing.JLabel();
        aTime6 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        stopNo6 = new javax.swing.JLabel();
        stopName6 = new javax.swing.JLabel();
        dateLavel6 = new javax.swing.JLabel();
        aDate6 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        fID6 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        baggage6 = new javax.swing.JLabel();
        refund6 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        price6 = new javax.swing.JLabel();
        selectBtn6 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        classLabel6 = new javax.swing.JLabel();
        fName6 = new javax.swing.JLabel();
        toLabel6 = new javax.swing.JLabel();
        ticketPanel7 = new javax.swing.JPanel();
        fromLabel7 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        dTime7 = new javax.swing.JLabel();
        aTime7 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        stopNo7 = new javax.swing.JLabel();
        stopName7 = new javax.swing.JLabel();
        dateLavel7 = new javax.swing.JLabel();
        aDate7 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        fID7 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        baggage7 = new javax.swing.JLabel();
        refund7 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        price7 = new javax.swing.JLabel();
        selectBtn7 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        classLabel7 = new javax.swing.JLabel();
        fName7 = new javax.swing.JLabel();
        toLabel7 = new javax.swing.JLabel();
        ticketPanel8 = new javax.swing.JPanel();
        fromLabel8 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        dTime8 = new javax.swing.JLabel();
        aTime8 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        stopNo8 = new javax.swing.JLabel();
        stopName8 = new javax.swing.JLabel();
        dateLavel8 = new javax.swing.JLabel();
        aDate8 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        fID8 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        baggage8 = new javax.swing.JLabel();
        refund8 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        price8 = new javax.swing.JLabel();
        selectBtn8 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        classLabel8 = new javax.swing.JLabel();
        fName8 = new javax.swing.JLabel();
        toLabel8 = new javax.swing.JLabel();
        ticketPanel9 = new javax.swing.JPanel();
        fromLabel9 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        dTime9 = new javax.swing.JLabel();
        aTime9 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        stopNo9 = new javax.swing.JLabel();
        stopName9 = new javax.swing.JLabel();
        dateLavel9 = new javax.swing.JLabel();
        aDate9 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        fID9 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        baggage9 = new javax.swing.JLabel();
        refund9 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        price9 = new javax.swing.JLabel();
        selectBtn9 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        classLabel9 = new javax.swing.JLabel();
        fName9 = new javax.swing.JLabel();
        toLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        time00 = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        time06 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        time12 = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        time18 = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        earliest = new javax.swing.JButton();
        fastest = new javax.swing.JButton();
        cheapest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1120, 530));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ICON");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        no.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        no.setText("*");

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel3.setText("Available Flights ");

        showBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        showBtn.setText("Show");
        showBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel2.setText("Source       :");

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel4.setText("Destination :");

        jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel5.setText("Class          :");

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel6.setText("Date           :");

        labelSource.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labelSource.setMaximumSize(new java.awt.Dimension(75, 16));
        labelSource.setMinimumSize(new java.awt.Dimension(75, 16));

        labelDestination.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labelDestination.setMaximumSize(new java.awt.Dimension(75, 16));
        labelDestination.setMinimumSize(new java.awt.Dimension(75, 16));

        labelClass.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labelClass.setMaximumSize(new java.awt.Dimension(75, 16));
        labelClass.setMinimumSize(new java.awt.Dimension(75, 16));

        labelDate.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        labelDate.setMaximumSize(new java.awt.Dimension(75, 16));
        labelDate.setMinimumSize(new java.awt.Dimension(75, 16));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(showBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelSource, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(no))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSource, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelClass, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(showBtn)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel8.setText("Refundability");

        buttonGroup2.add(nonRefundCB);
        nonRefundCB.setText("Non Refundable");
        nonRefundCB.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        nonRefundCB.setMultiClickThreshhold(2L);
        nonRefundCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonRefundCBActionPerformed(evt);
            }
        });

        buttonGroup2.add(refundCB);
        refundCB.setText("Refundable");
        refundCB.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        refundCB.setMultiClickThreshhold(2L);
        refundCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refundCB)
                            .addComponent(nonRefundCB))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nonRefundCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refundCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel9.setText("No of Stops");

        buttonGroup3.add(nonStopCB);
        nonStopCB.setText("Non-Stop");
        nonStopCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonStopCBActionPerformed(evt);
            }
        });

        buttonGroup3.add(stopCB);
        stopCB.setText("One Transitional Point");
        stopCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stopCB)
                            .addComponent(nonStopCB))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nonStopCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clearBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        clearBtn.setText("Clear Filters");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        backBTN.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        backBTN.setText("Back");
        backBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(backBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(backBTN))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ticketPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel11.setText("-");

        dTime.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime.setText("Departure Time");

        aTime.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime.setText("Arrival Time");

        jLabel16.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel16.setText("No of Stops :");

        stopNo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo.setText("No");

        stopName.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName.setText("Name");

        dateLavel.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel.setText("Date");

        aDate.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate.setText("Date");

        jLabel22.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel22.setText("Flight No -");

        fID.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID.setText("ID");

        jLabel24.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel24.setText("Refundability - ");

        jLabel25.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel25.setText("Class -");

        jLabel26.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel26.setText("Baggages -");

        baggage.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage.setText("Kg");

        refund.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund.setText("Y/N");

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        selectBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn.setText("Select");
        selectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtnMouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel30.setText("BDT");

        price.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price.setText("Tk");
        adviceLabel0 = new javax.swing.JLabel();
        adviceLabel0.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(price))
                .addGap(18, 18, 18)
                .addComponent(selectBtn)
                .addContainerGap())
        );

        classLabel.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName.setText("Flight Name");
        fName.setDoubleBuffered(true);

        toLabel.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanelLayout = new javax.swing.GroupLayout(ticketPanel);
        ticketPanel.setLayout(ticketPanelLayout);
        ticketPanelLayout.setHorizontalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addComponent(fromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addComponent(dateLavel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(aDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(stopName, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addComponent(dTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(aTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel16)
                                .addGap(6, 6, 6)
                                .addComponent(stopNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(6, 6, 6)
                                .addComponent(fID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(6, 6, 6)
                                .addComponent(refund, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(baggage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        ticketPanelLayout.setVerticalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel16))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(stopNo)))
                .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(aDate))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(stopName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(ticketPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(refund))))
                    .addGroup(ticketPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(baggage)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26))
                            .addComponent(classLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        ticketPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel1.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel12.setText("-");

        dTime1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime1.setText("Departure Time");

        aTime1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime1.setText("Arrival Time");

        jLabel17.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel17.setText("No of Stops :");

        stopNo1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo1.setText("No");

        stopName1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName1.setText("Name");

        dateLavel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel1.setText("Date");

        aDate1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate1.setText("Date");

        jLabel23.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel23.setText("Flight No -");

        fID1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID1.setText("ID");

        jLabel27.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel27.setText("Refundability - ");

        jLabel28.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel28.setText("Class -");

        jLabel29.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel29.setText("Baggages -");

        baggage1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage1.setText("Kg");

        refund1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund1.setText("Y/N");

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setPreferredSize(new java.awt.Dimension(144, 87));

        price1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price1.setText("Tk");
        adviceLabel1 = new javax.swing.JLabel();
        adviceLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
        price1.setMaximumSize(new java.awt.Dimension(16, 16));
        price1.setMinimumSize(new java.awt.Dimension(16, 16));
        price1.setPreferredSize(new java.awt.Dimension(16, 16));

        selectBtn1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn1.setText("Select");
        selectBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn1MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel31.setText("BDT");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addComponent(selectBtn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        classLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName1.setText("Flight Name");

        toLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel1Layout = new javax.swing.GroupLayout(ticketPanel1);
        ticketPanel1.setLayout(ticketPanel1Layout);
        ticketPanel1Layout.setHorizontalGroup(
            ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(6, 6, 6)
                                .addComponent(fID1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(6, 6, 6)
                                .addComponent(refund1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(baggage1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addComponent(fromLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(toLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fName1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLavel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addComponent(aTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stopNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addComponent(aDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(stopName1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        ticketPanel1Layout.setVerticalGroup(
            ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel1Layout.createSequentialGroup()
                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fromLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(aTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stopNo1)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLavel1)
                    .addComponent(aDate1)
                    .addComponent(stopName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fID1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(refund1))))
                            .addGroup(ticketPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(ticketPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(baggage1)
                                        .addComponent(jLabel28))))))
                    .addGroup(ticketPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        ticketPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel2.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel21.setText("-");

        dTime2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime2.setText("Departure Time");

        aTime2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime2.setText("Arrival Time");

        jLabel47.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel47.setText("No of Stops :");

        stopNo2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo2.setText("No");

        stopName2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName2.setText("Name");

        dateLavel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel2.setText("Date");

        aDate2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate2.setText("Date");

        jLabel48.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel48.setText("Flight No -");

        fID2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID2.setText("ID");

        jLabel49.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel49.setText("Refundability - ");

        jLabel50.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel50.setText("Class -");

        jLabel51.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel51.setText("Baggages -");

        baggage2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage2.setText("Kg");

        refund2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund2.setText("Y/N");

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setPreferredSize(new java.awt.Dimension(142, 87));

        price2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price2.setText("Tk");
        adviceLabel2 = new javax.swing.JLabel();
        adviceLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn2.setText("Select");
        selectBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn2MouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel52.setText("BDT");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(price2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addComponent(selectBtn2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        classLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName2.setText("Flight Name");

        toLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel2Layout = new javax.swing.GroupLayout(ticketPanel2);
        ticketPanel2.setLayout(ticketPanel2Layout);
        ticketPanel2Layout.setHorizontalGroup(
            ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addGap(6, 6, 6)
                                .addComponent(fID2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(6, 6, 6)
                                .addComponent(refund2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addComponent(jLabel51)
                        .addGap(12, 12, 12)
                        .addComponent(baggage2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(fromLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel21)
                                .addGap(30, 30, 30)
                                .addComponent(toLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(dTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(fName2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(dateLavel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(72, 72, 72)
                        .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(aDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(stopName2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addComponent(aTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(3, 3, 3))
        );
        ticketPanel2Layout.setVerticalGroup(
            ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47)
                        .addComponent(stopNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fName2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLavel2)
                            .addComponent(stopName2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aDate2)))
                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fID2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(refund2))))
                            .addGroup(ticketPanel2Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addGroup(ticketPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(baggage2)
                                        .addComponent(jLabel50))
                                    .addComponent(classLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(ticketPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        ticketPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel3.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel53.setText("-");

        dTime3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime3.setText("Departure Time");

        aTime3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime3.setText("Arrival Time");

        jLabel54.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel54.setText("No of Stops :");

        stopNo3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo3.setText("No");

        stopName3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName3.setText("Name");

        dateLavel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel3.setText("Date");

        aDate3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate3.setText("Date");

        jLabel55.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel55.setText("Flight No -");

        fID3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID3.setText("ID");

        jLabel56.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel56.setText("Refundability - ");

        jLabel57.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel57.setText("Class -");

        jLabel58.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel58.setText("Baggages -");

        baggage3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage3.setText("Kg");

        refund3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund3.setText("Y/N");

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setPreferredSize(new java.awt.Dimension(142, 87));

        price3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price3.setText("Tk");
        adviceLabel3 = new javax.swing.JLabel();
        adviceLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn3.setText("Select");
        selectBtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn3MouseClicked(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel59.setText("BDT");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn3, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn3)
                .addContainerGap())
        );

        classLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName3.setText("Flight Name");

        toLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel3Layout = new javax.swing.GroupLayout(ticketPanel3);
        ticketPanel3.setLayout(ticketPanel3Layout);
        ticketPanel3Layout.setHorizontalGroup(
            ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel3Layout.createSequentialGroup()
                                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                                        .addComponent(fromLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel53)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel3Layout.setVerticalGroup(
            ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel3)
                            .addComponent(aDate3)
                            .addComponent(fName3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel3Layout.createSequentialGroup()
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(stopNo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel56)
                                .addComponent(jLabel57)
                                .addComponent(jLabel58)
                                .addComponent(baggage3)
                                .addComponent(refund3))
                            .addComponent(classLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        noFlight.setFont(new java.awt.Font("Lucida Console", 1, 24)); // NOI18N
        noFlight.setText("No Flight Found");

        ticketPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel4.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel60.setText("-");

        dTime4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime4.setText("Departure Time");

        aTime4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime4.setText("Arrival Time");

        jLabel61.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel61.setText("No of Stops :");

        stopNo4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo4.setText("No");

        stopName4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName4.setText("Name");

        dateLavel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel4.setText("Date");

        aDate4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate4.setText("Date");

        jLabel62.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel62.setText("Flight No -");

        fID4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID4.setText("ID");

        jLabel63.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel63.setText("Refundability - ");

        jLabel64.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel64.setText("Class -");

        jLabel65.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel65.setText("Baggages -");

        baggage4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage4.setText("Kg");

        refund4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund4.setText("Y/N");

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setPreferredSize(new java.awt.Dimension(142, 87));

        price4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price4.setText("Tk");
        adviceLabel4 = new javax.swing.JLabel();
        adviceLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn4.setText("Select");
        selectBtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn4MouseClicked(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel66.setText("BDT");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn4)
                .addContainerGap())
        );

        classLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName4.setText("Flight Name");

        toLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel4Layout = new javax.swing.GroupLayout(ticketPanel4);
        ticketPanel4.setLayout(ticketPanel4Layout);
        ticketPanel4Layout.setHorizontalGroup(
            ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel4Layout.createSequentialGroup()
                                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel63)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                                        .addComponent(fromLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel60)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel65)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel4Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel4Layout.setVerticalGroup(
            ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel4)
                            .addComponent(aDate4)
                            .addComponent(fName4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel4Layout.createSequentialGroup()
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(stopNo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel63)
                                .addComponent(jLabel64)
                                .addComponent(jLabel65)
                                .addComponent(baggage4)
                                .addComponent(refund4))
                            .addComponent(classLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        ticketPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel5.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel67.setText("-");

        dTime5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime5.setText("Departure Time");

        aTime5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime5.setText("Arrival Time");

        jLabel68.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel68.setText("No of Stops :");

        stopNo5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo5.setText("No");

        stopName5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName5.setText("Name");

        dateLavel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel5.setText("Date");

        aDate5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate5.setText("Date");

        jLabel69.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel69.setText("Flight No -");

        fID5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID5.setText("ID");

        jLabel70.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel70.setText("Refundability - ");

        jLabel71.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel71.setText("Class -");

        jLabel72.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel72.setText("Baggages -");

        baggage5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage5.setText("Kg");

        refund5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund5.setText("Y/N");

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setPreferredSize(new java.awt.Dimension(142, 87));

        price5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price5.setText("Tk");
        adviceLabel5 = new javax.swing.JLabel();
        adviceLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn5.setText("Select");
        selectBtn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn5MouseClicked(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel73.setText("BDT");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn5, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn5)
                .addContainerGap())
        );

        classLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName5.setText("Flight Name");

        toLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel5Layout = new javax.swing.GroupLayout(ticketPanel5);
        ticketPanel5.setLayout(ticketPanel5Layout);
        ticketPanel5Layout.setHorizontalGroup(
            ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel5Layout.createSequentialGroup()
                                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel70)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                                        .addComponent(fromLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel67)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel72)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel5Layout.setVerticalGroup(
            ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel5)
                            .addComponent(aDate5)
                            .addComponent(fName5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel5Layout.createSequentialGroup()
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(stopNo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel5Layout.createSequentialGroup()
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel70)
                                .addComponent(jLabel71)
                                .addComponent(jLabel72)
                                .addComponent(baggage5)
                                .addComponent(refund5))
                            .addComponent(classLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        ticketPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel6.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel74.setText("-");

        dTime6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime6.setText("Departure Time");

        aTime6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime6.setText("Arrival Time");

        jLabel75.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel75.setText("No of Stops :");

        stopNo6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo6.setText("No");

        stopName6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName6.setText("Name");

        dateLavel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel6.setText("Date");

        aDate6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate6.setText("Date");

        jLabel76.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel76.setText("Flight No -");

        fID6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID6.setText("ID");

        jLabel77.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel77.setText("Refundability - ");

        jLabel78.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel78.setText("Class -");

        jLabel79.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel79.setText("Baggages -");

        baggage6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage6.setText("Kg");

        refund6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund6.setText("Y/N");

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setPreferredSize(new java.awt.Dimension(142, 87));

        price6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price6.setText("Tk");
        adviceLabel6 = new javax.swing.JLabel();
        adviceLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn6.setText("Select");
        selectBtn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn6MouseClicked(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel80.setText("BDT");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn6, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn6)
                .addContainerGap())
        );

        classLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName6.setText("Flight Name");

        toLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel6Layout = new javax.swing.GroupLayout(ticketPanel6);
        ticketPanel6.setLayout(ticketPanel6Layout);
        ticketPanel6Layout.setHorizontalGroup(
            ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel6Layout.createSequentialGroup()
                                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel77)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                                        .addComponent(fromLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel74)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel79)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel6Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel6Layout.setVerticalGroup(
            ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel6)
                            .addComponent(aDate6)
                            .addComponent(fName6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel6Layout.createSequentialGroup()
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(stopNo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel77)
                                .addComponent(jLabel78)
                                .addComponent(jLabel79)
                                .addComponent(baggage6)
                                .addComponent(refund6))
                            .addComponent(classLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        ticketPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel7.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel81.setText("-");

        dTime7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime7.setText("Departure Time");

        aTime7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime7.setText("Arrival Time");

        jLabel82.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel82.setText("No of Stops :");

        stopNo7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo7.setText("No");

        stopName7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName7.setText("Name");

        dateLavel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel7.setText("Date");

        aDate7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate7.setText("Date");

        jLabel83.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel83.setText("Flight No -");

        fID7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID7.setText("ID");

        jLabel84.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel84.setText("Refundability - ");

        jLabel85.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel85.setText("Class -");

        jLabel86.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel86.setText("Baggages -");

        baggage7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage7.setText("Kg");

        refund7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund7.setText("Y/N");

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setPreferredSize(new java.awt.Dimension(142, 87));

        price7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price7.setText("Tk");
        adviceLabel7 = new javax.swing.JLabel();
        adviceLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn7.setText("Select");
        selectBtn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn7MouseClicked(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel87.setText("BDT");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn7, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn7)
                .addContainerGap())
        );

        classLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName7.setText("Flight Name");

        toLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel7Layout = new javax.swing.GroupLayout(ticketPanel7);
        ticketPanel7.setLayout(ticketPanel7Layout);
        ticketPanel7Layout.setHorizontalGroup(
            ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel7Layout.createSequentialGroup()
                                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel84)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                                        .addComponent(fromLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel81)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel86)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel7Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel85)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel7Layout.setVerticalGroup(
            ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel7)
                            .addComponent(aDate7)
                            .addComponent(fName7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel7Layout.createSequentialGroup()
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(stopNo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel84)
                                .addComponent(jLabel85)
                                .addComponent(jLabel86)
                                .addComponent(baggage7)
                                .addComponent(refund7))
                            .addComponent(classLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        ticketPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel8.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel88.setText("-");

        dTime8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime8.setText("Departure Time");

        aTime8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime8.setText("Arrival Time");

        jLabel89.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel89.setText("No of Stops :");

        stopNo8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo8.setText("No");

        stopName8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName8.setText("Name");

        dateLavel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel8.setText("Date");

        aDate8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate8.setText("Date");

        jLabel90.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel90.setText("Flight No -");

        fID8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID8.setText("ID");

        jLabel91.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel91.setText("Refundability - ");

        jLabel92.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel92.setText("Class -");

        jLabel93.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel93.setText("Baggages -");

        baggage8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage8.setText("Kg");

        refund8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund8.setText("Y/N");

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setPreferredSize(new java.awt.Dimension(142, 87));

        price8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price8.setText("Tk");
        adviceLabel8 = new javax.swing.JLabel();
        adviceLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn8.setText("Select");
        selectBtn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn8MouseClicked(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel94.setText("BDT");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn8, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn8)
                .addContainerGap())
        );

        classLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName8.setText("Flight Name");

        toLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel8Layout = new javax.swing.GroupLayout(ticketPanel8);
        ticketPanel8.setLayout(ticketPanel8Layout);
        ticketPanel8Layout.setHorizontalGroup(
            ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel8Layout.createSequentialGroup()
                                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel91)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                                        .addComponent(fromLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel88)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel93)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel92)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel89)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel8Layout.setVerticalGroup(
            ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel8)
                            .addComponent(aDate8)
                            .addComponent(fName8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel8Layout.createSequentialGroup()
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(stopNo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel91)
                                .addComponent(jLabel92)
                                .addComponent(jLabel93)
                                .addComponent(baggage8)
                                .addComponent(refund8))
                            .addComponent(classLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        ticketPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketPanel9.setPreferredSize(new java.awt.Dimension(778, 178));

        fromLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel95.setText("-");

        dTime9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        dTime9.setText("Departure Time");

        aTime9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        aTime9.setText("Arrival Time");

        jLabel96.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel96.setText("No of Stops :");

        stopNo9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        stopNo9.setText("No");

        stopName9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        stopName9.setText("Name");

        dateLavel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        dateLavel9.setText("Date");

        aDate9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        aDate9.setText("Date");

        jLabel97.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jLabel97.setText("Flight No -");

        fID9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        fID9.setText("ID");

        jLabel98.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel98.setText("Refundability - ");

        jLabel99.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel99.setText("Class -");

        jLabel100.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel100.setText("Baggages -");

        baggage9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        baggage9.setText("Kg");

        refund9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        refund9.setText("Y/N");

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setPreferredSize(new java.awt.Dimension(142, 87));

        price9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        price9.setText("Tk");
        adviceLabel9 = new javax.swing.JLabel();
        adviceLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));

        selectBtn9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        selectBtn9.setText("Select");
        selectBtn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectBtn9MouseClicked(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel101.setText("BDT");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn9, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(price9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel101, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(selectBtn9)
                .addContainerGap())
        );

        classLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        fName9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        fName9.setText("Flight Name");

        toLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        javax.swing.GroupLayout ticketPanel9Layout = new javax.swing.GroupLayout(ticketPanel9);
        ticketPanel9.setLayout(ticketPanel9Layout);
        ticketPanel9Layout.setHorizontalGroup(
            ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fID9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel9Layout.createSequentialGroup()
                                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ticketPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel98)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(refund9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                                        .addComponent(fromLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel95)
                                        .addGap(111, 111, 111)))
                                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel100)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(baggage9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateLavel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dTime9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(toLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fName9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aTime9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aDate9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                            .addGroup(ticketPanel9Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel99)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(classLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ticketPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel96)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stopName9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ticketPanel9Layout.setVerticalGroup(
            ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(dTime9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(aTime9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fromLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLavel9)
                            .addComponent(aDate9)
                            .addComponent(fName9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketPanel9Layout.createSequentialGroup()
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(stopNo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopName9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fID9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ticketPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel98)
                                .addComponent(jLabel99)
                                .addComponent(jLabel100)
                                .addComponent(baggage9)
                                .addComponent(refund9))
                            .addComponent(classLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ticketPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ticketPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ticketPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(ticketPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ticketPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                                            .addComponent(ticketPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                                            .addComponent(ticketPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                                            .addComponent(ticketPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(290, 290, 290)
                                        .addComponent(noFlight)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ticketPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                            .addComponent(ticketPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                            .addComponent(ticketPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(noFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ticketPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(mainPanel);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel8.setPreferredSize(new java.awt.Dimension(769, 40));

        jLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel7.setText("Departure Time");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 255, 255), new java.awt.Color(102, 102, 102)));
        jPanel19.setPreferredSize(new java.awt.Dimension(125, 22));

        buttonGroup1.add(time00);
        time00.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        time00.setText("12 am - 06 am");
        time00.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        time00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time00ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time00)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(time00, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 255, 255), new java.awt.Color(102, 102, 102)));

        buttonGroup1.add(time06);
        time06.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        time06.setText("6 am - 12 pm");
        time06.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        time06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time06ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time06)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(time06, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 255, 255), new java.awt.Color(102, 102, 102)));
        jPanel5.setPreferredSize(new java.awt.Dimension(125, 22));

        buttonGroup1.add(time12);
        time12.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        time12.setText("12 pm - 6 pm");
        time12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        time12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(time12)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 255, 255), new java.awt.Color(102, 102, 102)));
        jPanel16.setPreferredSize(new java.awt.Dimension(125, 22));

        buttonGroup1.add(time18);
        time18.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        time18.setText("6 pm - 12 am");
        time18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        time18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(time18)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        earliest.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        earliest.setText("E a r l i e s t");
        earliest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                earliestActionPerformed(evt);
            }
        });

        fastest.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        fastest.setText("F a s t e s t");
        fastest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastestActionPerformed(evt);
            }
        });

        cheapest.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        cheapest.setText("C h e a p e s t");
        cheapest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheapestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cheapest, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(earliest, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fastest, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cheapest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(earliest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fastest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cheapestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheapestActionPerformed

        
                    try {
                        int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                        String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ORDER BY Price ASC";
                        String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ";

                        PreparedStatement ps = con.prepareStatement(
                            query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        PreparedStatement ps1 = con.prepareStatement(query1);

                        // Convert fDate to java.sql.Date if needed
                        java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                        // Set parameters for both queries
                        ps.setString(1, source);
                        ps.setString(2, destination);
                        ps.setString(3, fClass);
                        ps.setDate(4, sqlDate);

                        ps1.setString(1, source);
                        ps1.setString(2, destination);
                        ps1.setString(3, fClass);
                        ps1.setDate(4, sqlDate);

                        int rowCount = 0;

                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }

                    
    }//GEN-LAST:event_cheapestActionPerformed

    private void fastestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastestActionPerformed

        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

            // Prepare SQL queries
            String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ORDER BY Journey_Time ASC";
            String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ";

            PreparedStatement ps = con.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            PreparedStatement ps1 = con.prepareStatement(query1);

            // Convert fDate to java.sql.Date if needed
            java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

            // Set parameters for both queries
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setString(3, fClass);
            ps.setDate(4, sqlDate);

            ps1.setString(1, source);
            ps1.setString(2, destination);
            ps1.setString(3, fClass);
            ps1.setDate(4, sqlDate);

            int rowCount = 0;

            // Execute the row count query
            ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
            }

    }//GEN-LAST:event_fastestActionPerformed

    private void earliestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_earliestActionPerformed
        // TODO add your handling code here:

        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

            // Prepare SQL queries
            String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ORDER BY Departure_Time ASC";
            String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? ";

            PreparedStatement ps = con.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            PreparedStatement ps1 = con.prepareStatement(query1);

            // Convert fDate to java.sql.Date if needed
            java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

            // Set parameters for both queries
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setString(3, fClass);
            ps.setDate(4, sqlDate);

            ps1.setString(1, source);
            ps1.setString(2, destination);
            ps1.setString(3, fClass);
            ps1.setDate(4, sqlDate);

            int rowCount = 0;

            // Execute the row count query
            ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
            }

    }//GEN-LAST:event_earliestActionPerformed
    

    private void selectBtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn3MouseClicked
       showBookingSummaryDialog(price3.getText(), username, flightID[3]);
    }

    
    private void selectBtn2MouseClicked(java.awt.event.MouseEvent evt) {
       showBookingSummaryDialog(price2.getText(), username, flightID[2]);
    }


    private void selectBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn1MouseClicked
       showBookingSummaryDialog(price1.getText(), username, flightID[1]);
    }
    
    private void stopCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopCBActionPerformed
        // TODO add your handling code here:
        if(nonRefundCB.isSelected()){

            try {
                int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Database Connection
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                // Prepare SQL queries
                String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                PreparedStatement ps = con.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                PreparedStatement ps1 = con.prepareStatement(query1);

                // Convert fDate to java.sql.Date if needed
                java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                // Set parameters for both queries
                ps.setString(1, source);
                ps.setString(2, destination);
                ps.setString(3, fClass);
                ps.setDate(4, sqlDate);
                ps.setString(5, "No");
                ps.setInt(6, 1);

                ps1.setString(1, source);
                ps1.setString(2, destination);
                ps1.setString(3, fClass);
                ps1.setDate(4, sqlDate);
                ps1.setString(5, "No");
                ps1.setInt(6, 1);

                int rowCount = 0;

                // Execute the row count query
                ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                }
            }
            else if(refundCB.isSelected()){

                try {
                    int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                    // Load MySQL JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Database Connection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                    // Prepare SQL queries
                    String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                    String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                    PreparedStatement ps = con.prepareStatement(
                        query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                    );
                    PreparedStatement ps1 = con.prepareStatement(query1);

                    // Convert fDate to java.sql.Date if needed
                    java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                    // Set parameters for both queries
                    ps.setString(1, source);
                    ps.setString(2, destination);
                    ps.setString(3, fClass);
                    ps.setDate(4, sqlDate);
                    ps.setString(5, "Yes");
                    ps.setInt(6, 1);

                    ps1.setString(1, source);
                    ps1.setString(2, destination);
                    ps1.setString(3, fClass);
                    ps1.setDate(4, sqlDate);
                    ps1.setString(5, "Yes");
                    ps1.setInt(6, 1);

                    int rowCount = 0;

                    // Execute the row count query
                    ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                            // JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                    }
                }
                else{
                    try {
                        int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                        String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `No_of_Stops` = ? ";
                        String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `No_of_Stops` = ?";

                        PreparedStatement ps = con.prepareStatement(
                            query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        PreparedStatement ps1 = con.prepareStatement(query1);

                        // Convert fDate to java.sql.Date if needed
                        java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                        // Set parameters for both queries
                        ps.setString(1, source);
                        ps.setString(2, destination);
                        ps.setString(3, fClass);
                        ps.setDate(4, sqlDate);
                        ps.setString(5, "1");

                        ps1.setString(1, source);
                        ps1.setString(2, destination);
                        ps1.setString(3, fClass);
                        ps1.setDate(4, sqlDate);
                        ps1.setString(5, "1");

                        int rowCount = 0;

                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }
                    }

    }//GEN-LAST:event_stopCBActionPerformed

    private void nonStopCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonStopCBActionPerformed
        // TODO add your handling code here:

        if(nonRefundCB.isSelected()){
            try {
                int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Database Connection
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                // Prepare SQL queries
                String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                PreparedStatement ps = con.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                PreparedStatement ps1 = con.prepareStatement(query1);

                // Convert fDate to java.sql.Date if needed
                java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                // Set parameters for both queries
                ps.setString(1, source);
                ps.setString(2, destination);
                ps.setString(3, fClass);
                ps.setDate(4, sqlDate);
                ps.setString(5, "No");
                ps.setInt(6, 0);

                ps1.setString(1, source);
                ps1.setString(2, destination);
                ps1.setString(3, fClass);
                ps1.setDate(4, sqlDate);
                ps1.setString(5, "No");
                ps1.setInt(6, 0);

                int rowCount = 0;

                // Execute the row count query
                ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                }

            }else if(refundCB.isSelected()){
                try {
                    int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                    // Load MySQL JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Database Connection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                    // Prepare SQL queries
                    String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                    String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                    PreparedStatement ps = con.prepareStatement(
                        query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                    );
                    PreparedStatement ps1 = con.prepareStatement(query1);

                    // Convert fDate to java.sql.Date if needed
                    java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                    // Set parameters for both queries
                    ps.setString(1, source);
                    ps.setString(2, destination);
                    ps.setString(3, fClass);
                    ps.setDate(4, sqlDate);
                    ps.setString(5, "Yes");
                    ps.setInt(6, 0);

                    ps1.setString(1, source);
                    ps1.setString(2, destination);
                    ps1.setString(3, fClass);
                    ps1.setDate(4, sqlDate);
                    ps1.setString(5, "Yes");
                    ps1.setInt(6, 0);

                    int rowCount = 0;

                    // Execute the row count query
                    ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                    }

                }
                else{
                    try {
                        int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                        String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `No_of_Stops` = ? ";
                        String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `No_of_Stops` = ?";

                        PreparedStatement ps = con.prepareStatement(
                            query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        PreparedStatement ps1 = con.prepareStatement(query1);

                        // Convert fDate to java.sql.Date if needed
                        java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                        // Set parameters for both queries
                        ps.setString(1, source);
                        ps.setString(2, destination);
                        ps.setString(3, fClass);
                        ps.setDate(4, sqlDate);
                        ps.setInt(5, 0);

                        ps1.setString(1, source);
                        ps1.setString(2, destination);
                        ps1.setString(3, fClass);
                        ps1.setDate(4, sqlDate);
                        ps1.setInt(5, 0);

                        int rowCount = 0;

                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }

                    }

    }//GEN-LAST:event_nonStopCBActionPerformed

    private void refundCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundCBActionPerformed
        // TODO add your handling code here:

        if(nonStopCB.isSelected()){

            try {
                int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Database Connection
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                // Prepare SQL queries
                String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                PreparedStatement ps = con.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                PreparedStatement ps1 = con.prepareStatement(query1);

                // Convert fDate to java.sql.Date if needed
                java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                // Set parameters for both queries
                ps.setString(1, source);
                ps.setString(2, destination);
                ps.setString(3, fClass);
                ps.setDate(4, sqlDate);
                ps.setString(5, "Yes");
                ps.setInt(6, 0);

                ps1.setString(1, source);
                ps1.setString(2, destination);
                ps1.setString(3, fClass);
                ps1.setDate(4, sqlDate);
                ps1.setString(5, "Yes");
                ps1.setInt(6, 0);

                int rowCount = 0;

                // Execute the row count query
                ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                }

            }else if(stopCB.isSelected()){

                try {
                    int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                    // Load MySQL JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Database Connection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                    // Prepare SQL queries
                    String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                    String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                    PreparedStatement ps = con.prepareStatement(
                        query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                    );
                    PreparedStatement ps1 = con.prepareStatement(query1);

                    // Convert fDate to java.sql.Date if needed
                    java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                    // Set parameters for both queries
                    ps.setString(1, source);
                    ps.setString(2, destination);
                    ps.setString(3, fClass);
                    ps.setDate(4, sqlDate);
                    ps.setString(5, "Yes");
                    ps.setInt(6, 1);

                    ps1.setString(1, source);
                    ps1.setString(2, destination);
                    ps1.setString(3, fClass);
                    ps1.setDate(4, sqlDate);
                    ps1.setString(5, "Yes");
                    ps1.setInt(6, 1);

                    int rowCount = 0;

                    // Execute the row count query
                    ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                            // JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                    }

                } else{

                    try {
                        int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                        String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? ";
                        String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ?";

                        PreparedStatement ps = con.prepareStatement(
                            query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        PreparedStatement ps1 = con.prepareStatement(query1);

                        // Convert fDate to java.sql.Date if needed
                        java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                        // Set parameters for both queries
                        ps.setString(1, source);
                        ps.setString(2, destination);
                        ps.setString(3, fClass);
                        ps.setDate(4, sqlDate);
                        ps.setString(5, "Yes");

                        ps1.setString(1, source);
                        ps1.setString(2, destination);
                        ps1.setString(3, fClass);
                        ps1.setDate(4, sqlDate);
                        ps1.setString(5, "Yes");

                        int rowCount = 0;

                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                            // JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }

                    }

    }//GEN-LAST:event_refundCBActionPerformed

    private void nonRefundCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonRefundCBActionPerformed

        if(nonStopCB.isSelected()){

            try {
                int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Database Connection
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                // Prepare SQL queries
                String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                PreparedStatement ps = con.prepareStatement(
                    query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                PreparedStatement ps1 = con.prepareStatement(query1);

                // Convert fDate to java.sql.Date if needed
                java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                // Set parameters for both queries
                ps.setString(1, source);
                ps.setString(2, destination);
                ps.setString(3, fClass);
                ps.setDate(4, sqlDate);
                ps.setString(5, "No");
                ps.setInt(6, 0);

                ps1.setString(1, source);
                ps1.setString(2, destination);
                ps1.setString(3, fClass);
                ps1.setDate(4, sqlDate);
                ps1.setString(5, "No");
                ps1.setInt(6, 0);

                int rowCount = 0;

                // Execute the row count query
                ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                            // JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                }

            } else if(stopCB.isSelected()){

                try {
                    int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                    // Load MySQL JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Database Connection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                    // Prepare SQL queries
                    String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";
                    String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? AND `No_of_Stops` = ?";

                    PreparedStatement ps = con.prepareStatement(
                        query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                    );
                    PreparedStatement ps1 = con.prepareStatement(query1);

                    // Convert fDate to java.sql.Date if needed
                    java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                    // Set parameters for both queries
                    ps.setString(1, source);
                    ps.setString(2, destination);
                    ps.setString(3, fClass);
                    ps.setDate(4, sqlDate);
                    ps.setString(5, "No");
                    ps.setInt(6, 1);

                    ps1.setString(1, source);
                    ps1.setString(2, destination);
                    ps1.setString(3, fClass);
                    ps1.setDate(4, sqlDate);
                    ps1.setString(5, "No");
                    ps1.setInt(6, 1);

                    int rowCount = 0;

                    // Execute the row count query
                    ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                    } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                    }
                }else{

                    try { 
                        int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                        String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ? ";
                        String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Refundability` = ?";

                        PreparedStatement ps = con.prepareStatement(
                            query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY
                        );
                        PreparedStatement ps1 = con.prepareStatement(query1);

                        // Convert fDate to java.sql.Date if needed
                        java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

                        // Set parameters for both queries
                        ps.setString(1, source);
                        ps.setString(2, destination);
                        ps.setString(3, fClass);
                        ps.setDate(4, sqlDate);
                        ps.setString(5, "No");

                        ps1.setString(1, source);
                        ps1.setString(2, destination);
                        ps1.setString(3, fClass);
                        ps1.setDate(4, sqlDate);
                        ps1.setString(5, "No");

                        int rowCount = 0;

                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }

                    }

    }//GEN-LAST:event_nonRefundCBActionPerformed

    private void showBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBtnActionPerformed
        // TODO add your handling code here:
        
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        nonRefundCB.setSelected(false);
        refundCB.setSelected(false);
        nonStopCB.setSelected(false);
        stopCB.setSelected(false);
        time00.setSelected(false);
        time06.setSelected(false);
        time12.setSelected(false);
        time18.setSelected(false);
        
        noFlight.setVisible(false);

        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

            // Prepare SQL queries
            String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ?";
            String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ?";

            PreparedStatement ps = con.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            PreparedStatement ps1 = con.prepareStatement(query1);

            // Convert fDate to java.sql.Date if needed
            java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

            // Set parameters for both queries
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setString(3, fClass);
            ps.setDate(4, sqlDate);

            ps1.setString(1, source);
            ps1.setString(2, destination);
            ps1.setString(3, fClass);
            ps1.setDate(4, sqlDate);

            int rowCount = 0;

            // Execute the row count query
            ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
                        
                        String fid = rs.getString(1);
                        id[j].setText(fid);
                        flightID[j] = Integer.parseInt(fid); // Store numeric flight ID

                        
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                           //  JOptionPane.showMessageDialog(null, "Something wrong");
                                }

            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
            }

    }//GEN-LAST:event_showBtnActionPerformed

    
    private void showBookingSummaryDialog(String priceText, String username, int flightId) {
    AtomicInteger qty = new AtomicInteger(1);
    AtomicReference<Double> total = new AtomicReference<>(0.0);

    try {
        double baseFare = Double.parseDouble(priceText.replace(" BDT", ""));
        double convenienceFee = baseFare * 0.03;

        // Dialog setup...
        JDialog dialog = new JDialog(this, "Booking Summary", true);
        dialog.setSize(420, 320);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
            "Ticket Price Details",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16),
            new Color(30, 30, 30)
        ));
        contentPanel.setBackground(new Color(250, 250, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font valueFont = new Font("Segoe UI", Font.BOLD, 14);

        // Add Labels and Spinner similar to your original code
        // Base Fare
        gbc.gridx = 0; gbc.gridy = 0;
        contentPanel.add(new JLabel("Base Fare:") {{ setFont(labelFont); }}, gbc);
        gbc.gridx = 1;
        JLabel baseFareLabel = new JLabel(String.format("%.2f BDT", baseFare));
        baseFareLabel.setFont(valueFont);
        contentPanel.add(baseFareLabel, gbc);

        // Convenience Fee
        gbc.gridx = 0; gbc.gridy++;
        contentPanel.add(new JLabel("Convenience Fee (3%):") {{ setFont(labelFont); }}, gbc);
        gbc.gridx = 1;
        JLabel feeLabel = new JLabel(String.format("%.2f BDT", convenienceFee));
        feeLabel.setFont(valueFont);
        contentPanel.add(feeLabel, gbc);

        // Number of Tickets
        gbc.gridx = 0; gbc.gridy++;
        contentPanel.add(new JLabel("Number of Tickets:") {{ setFont(labelFont); }}, gbc);
        gbc.gridx = 1;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 4, 1);
        JSpinner ticketSpinner = new JSpinner(spinnerModel);
        ((JSpinner.DefaultEditor) ticketSpinner.getEditor()).getTextField().setFont(valueFont);
        contentPanel.add(ticketSpinner, gbc);

        // Total Price
        gbc.gridx = 0; gbc.gridy++;
        contentPanel.add(new JLabel("Total Price:") {{ setFont(labelFont); }}, gbc);
        gbc.gridx = 1;
        JLabel totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        totalLabel.setForeground(new Color(0, 102, 204));
        contentPanel.add(totalLabel, gbc);

        // Update total price logic
        Runnable updateTotal = () -> {
            int count = (int) ticketSpinner.getValue();
            qty.set(count);
            double calculatedTotal = (baseFare + convenienceFee) * count;
            total.set(calculatedTotal);
            totalLabel.setText(String.format("%.2f BDT", calculatedTotal));
        };

        updateTotal.run();
        ticketSpinner.addChangeListener(e -> updateTotal.run());

        // Buy Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton buyBtn = new JButton("Buy Now");
        buyBtn.setFocusPainted(false);
        buyBtn.setBackground(new Color(0, 153, 76));
        buyBtn.setForeground(Color.WHITE);
        buyBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buyBtn.setPreferredSize(new Dimension(100, 35));

        buyBtn.addActionListener(e -> {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
                 PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO Bookings (Username, Flight_ID, No_of_tickets, Total_price) VALUES (?, ?, ?, ?)")) {

                pst.setString(1, username);
                pst.setInt(2, flightId);
                pst.setInt(3, qty.get());
                pst.setDouble(4, total.get());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Booking Successful!");
                dialog.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Booking Failed: " + ex.getMessage());
            }
        });

        buttonPanel.add(buyBtn);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error processing ticket price.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    private void selectBtnMouseClicked(java.awt.event.MouseEvent evt) {
        showBookingSummaryDialog(price.getText(), username, flightID[0]);
}
//GEN-LAST:event_selectBtnMouseClicked

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        nonRefundCB.setSelected(false);
        refundCB.setSelected(false);
        nonStopCB.setSelected(false);
        stopCB.setSelected(false);
        time00.setSelected(false);
        time06.setSelected(false);
        time12.setSelected(false);
        time18.setSelected(false);
        
        
        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

            // Prepare SQL queries
            String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ?";
            String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ?";

            PreparedStatement ps = con.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            PreparedStatement ps1 = con.prepareStatement(query1);

            // Convert fDate to java.sql.Date if needed
            java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());

            // Set parameters for both queries
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setString(3, fClass);
            ps.setDate(4, sqlDate);

            ps1.setString(1, source);
            ps1.setString(2, destination);
            ps1.setString(3, fClass);
            ps1.setDate(4, sqlDate);

            int rowCount = 0;

            // Execute the row count query
            ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
            }
        
    }//GEN-LAST:event_clearBtnActionPerformed

    private void time00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time00ActionPerformed
        // TODO add your handling code here:
        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                 String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 
                 PreparedStatement ps = con.prepareStatement(
                         query,
                         ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY
                 );
                 PreparedStatement ps1 = con.prepareStatement(query1);
                 
                 // Convert fDate to java.sql.Date if needed
                 java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());
                 
                 // Set parameters for both queries
                 ps.setString(1, source);
                 ps.setString(2, destination);
                 ps.setString(3, fClass);
                 ps.setDate(4, sqlDate); 
                 ps.setTime(5, java.sql.Time.valueOf("00:00:00"));  // Start of time range
                 ps.setTime(6, java.sql.Time.valueOf("05:59:59"));  // End of time range
                 
                 ps1.setString(1, source);
                 ps1.setString(2, destination);
                 ps1.setString(3, fClass);
                 ps1.setDate(4, sqlDate);
                 ps1.setTime(5, java.sql.Time.valueOf("00:00:00"));  // Start of time range
                 ps1.setTime(6, java.sql.Time.valueOf("05:59:59"));  // End of time range
                 
                 int rowCount = 0;


                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                            // JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }
    }//GEN-LAST:event_time00ActionPerformed

    private void time06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time06ActionPerformed
        // TODO add your handling code here:
        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                 String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 
                 PreparedStatement ps = con.prepareStatement(
                         query,
                         ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY
                 );
                 PreparedStatement ps1 = con.prepareStatement(query1);
                 
                 // Convert fDate to java.sql.Date if needed
                 java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());
                 
                 // Set parameters for both queries
                 ps.setString(1, source);
                 ps.setString(2, destination);
                 ps.setString(3, fClass);
                 ps.setDate(4, sqlDate);
                 ps.setTime(5, java.sql.Time.valueOf("06:00:00"));  // Start of time range
                 ps.setTime(6, java.sql.Time.valueOf("11:59:59"));  // End of time range
                 
                 ps1.setString(1, source);
                 ps1.setString(2, destination);
                 ps1.setString(3, fClass);
                 ps1.setDate(4, sqlDate);
                 ps1.setTime(5, java.sql.Time.valueOf("06:00:00"));  // Start of time range
                 ps1.setTime(6, java.sql.Time.valueOf("11:59:59"));  // End of time range
                 
                 int rowCount = 0;


                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }
    }//GEN-LAST:event_time06ActionPerformed

    private void time12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time12ActionPerformed
        // TODO add your handling code here:
       try {
           int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                 String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 
                 PreparedStatement ps = con.prepareStatement(
                         query,
                         ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY
                 );
                 PreparedStatement ps1 = con.prepareStatement(query1);
                 
                 // Convert fDate to java.sql.Date if needed
                 java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());
                 
                 // Set parameters for both queries
                 ps.setString(1, source);
                 ps.setString(2, destination);
                 ps.setString(3, fClass);
                 ps.setDate(4, sqlDate);
                 ps.setTime(5, java.sql.Time.valueOf("12:00:00"));  // Start of time range
                 ps.setTime(6, java.sql.Time.valueOf("17:59:59"));  // End of time range
                 
                 ps1.setString(1, source);
                 ps1.setString(2, destination);
                 ps1.setString(3, fClass);
                 ps1.setDate(4, sqlDate);
                 ps1.setTime(5, java.sql.Time.valueOf("12:00:00"));  // Start of time range
                 ps1.setTime(6, java.sql.Time.valueOf("17:59:59"));  // End of time range
                 
                 int rowCount = 0;


                        // Execute the row count query
                        ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                  j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }
    }//GEN-LAST:event_time12ActionPerformed

    private void time18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time18ActionPerformed
        // TODO add your handling code here:
        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
                        // Load MySQL JDBC Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Database Connection
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

                        // Prepare SQL queries
                 String query = "SELECT * FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 String query1 = "SELECT COUNT(*) AS row_count FROM flight_details WHERE `Source` = ? AND `Destination` = ? AND `Class` = ? AND `Date` = ? AND `Departure_Time` BETWEEN ? AND ?";
                 
                 PreparedStatement ps = con.prepareStatement(
                         query,
                         ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY
                 );
                 PreparedStatement ps1 = con.prepareStatement(query1);
                 
                 // Convert fDate to java.sql.Date if needed
                 java.sql.Date sqlDate = new java.sql.Date(fDate.getTime());
                 
                 // Set parameters for both queries
                 ps.setString(1, source);
                 ps.setString(2, destination);
                 ps.setString(3, fClass);
                 ps.setDate(4, sqlDate);
                 ps.setTime(5, java.sql.Time.valueOf("18:00:00"));  // Start of time range
                 ps.setTime(6, java.sql.Time.valueOf("23:59:59"));  // End of time range
                 
                 ps1.setString(1, source);
                 ps1.setString(2, destination);
                 ps1.setString(3, fClass);
                 ps1.setDate(4, sqlDate);
                 ps1.setTime(5, java.sql.Time.valueOf("18:00:00"));  // Start of time range
                 ps1.setTime(6, java.sql.Time.valueOf("23:59:59"));  // End of time range
                 
                 int rowCount = 0;


                        // Execute the row count query
                       ResultSet rs1 = ps1.executeQuery();
                        
                        if (rs1.next()) {
                            rowCount = rs1.getInt("row_count"); // Retrieve row count as an integer
                            no.setText(String.valueOf(rowCount));    // Convert integer to string and set it in the label
                            System.out.println("Row count: " + rowCount);

                            if (tp != null) {
                                    for (int i = tp.length - 1; i >= 0; i--) {
                                        if (tp[i] != null) {
                                            tp[i].setVisible(rowCount > i);
                                        }
                                    }
                                }

                                noFlight.setVisible(rowCount < 1);
                                
                        }
                        
                        ResultSet rs = ps.executeQuery();
                        
                        if (rs.next()) {

                        // Execute the main query for flight details
                        

                        // Check if there is a second row

                        for (int i = 1; i <= rowCount; i++) {
                            if (rs.absolute(i)) {  // Moves the cursor directly to row i
                                System.out.println("Flight found on row " + i);
                                 j=i-1;
                        
                        tp[j].setVisible(true);
                        id[j].setText(rs.getString(1));
                        nam[j].setText(rs.getString(2));
                        frm[j].setText(rs.getString(3));
                        to[j].setText(rs.getString(4));
                        cl[j].setText(rs.getString(5));
                        dl[j].setText(rs.getString(6));
                        dt[j].setText(rs.getString(7));
                        at[j].setText(rs.getString(8));
                        ad[j].setText(rs.getString(9));
                        st[j].setText(rs.getString(10));
                        sNam[j].setText(rs.getString(11));
                        bag[j].setText(rs.getString(12));
                        ref[j].setText(rs.getString(13));
                        tk[j].setText(rs.getString(14));
        // --- Advice Logic Call ---
predictFuturePricesAndAdvise(rs, j);

                            }

                        }
                        
                        
                            
                            rs.close();
                            rs1.close();
                            ps.close();
                            ps1.close();
                            con.close();
                         }else {
                             System.out.println("No flights found");
                             //JOptionPane.showMessageDialog(null, "Something wrong");
                                }

                        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while searching for flights. Please try again.");
                        }
    }//GEN-LAST:event_time18ActionPerformed

    
    private void selectBtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn4MouseClicked
        showBookingSummaryDialog(price4.getText(), username, flightID[4]);
    }
    
    
    private void selectBtn5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn5MouseClicked
        showBookingSummaryDialog(price5.getText(), username, flightID[5]);
    }
    

    private void selectBtn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn6MouseClicked
        showBookingSummaryDialog(price6.getText(), username, flightID[6]);
    }
    

    private void selectBtn7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn7MouseClicked
        showBookingSummaryDialog(price7.getText(), username, flightID[7]);
    }
    

    private void selectBtn8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn8MouseClicked
        showBookingSummaryDialog(price8.getText(), username, flightID[8]);
    }
    
    
    private void selectBtn9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectBtn9MouseClicked
        showBookingSummaryDialog(price9.getText(), username, flightID[9]);
    }
    

    private void backBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBTNActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mp.setVisible(true);
    }//GEN-LAST:event_backBTNActionPerformed
/* nonRefundCB.setSelected(false);
        refundCB.setSelected(false);
        nonStopCB.setSelected(false);
        stopCB.setSelected(false); */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            int j = 0;
            System.out.println("Running advice logic for ticket " + j);
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FlightBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlightBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlightBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlightBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FlightBooking().setVisible(true);
        });
    }

        private javax.swing.JLabel adviceLabel0;
    private javax.swing.JLabel adviceLabel1;
    private javax.swing.JLabel adviceLabel2;
    private javax.swing.JLabel adviceLabel3;
    private javax.swing.JLabel adviceLabel4;
    private javax.swing.JLabel adviceLabel5;
    private javax.swing.JLabel adviceLabel6;
    private javax.swing.JLabel adviceLabel7;
    private javax.swing.JLabel adviceLabel8;
    private javax.swing.JLabel adviceLabel9;
// Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel aDate;
    private javax.swing.JLabel aDate1;
    private javax.swing.JLabel aDate2;
    private javax.swing.JLabel aDate3;
    private javax.swing.JLabel aDate4;
    private javax.swing.JLabel aDate5;
    private javax.swing.JLabel aDate6;
    private javax.swing.JLabel aDate7;
    private javax.swing.JLabel aDate8;
    private javax.swing.JLabel aDate9;
    public javax.swing.JLabel aTime;
    private javax.swing.JLabel aTime1;
    private javax.swing.JLabel aTime2;
    private javax.swing.JLabel aTime3;
    private javax.swing.JLabel aTime4;
    private javax.swing.JLabel aTime5;
    private javax.swing.JLabel aTime6;
    private javax.swing.JLabel aTime7;
    private javax.swing.JLabel aTime8;
    private javax.swing.JLabel aTime9;
    private javax.swing.JButton backBTN;
    public javax.swing.JLabel baggage;
    private javax.swing.JLabel baggage1;
    private javax.swing.JLabel baggage2;
    private javax.swing.JLabel baggage3;
    private javax.swing.JLabel baggage4;
    private javax.swing.JLabel baggage5;
    private javax.swing.JLabel baggage6;
    private javax.swing.JLabel baggage7;
    private javax.swing.JLabel baggage8;
    private javax.swing.JLabel baggage9;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton cheapest;
    public javax.swing.JLabel classLabel;
    public javax.swing.JLabel classLabel1;
    public javax.swing.JLabel classLabel2;
    public javax.swing.JLabel classLabel3;
    public javax.swing.JLabel classLabel4;
    public javax.swing.JLabel classLabel5;
    public javax.swing.JLabel classLabel6;
    public javax.swing.JLabel classLabel7;
    public javax.swing.JLabel classLabel8;
    public javax.swing.JLabel classLabel9;
    private javax.swing.JButton clearBtn;
    public javax.swing.JLabel dTime;
    private javax.swing.JLabel dTime1;
    private javax.swing.JLabel dTime2;
    private javax.swing.JLabel dTime3;
    private javax.swing.JLabel dTime4;
    private javax.swing.JLabel dTime5;
    private javax.swing.JLabel dTime6;
    private javax.swing.JLabel dTime7;
    private javax.swing.JLabel dTime8;
    private javax.swing.JLabel dTime9;
    public javax.swing.JLabel dateLavel;
    private javax.swing.JLabel dateLavel1;
    private javax.swing.JLabel dateLavel2;
    private javax.swing.JLabel dateLavel3;
    private javax.swing.JLabel dateLavel4;
    private javax.swing.JLabel dateLavel5;
    private javax.swing.JLabel dateLavel6;
    private javax.swing.JLabel dateLavel7;
    private javax.swing.JLabel dateLavel8;
    private javax.swing.JLabel dateLavel9;
    private javax.swing.JButton earliest;
    public javax.swing.JLabel fID;
    private javax.swing.JLabel fID1;
    private javax.swing.JLabel fID2;
    private javax.swing.JLabel fID3;
    private javax.swing.JLabel fID4;
    private javax.swing.JLabel fID5;
    private javax.swing.JLabel fID6;
    private javax.swing.JLabel fID7;
    private javax.swing.JLabel fID8;
    private javax.swing.JLabel fID9;
    public javax.swing.JLabel fName;
    private javax.swing.JLabel fName1;
    private javax.swing.JLabel fName2;
    private javax.swing.JLabel fName3;
    private javax.swing.JLabel fName4;
    private javax.swing.JLabel fName5;
    private javax.swing.JLabel fName6;
    private javax.swing.JLabel fName7;
    private javax.swing.JLabel fName8;
    private javax.swing.JLabel fName9;
    private javax.swing.JButton fastest;
    public javax.swing.JLabel fromLabel;
    public javax.swing.JLabel fromLabel1;
    public javax.swing.JLabel fromLabel2;
    public javax.swing.JLabel fromLabel3;
    public javax.swing.JLabel fromLabel4;
    public javax.swing.JLabel fromLabel5;
    public javax.swing.JLabel fromLabel6;
    public javax.swing.JLabel fromLabel7;
    public javax.swing.JLabel fromLabel8;
    public javax.swing.JLabel fromLabel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel labelClass;
    public javax.swing.JLabel labelDate;
    public javax.swing.JLabel labelDestination;
    public javax.swing.JLabel labelSource;
    private javax.swing.JPanel mainPanel;
    public javax.swing.JLabel no;
    private javax.swing.JLabel noFlight;
    private javax.swing.JCheckBox nonRefundCB;
    private javax.swing.JCheckBox nonStopCB;
    public javax.swing.JLabel price;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel price2;
    private javax.swing.JLabel price3;
    private javax.swing.JLabel price4;
    private javax.swing.JLabel price5;
    private javax.swing.JLabel price6;
    private javax.swing.JLabel price7;
    private javax.swing.JLabel price8;
    private javax.swing.JLabel price9;
    public javax.swing.JLabel refund;
    private javax.swing.JLabel refund1;
    private javax.swing.JLabel refund2;
    private javax.swing.JLabel refund3;
    private javax.swing.JLabel refund4;
    private javax.swing.JLabel refund5;
    private javax.swing.JLabel refund6;
    private javax.swing.JLabel refund7;
    private javax.swing.JLabel refund8;
    private javax.swing.JLabel refund9;
    private javax.swing.JCheckBox refundCB;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton selectBtn1;
    private javax.swing.JButton selectBtn2;
    private javax.swing.JButton selectBtn3;
    private javax.swing.JButton selectBtn4;
    private javax.swing.JButton selectBtn5;
    private javax.swing.JButton selectBtn6;
    private javax.swing.JButton selectBtn7;
    private javax.swing.JButton selectBtn8;
    private javax.swing.JButton selectBtn9;
    private javax.swing.JButton showBtn;
    public javax.swing.JCheckBox stopCB;
    public javax.swing.JLabel stopName;
    private javax.swing.JLabel stopName1;
    private javax.swing.JLabel stopName2;
    private javax.swing.JLabel stopName3;
    private javax.swing.JLabel stopName4;
    private javax.swing.JLabel stopName5;
    private javax.swing.JLabel stopName6;
    private javax.swing.JLabel stopName7;
    private javax.swing.JLabel stopName8;
    private javax.swing.JLabel stopName9;
    public javax.swing.JLabel stopNo;
    private javax.swing.JLabel stopNo1;
    private javax.swing.JLabel stopNo2;
    private javax.swing.JLabel stopNo3;
    private javax.swing.JLabel stopNo4;
    private javax.swing.JLabel stopNo5;
    private javax.swing.JLabel stopNo6;
    private javax.swing.JLabel stopNo7;
    private javax.swing.JLabel stopNo8;
    private javax.swing.JLabel stopNo9;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JPanel ticketPanel1;
    private javax.swing.JPanel ticketPanel2;
    private javax.swing.JPanel ticketPanel3;
    private javax.swing.JPanel ticketPanel4;
    private javax.swing.JPanel ticketPanel5;
    private javax.swing.JPanel ticketPanel6;
    private javax.swing.JPanel ticketPanel7;
    private javax.swing.JPanel ticketPanel8;
    private javax.swing.JPanel ticketPanel9;
    private javax.swing.JRadioButton time00;
    private javax.swing.JRadioButton time06;
    private javax.swing.JRadioButton time12;
    private javax.swing.JRadioButton time18;
    public javax.swing.JLabel toLabel;
    public javax.swing.JLabel toLabel1;
    public javax.swing.JLabel toLabel2;
    public javax.swing.JLabel toLabel3;
    public javax.swing.JLabel toLabel4;
    public javax.swing.JLabel toLabel5;
    public javax.swing.JLabel toLabel6;
    public javax.swing.JLabel toLabel7;
    public javax.swing.JLabel toLabel8;
    public javax.swing.JLabel toLabel9;
    // End of variables declaration//GEN-END:variables
    
    

private String getSeasonFromDate(String dateStr) {
    try {
        LocalDate date = LocalDate.parse(dateStr);

        /* Rule: Every Thursday–Saturday is Off-Peak
        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.THURSDAY || dow == DayOfWeek.FRIDAY || dow == DayOfWeek.SATURDAY) {
            return "Off-Peak";
        }*/

        return holidaySeasonManager.getSeason(date); // checks for CSV-defined seasons
    } catch (Exception e) {
        return "Regular";
    }
}




private void predictFuturePricesAndAdvise(ResultSet rs, int j) {
    try {
        double todayPrice = Double.parseDouble(rs.getString(14));
        String travelClass = rs.getString(5);
        int stopovers = Integer.parseInt(rs.getString(10));
        String durationStr = rs.getString(12);

        // Parse duration into decimal hours
        double duration = 0;
        try {
            String[] parts = durationStr.split(" ");
            for (String part : parts) {
                if (part.contains("h")) duration += Integer.parseInt(part.replace("h", ""));
                else if (part.contains("m")) duration += Integer.parseInt(part.replace("m", "")) / 60.0;
            }
        } catch (Exception e) {
            duration = 1.0; // fallback
        }

        String source = rs.getString(3);
        String destination = rs.getString(4);
        String airline = rs.getString(2);
        LocalDate today = LocalDate.now();
        LocalDate departureDate = LocalDate.parse(rs.getString(6)); // fixed departure date

        long daysUntilDeparture = java.time.temporal.ChronoUnit.DAYS.between(today, departureDate);
        if (daysUntilDeparture <= 0) {
            advice[j].setText("Departure date is today or past.");
            advice[j].setForeground(new java.awt.Color(204, 0, 0));
            select[j].setBackground(new java.awt.Color(204, 0, 0));
            select[j].setForeground(java.awt.Color.WHITE);
            return;
        }

        Map<Integer, Double> predictions = new HashMap<>();
        double bestPrice = Double.MAX_VALUE;
        int bestDayBefore = 0;

        // Loop: simulate buying the ticket on each day before the fixed departure date
        for (int daysBefore = (int) daysUntilDeparture; daysBefore >= 1; daysBefore--) {
            LocalDate simulatedPurchaseDate = departureDate.minusDays(daysBefore);
            String season = getSeasonFromDate(departureDate.toString()); // still season of departure

            JSONObject json = new JSONObject();
            json.put("Airline", airline);
            json.put("Source", source);
            json.put("Destination", destination);
            json.put("Duration (hrs)", duration);
            json.put("Stopovers", stopovers);
            json.put("Class", travelClass);
            json.put("Seasonality", season);
            json.put("Days Before Departure", daysBefore); // THIS is the key factor

            JSONObject response = sendPostRequest(json);
            double predicted = response.getDouble("predicted_price");
            predictions.put(daysBefore, predicted);

            System.out.printf("[PREDICT] If booked %d days before (%s): %.2f BDT | For Departure: %s | Season: %s%n",
                    daysBefore, simulatedPurchaseDate, predicted, departureDate, season);

            if (predicted < bestPrice) {
                bestPrice = predicted;
                bestDayBefore = daysBefore;
            }
        }

        // Advice based on price comparison
        boolean isBest = true;
        boolean isWorst = false;

        for (double predicted : predictions.values()) {
            if (todayPrice >= predicted * 0.97) isBest = false;
            if (predicted <= todayPrice * 0.97) isWorst = true;
        }

        if (isBest) {
            advice[j].setText("Buy now – price may increase as departure nears.");
            advice[j].setForeground(new java.awt.Color(0, 153, 0));
            select[j].setBackground(new java.awt.Color(0, 153, 0));
            select[j].setForeground(java.awt.Color.WHITE);
            select[j].setToolTipText("Best price is available today.");
        } else if (isWorst) {
            advice[j].setText("Wait – model predicts better prices soon.");
            advice[j].setForeground(new java.awt.Color(204, 0, 0));
            select[j].setBackground(new java.awt.Color(204, 0, 0));
            select[j].setForeground(java.awt.Color.WHITE);
            select[j].setToolTipText("Better prices expected if you wait.");
        } else {
            advice[j].setText("Price is average – monitor regularly.");
            advice[j].setForeground(new java.awt.Color(255, 204, 0));
            select[j].setBackground(new java.awt.Color(255, 204, 0));
            select[j].setForeground(java.awt.Color.BLACK);
            select[j].setToolTipText("Could go up or down. Consider buying soon.");
        }

    } catch (Exception ex) {
        advice[j].setText("Prediction error");
        ex.printStackTrace();
    }
}



private JSONObject sendPostRequest(JSONObject json) throws Exception {
URL url = new URL("http://127.0.0.1:5000/predict");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("POST");
conn.setDoOutput(true);
conn.setRequestProperty("Content-Type", "application/json");

try (OutputStream os = conn.getOutputStream()) {
    byte[] input = json.toString().getBytes("utf-8");
    os.write(input, 0, input.length);
}

StringBuilder response = new StringBuilder();
try (BufferedReader br = new BufferedReader(
        new InputStreamReader(conn.getInputStream(), "utf-8"))) {
    String responseLine;
    while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
    }
}

return new JSONObject(response.toString());
}
}