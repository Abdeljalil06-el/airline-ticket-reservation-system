package airline.ticket.reservation.system;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LoadingScreen extends javax.swing.JFrame {

    public LoadingScreen() {
        initComponents();
        
        //AssistantWebhookServer.setSignupPageInstance(new LoginPage());
        //AssistantWebhookServer.startServer();

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        LoadingBar = new javax.swing.JProgressBar();
        LoadingLabel = new javax.swing.JLabel();
        LoadingPercent = new javax.swing.JLabel();
        BG_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));
        setUndecorated(true); // Sleek, borderless window
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoadingBar.setForeground(new Color(0, 204, 102)); // Neon green style
        LoadingBar.setBackground(new Color(30, 30, 30));
        LoadingBar.setBorderPainted(false);
        LoadingBar.setStringPainted(false);
        getContentPane().add(LoadingBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1000, 20));

        LoadingLabel.setFont(new java.awt.Font("Consolas", Font.BOLD, 18));
        LoadingLabel.setForeground(new Color(0, 255, 102));
        LoadingLabel.setText("Initializing system...");
        getContentPane().add(LoadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 600, 30));

        LoadingPercent.setFont(new java.awt.Font("Consolas", Font.BOLD, 18));
        LoadingPercent.setForeground(new Color(0, 255, 102));
        LoadingPercent.setHorizontalAlignment(SwingConstants.RIGHT);
        LoadingPercent.setText("0%");
        getContentPane().add(LoadingPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 540, 80, 30));

        BG_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Loading_Screen.jpg")));
        BG_Label.setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().add(BG_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        LoadingScreen ls = new LoadingScreen();
        ls.setVisible(true);
        new Loader(ls).execute();
        //AssistantWebhookServer.startServer();
    }

    static class Loader extends SwingWorker<Void, Integer> {
        private final LoadingScreen ls;
        private final String[] smartTips = {
            "Initializing core modules...",
            "Optimizing engine...",
            "Synchronizing cloud settings...",
            "Establishing secure connection...",
            "Decrypting data...",
            "Booting system AI...",
            "Verifying user identity...",
            "Rendering smooth visuals...",
            "Starting services...",
            "Preparing final interface..."
        };
        private final Random random = new Random();

        public Loader(LoadingScreen ls) {
            this.ls = ls;
        }

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(10 + random.nextInt(15)); // Slight randomness for realism
                publish(i);
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            int val = chunks.get(chunks.size() - 1);
            ls.LoadingBar.setValue(val);
            ls.LoadingPercent.setText(val + " %");

            if (val % 15 == 0 && val != 0 && val < 90) {
                ls.LoadingLabel.setText(smartTips[random.nextInt(smartTips.length)]);
            }

            if (val >= 95) {
                ls.LoadingLabel.setText("Launching application...");
            }
        }

                @Override
        protected void done() {
            Timer timer = new Timer(15, null);
            timer.addActionListener(e -> {
                float opacity = ls.getOpacity();
                if (opacity > 0.05f) {  // Prevent opacity from going below 0
                    ls.setOpacity(opacity - 0.05f);
                } else {
                    ((Timer) e.getSource()).stop();
                    ls.setVisible(false);
                    LoginPage sp=new LoginPage();
                    LoginOption lo= new LoginOption();
                    lo.setVisible(true); // Show the login screen
                    //sp.loadSavedCredentials();
                }
            });
            timer.start();
        }
    }

    // Variables
    private javax.swing.JLabel BG_Label;
    private javax.swing.JProgressBar LoadingBar;
    private javax.swing.JLabel LoadingLabel;
    private javax.swing.JLabel LoadingPercent;
}
