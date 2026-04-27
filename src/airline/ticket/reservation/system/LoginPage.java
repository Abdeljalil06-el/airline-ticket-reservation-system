package airline.ticket.reservation.system;

import javax.swing.*;     
import java.awt.Color;
import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class LoginPage extends javax.swing.JFrame {

   
    public LoginPage() {
        initComponents();
        
        loadSavedCredentials();
       
    }
    
        private static final String SECRET_KEY = "HorizonSecretKey"; // Must be 16 characters

        private String encrypt(String data) throws Exception {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }

        private String decrypt(String encryptedData) throws Exception {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        }

        private void saveCredentials() {
            try {
                Properties props = new Properties();
                File file = new File("login.properties");

                String username = UsernameTXT.getText().trim();
                String password = new String(PasswordTXT.getPassword()).trim();

                if (CheckBox.isSelected()) {
                    props.setProperty("username", encrypt(username));
                    props.setProperty("password", encrypt(password));
                    props.setProperty("remember", "true");
                } else {
                    props.setProperty("username", "");
                    props.setProperty("password", "");
                    props.setProperty("remember", "false");
                }

                FileOutputStream out = new FileOutputStream(file);
                props.store(out, "Login Details");
                out.close();
                System.out.println("Encrypted and saved login.properties");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        public void loadSavedCredentials() {
            try {
                Properties props = new Properties();
                File file = new File("login.properties");
                if (file.exists()) {
                    FileInputStream in = new FileInputStream(file);
                    props.load(in);
                    in.close();

                    boolean remember = Boolean.parseBoolean(props.getProperty("remember", "false"));
                    CheckBox.setSelected(remember);

                    if (remember) {
                        String encryptedUsername = props.getProperty("username", "");
                        String encryptedPassword = props.getProperty("password", "");
                        UsernameTXT.setText(decrypt(encryptedUsername));
                        PasswordTXT.setText(decrypt(encryptedPassword));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
    @SuppressWarnings("unchecked")
    
    
     void isEnter(JPanel btn){
        btn.setBackground(new Color(0,153,255));
    }
    
    void isExit(JPanel btn){
        btn.setBackground(new Color(0,51,204));
    }
    
    // These methods let the assistant fill in the signup form
public void setUsername(String username) {
    UsernameTXT.setText(username);  // Make sure this matches your actual variable
}

public void setPassword(String password) {
    PasswordTXT.setText(password);  // Make sure this matches your actual variable
}
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        SidebarBasePanel = new javax.swing.JPanel();
        SideBar = new javax.swing.JPanel();
        LogoPanel = new javax.swing.JPanel();
        LogoLabel = new javax.swing.JLabel();
        CreateAccountJP = new javax.swing.JPanel();
        CA_JL = new javax.swing.JLabel();
        ForgetPasswordJP = new javax.swing.JPanel();
        FP_JL = new javax.swing.JLabel();
        ExitJP = new javax.swing.JPanel();
        E_JL = new javax.swing.JLabel();
        MainPageBasePanel = new javax.swing.JPanel();
        WelcomeJP = new javax.swing.JLabel();
        TxtJP = new javax.swing.JLabel();
        UsernameJL = new javax.swing.JLabel();
        unJL = new javax.swing.JLabel();
        UsernameTXT = new javax.swing.JTextField();
        passJL = new javax.swing.JLabel();
        PasswordJL = new javax.swing.JLabel();
        PasswordTXT = new javax.swing.JPasswordField();
        PassowrdIconJP = new javax.swing.JPanel();
        HideIconJL = new javax.swing.JLabel();
        ShowIconJL = new javax.swing.JLabel();
        CheckBox = new javax.swing.JCheckBox();
        LoginJP = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));
        setResizable(false);

        SidebarBasePanel.setBackground(new java.awt.Color(204, 255, 255));
        SidebarBasePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SideBar.setBackground(new java.awt.Color(0, 51, 255));
        SideBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        SideBar.setPreferredSize(new java.awt.Dimension(400, 600));
        SideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoPanel.setBackground(new java.awt.Color(0, 0, 255));

        LogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N

        javax.swing.GroupLayout LogoPanelLayout = new javax.swing.GroupLayout(LogoPanel);
        LogoPanel.setLayout(LogoPanelLayout);
        LogoPanelLayout.setHorizontalGroup(
            LogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogoPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(LogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        LogoPanelLayout.setVerticalGroup(
            LogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        SideBar.add(LogoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 29, -1, -1));

        CreateAccountJP.setBackground(new java.awt.Color(0, 51, 204));
        CreateAccountJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CreateAccountJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateAccountJPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CreateAccountJPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CreateAccountJPMouseExited(evt);
            }
        });

        CA_JL.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        CA_JL.setForeground(new java.awt.Color(255, 255, 255));
        CA_JL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CreateAccount.png"))); // NOI18N
        CA_JL.setText("  Create Account");

        javax.swing.GroupLayout CreateAccountJPLayout = new javax.swing.GroupLayout(CreateAccountJP);
        CreateAccountJP.setLayout(CreateAccountJPLayout);
        CreateAccountJPLayout.setHorizontalGroup(
            CreateAccountJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateAccountJPLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(CA_JL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CreateAccountJPLayout.setVerticalGroup(
            CreateAccountJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreateAccountJPLayout.createSequentialGroup()
                .addComponent(CA_JL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        SideBar.add(CreateAccountJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 310, 390, -1));

        ForgetPasswordJP.setBackground(new java.awt.Color(0, 51, 204));
        ForgetPasswordJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ForgetPasswordJP.setPreferredSize(new java.awt.Dimension(302, 52));
        ForgetPasswordJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForgetPasswordJPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ForgetPasswordJPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ForgetPasswordJPMouseExited(evt);
            }
        });

        FP_JL.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        FP_JL.setForeground(new java.awt.Color(255, 255, 255));
        FP_JL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ForgetPassword1.png"))); // NOI18N
        FP_JL.setText(" Forget Password");
        FP_JL.setPreferredSize(new java.awt.Dimension(54, 40));

        javax.swing.GroupLayout ForgetPasswordJPLayout = new javax.swing.GroupLayout(ForgetPasswordJP);
        ForgetPasswordJP.setLayout(ForgetPasswordJPLayout);
        ForgetPasswordJPLayout.setHorizontalGroup(
            ForgetPasswordJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForgetPasswordJPLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(FP_JL, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        ForgetPasswordJPLayout.setVerticalGroup(
            ForgetPasswordJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForgetPasswordJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FP_JL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        SideBar.add(ForgetPasswordJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 392, 390, -1));

        ExitJP.setBackground(new java.awt.Color(0, 51, 204));
        ExitJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitJPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitJPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitJPMouseExited(evt);
            }
        });

        E_JL.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        E_JL.setForeground(new java.awt.Color(255, 255, 255));
        E_JL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit.png"))); // NOI18N
        E_JL.setText("  Exit");

        javax.swing.GroupLayout ExitJPLayout = new javax.swing.GroupLayout(ExitJP);
        ExitJP.setLayout(ExitJPLayout);
        ExitJPLayout.setHorizontalGroup(
            ExitJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitJPLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(E_JL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ExitJPLayout.setVerticalGroup(
            ExitJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(E_JL, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        SideBar.add(ExitJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 473, 390, -1));

        SidebarBasePanel.add(SideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        MainPageBasePanel.setBackground(new java.awt.Color(204, 255, 255));
        MainPageBasePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WelcomeJP.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        WelcomeJP.setText("Welcome");
        MainPageBasePanel.add(WelcomeJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 85, -1, -1));

        TxtJP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtJP.setText(" Hello! Let's get start");
        MainPageBasePanel.add(TxtJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 155, -1, -1));

        UsernameJL.setBackground(new java.awt.Color(255, 255, 255));
        UsernameJL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        UsernameJL.setText("Username :");
        MainPageBasePanel.add(UsernameJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 238, -1, -1));

        unJL.setBackground(new java.awt.Color(204, 255, 255));
        unJL.setText("_____________________________________________________________________________________________________________________");
        MainPageBasePanel.add(unJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 430, -1));

        UsernameTXT.setBackground(new java.awt.Color(204, 255, 255));
        UsernameTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UsernameTXT.setToolTipText("");
        UsernameTXT.setBorder(null);
        MainPageBasePanel.add(UsernameTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 430, 40));

        passJL.setText("____________________________________________________________________________________________________");
        passJL.setToolTipText("");
        MainPageBasePanel.add(passJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 396, 430, 20));

        PasswordJL.setBackground(new java.awt.Color(255, 255, 255));
        PasswordJL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        PasswordJL.setText("Password :");
        MainPageBasePanel.add(PasswordJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 332, 140, 40));

        PasswordTXT.setBackground(new java.awt.Color(204, 255, 255));
        PasswordTXT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PasswordTXT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PasswordTXT.setToolTipText("Please enter password");
        PasswordTXT.setBorder(null);
        PasswordTXT.setPreferredSize(new java.awt.Dimension(64, 21));
        PasswordTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTXTActionPerformed(evt);
            }
        });
        MainPageBasePanel.add(PasswordTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 430, 40));

        PassowrdIconJP.setBackground(new java.awt.Color(204, 255, 255));
        PassowrdIconJP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HideIconJL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hideIcon.png"))); // NOI18N
        HideIconJL.setText("jLabel2");
        HideIconJL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HideIconJLMouseClicked(evt);
            }
        });
        PassowrdIconJP.add(HideIconJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        ShowIconJL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/showicon.png"))); // NOI18N
        ShowIconJL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowIconJLMouseClicked(evt);
            }
        });
        PassowrdIconJP.add(ShowIconJL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        MainPageBasePanel.add(PassowrdIconJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 40, 30));

        CheckBox.setBackground(new java.awt.Color(204, 255, 255));
        CheckBox.setText("Remember Me");
        MainPageBasePanel.add(CheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 418, -1, 30));

        LoginJP.setBackground(new java.awt.Color(255, 255, 255));
        LoginJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginJPMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jButton1.setText("Login");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginJPLayout = new javax.swing.GroupLayout(LoginJP);
        LoginJP.setLayout(LoginJPLayout);
        LoginJPLayout.setHorizontalGroup(
            LoginJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginJPLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LoginJPLayout.setVerticalGroup(
            LoginJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        MainPageBasePanel.add(LoginJP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, -1, -1));

        SidebarBasePanel.add(MainPageBasePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 600, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidebarBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidebarBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
        
    }// </editor-fold>    
    
    private void CreateAccountJPMouseEntered(java.awt.event.MouseEvent evt) {                                             
        
        JPanel enter = CreateAccountJP;
        isEnter(enter);
        
    }                                            

    private void CreateAccountJPMouseExited(java.awt.event.MouseEvent evt) {                                            
        
        JPanel exit = CreateAccountJP;
        isExit(exit);
        
    }                                           

    private void CreateAccountJPMouseClicked(java.awt.event.MouseEvent evt) {                                             
        
        
        
        CreateAccount ca = new CreateAccount();
        setVisible(false);
        
        ca.setVisible(true);
      //dispose();
        
    }                                            

    private void ForgetPasswordJPMouseClicked(java.awt.event.MouseEvent evt) {
    String username = JOptionPane.showInputDialog(this, "Enter your username:");

    if (username == null || username.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username is required to reset the password.");
        return;
    }

    try {
        // 1. Connect to DB
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

        // 2. Check if username exists
        String checkQuery = "SELECT * FROM user_details WHERE UserName = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // 3. Ask for new password
            String newPassword = JOptionPane.showInputDialog(this, "Enter your new password:");

            if (newPassword == null || newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password cannot be empty.");
                return;
            }

            // 4. Update password in DB
            String updateQuery = "UPDATE user_details SET Password = ? WHERE UserName = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, username);
            int updated = updateStmt.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Password reset successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Password reset failed.");
            }

            updateStmt.close();
        } else {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

        rs.close();
        checkStmt.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while resetting password.");
    }
}
                                            

    private void ForgetPasswordJPMouseEntered(java.awt.event.MouseEvent evt) {                                              
        
        JPanel enter = ForgetPasswordJP;
        isEnter(enter);
        
    }                                             

    private void ForgetPasswordJPMouseExited(java.awt.event.MouseEvent evt) {                                             
        
        JPanel exit = ForgetPasswordJP;
        isExit(exit);
        
    }                                            

    private void ExitJPMouseClicked(java.awt.event.MouseEvent evt) {                                    
         
        System.exit(0);
        
        /* 
        setVisible(false);
        LoginOption lo = new LoginOption();
        lo.setVisible(rootPaneCheckingEnabled); 
        */
        
    }                                   

    private void ExitJPMouseEntered(java.awt.event.MouseEvent evt) {                                    
        
        JPanel enter = ExitJP;
        isEnter(enter);
        
    }                                   

    private void ExitJPMouseExited(java.awt.event.MouseEvent evt) {                                   
        
        JPanel exit = ExitJP;
        isExit(exit);
        
    }                                  

    private void ShowIconJLMouseClicked(java.awt.event.MouseEvent evt) {                                        
        
        PasswordTXT.setEchoChar((char)8226);
        
        JLabel si = ShowIconJL;
            si.setVisible(false);
            
        JLabel hi = HideIconJL;
            hi.setVisible(true);
        
    }                                       
 
    
    private void PasswordTXTActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void LoginJPMouseClicked(java.awt.event.MouseEvent evt) {                                     
         
        
        
    }                                    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {   
        
       
             var name = UsernameTXT.getText();
             var pass = new String(PasswordTXT.getPassword());
             
             

             if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.");
                return;
}
        


        //var pass = new String(PasswordTXT.getPassword());

        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");

            // Use PreparedStatement to prevent SQL injection
            String query = "SELECT * FROM user_details WHERE `UserName` = ? AND `Password` = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);  // Set username
            pstmt.setString(2, pass);  // Set password

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                
                System.out.println("Successful login");
                SessionManager.setUsername(name);
                
                saveCredentials();
                System.out.println("Saved to: " + new File("login.properties").getAbsolutePath());



                // Close login window and open the main page
                setVisible(false);
                MainPage mp = new MainPage();
                mp.setVisible(true);
            } else {
                System.out.println("Login failed");
                JOptionPane.showMessageDialog(null, "Username and password do not match");
            }

            // Close resources
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Prints the full stack trace for easier debugging
            JOptionPane.showMessageDialog(null, "An error occurred during login. Please try again.");
        }
        
        
      
    }                                        

    private void HideIconJLMouseClicked(java.awt.event.MouseEvent evt) {                                        
                                              

        PasswordTXT.setEchoChar((char)0);

        JLabel hi = HideIconJL;
        hi.setVisible(false);

        JLabel si = ShowIconJL;
        si.setVisible(true);
        
    }

    public void clearLoginFields() {
            UsernameTXT.setText("");
            PasswordTXT.setText("");
            CheckBox.setSelected(false);
        }
    
    


    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage sp=new LoginPage();
                sp.setVisible(true);
                //sp.loadSavedCredentials();
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel CA_JL;
    private javax.swing.JCheckBox CheckBox;
    private javax.swing.JPanel CreateAccountJP;
    private javax.swing.JLabel E_JL;
    private javax.swing.JPanel ExitJP;
    private javax.swing.JLabel FP_JL;
    private javax.swing.JPanel ForgetPasswordJP;
    private javax.swing.JLabel HideIconJL;
    private javax.swing.JPanel LoginJP;
    private javax.swing.JLabel LogoLabel;
    private javax.swing.JPanel LogoPanel;
    private javax.swing.JPanel MainPageBasePanel;
    private javax.swing.JPanel PassowrdIconJP;
    private javax.swing.JLabel PasswordJL;
    private javax.swing.JPasswordField PasswordTXT;
    private javax.swing.JLabel ShowIconJL;
    private javax.swing.JPanel SideBar;
    private javax.swing.JPanel SidebarBasePanel;
    private javax.swing.JLabel TxtJP;
    private javax.swing.JLabel UsernameJL;
    private javax.swing.JTextField UsernameTXT;
    private javax.swing.JLabel WelcomeJP;
    javax.swing.JButton jButton1;
    private javax.swing.JLabel passJL;
    private javax.swing.JLabel unJL;
    // End of variables declaration                   

    private void isVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
