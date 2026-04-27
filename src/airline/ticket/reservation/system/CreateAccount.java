package airline.ticket.reservation.system;

//import Backup.LoginPage;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateAccount extends javax.swing.JFrame {

   
    public CreateAccount() {
        initComponents();
        
        
        // Create a Calendar instance to specify the minimum date
        Calendar cal0 = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();

        // Set the minimum date to 1st January 2020
        cal0.set(Calendar.YEAR, 1900);
        cal0.set(Calendar.MONTH, Calendar.JANUARY);
        cal0.set(Calendar.DAY_OF_MONTH, 1);
        Date minDate = cal0.getTime();
        
        // Subtract 10 years from the current date
        cal.add(Calendar.YEAR, -15);
        Date maxDate = cal.getTime(); // This is 10 years ago from today

        // Set the maximum selectable date to 10 years ago
        DOB.setMaxSelectableDate(maxDate);

        // Set the minimum selectable date to 1st Jan 2020
        DOB.setMinSelectableDate(minDate);
        
        
        AutoCompleteDecorator.decorate(CountryCB);  //Auto Complete ComboBox
        
    }

    
    @SuppressWarnings("unchecked")
    String firstName,lastName,genderM,genderF,phn,userName,passport;
        char[] password,confirmPassword;
        Object country;
        Date dob;
    
    public boolean validation(){
        
        
        firstName=FirstNameTXT.getText();
        lastName=LastNameTXT.getText();
        genderM=MaleBtn.getText();
        genderF=FemaleBtn.getActionCommand();
        dob=DOB.getDate();
        country=CountryCB.getSelectedItem();
        phn=PhnTXT.getText();
        userName=UserNameTXT.getText();
        passport=PassportTXT.getText();
        password=PassJField.getPassword();
        confirmPassword=ConfirmPassJField.getPassword();//.toString()
        
        if(firstName.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter First Name");
            return false;
        }
        
        if(lastName.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter Last Name");
            return false;
        }
        
        if(!MaleBtn.isSelected() && !FemaleBtn.isSelected())
        {
            JOptionPane.showMessageDialog(this,"Please select your gender");
            return false;
        }
        
        if(dob==null)
        {
            JOptionPane.showMessageDialog(this,"Please enter Date of Birth");
            return false;
        }
        
        if(phn.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter Contact Number");
            return false;
        }
        
        if(userName.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter User Name");
            return false;
        }
        
        if(passport.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter Passport No");
            return false;
            
        }
        
        if(password.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please enter Password");
            
            
            return false;
        }
        
        if (password.length < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
            return false;
        }

        
        if(!Arrays.equals(confirmPassword, password))
        {
            JOptionPane.showMessageDialog(this,"Password not matched");
            return false;
        }
        if(!agreeCheck.isSelected())
        {
            JOptionPane.showMessageDialog(this,"Please agree with our terms and condition");
            return false;
        }
        
        return true;
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        FirstNameTXT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        LastNameTXT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PassportTXT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CountryCB = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        DOB = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        MaleBtn = new javax.swing.JRadioButton();
        FemaleBtn = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        PhnTXT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        UserNameTXT = new javax.swing.JTextField();
        PassJField = new javax.swing.JPasswordField();
        ConfirmPassJField = new javax.swing.JPasswordField();
        agreeCheck = new javax.swing.JCheckBox();
        NxtBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JButton();
        phnOTP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Welcome to Safetrip_1.gif"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 600));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true), "Create Account", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Microsoft Sans Serif", 0, 36))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel1.setText("First Name");

        FirstNameTXT.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        FirstNameTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstNameTXTActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel2.setText("Last Name");

        LastNameTXT.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel3.setText("Username");

        PassportTXT.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel4.setText("Country");

        CountryCB.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        CountryCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Australia", "Bangladesh", "Canada", "India", "Indonesia", "Italy", "Japan", "Malaysia", "Maldives", "Myanmar", "Nepal", "Netherlands", "Oman", "Pakistan", "Qatar", "Singapore", "Srilanka", "Thailand", "Turkey", "United Arab Emirates(UAE)", "United Kingdom(UK)", "United States(US)" }));
        CountryCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountryCBActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel5.setText("Date of Birth");

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel6.setText("Gender");

        ButtonGroup.add(MaleBtn);
        MaleBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        MaleBtn.setText("Male");
        MaleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleBtnActionPerformed(evt);
            }
        });

        ButtonGroup.add(FemaleBtn);
        FemaleBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        FemaleBtn.setText("Female");

        jLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel7.setText("Passport No");

        PhnTXT.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel9.setText("Confirm Password");

        jLabel10.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel10.setText("Contact No");

        UserNameTXT.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        PassJField.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        ConfirmPassJField.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N

        agreeCheck.setText("I agree all terms of services");

        NxtBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NxtBtn.setText("Next");
        NxtBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NxtBtnMouseClicked(evt);
            }
        });
        NxtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NxtBtnActionPerformed(evt);
            }
        });

        BackBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BackBtn.setText("Back");
        BackBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackBtnMouseClicked(evt);
            }
        });

        phnOTP.setText("i");
        phnOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phnOTPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BackBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NxtBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(UserNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PassJField))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(PassportTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ConfirmPassJField)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(agreeCheck)
                                        .addGap(177, 177, 177))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(MaleBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(FemaleBtn))
                                            .addComponent(FirstNameTXT)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CountryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(PhnTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LastNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phnOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FirstNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(LastNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(MaleBtn)
                        .addComponent(FemaleBtn)
                        .addComponent(jLabel5))
                    .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CountryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(PhnTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phnOTP))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(UserNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(PassportTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(PassJField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmPassJField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(agreeCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NxtBtn)
                    .addComponent(BackBtn))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 600, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseClicked
        LoginPage sp = new LoginPage();
        setVisible(false);

        sp.setVisible(true);
        //dispose();
        
    }//GEN-LAST:event_BackBtnMouseClicked

    private void CountryCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountryCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CountryCBActionPerformed

    private void NxtBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NxtBtnActionPerformed
            
        
        if (validation()){   
           
      
        //-------------------------------------------------------------Database Connection------------------------------------------------------------------------   
        
        
             try{
                 
            //register driver      
            Class.forName("com.mysql.cj.jdbc.Driver");

            //connect database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/horizon", "root", "");
            
                 PreparedStatement ps;
                          ps = con.prepareStatement("INSERT INTO user_details (`UserName`, `FirstName`, `LastName`, `Gender`, `DateOfBirth`, `Country`, `ContactNo`, `PassportNo`, `Password`)"
                                                  + " VALUES (?,?,?,?,?,?,?,?,?)");

                        ps.setString(1, userName);
                        ps.setString(2, firstName);
                        ps.setString(3, lastName);

                        if (MaleBtn.isSelected()) {
                             ps.setString(4, MaleBtn.getText());
                        } else {
                             ps.setString(4, FemaleBtn.getText());
                        }

                        ps.setDate(5, new java.sql.Date(dob.getTime())); // Cast dob to java.sql.Date
                        ps.setString(6, CountryCB.getSelectedItem().toString()); // Get selected country as string
                        ps.setString(7, phn);
                        ps.setString(8, passport);
                        ps.setString(9, String.copyValueOf(password));
                        
                         // 🔐 Username Check
                        if (isUsernameTaken(con, userName)) {
                            JOptionPane.showMessageDialog(null, "Username already exists. Please choose another.");
                            return;
                        }

                        // 📱 Phone Format Check
                        if (!isPhoneValid(phn)) {
                            JOptionPane.showMessageDialog(null, "Phone number must be exactly 11 digits.");
                            return;
                        }

                        // 🛂 Passport Format Check
                        if (!isPassportValid(passport)) {
                            JOptionPane.showMessageDialog(null, "Invalid passport number format. It must be 6–9 alphanumeric characters.");
                            return;
                        }

                 ps.executeUpdate();

                 
                JOptionPane.showMessageDialog(this,"Successfully Create Account");
                
                LoginPage sp = new LoginPage();
                setVisible(false);
                sp.setVisible(true);
            
             } catch (HeadlessException | ClassNotFoundException | SQLException e){
            System.out.println(e);//JOptionPane.showMessageDialog(null,"name : "+name+" password : "+pass);
            JOptionPane.showMessageDialog(this,"Can not create account");
          
            } catch (Exception ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }       
             
    }//GEN-LAST:event_NxtBtnActionPerformed

    private void MaleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaleBtnActionPerformed

    private void NxtBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NxtBtnMouseClicked
       
        
        setVisible(false);
        LoginPage sp = new LoginPage();
        sp.setVisible(true);
    }//GEN-LAST:event_NxtBtnMouseClicked

        

          private boolean isUsernameTaken(Connection con, String username) throws Exception {
              
                String query = "SELECT * FROM user_details WHERE `UserName` = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }

            private boolean isPhoneValid(String phone) {
                return phone.matches("\\d{11}");  // accepts only 11-digit numbers
                
                  // 'this' should be the parent JFrame (e.g., MainForm.this)

            }

            private boolean isPassportValid(String passport) {
                return passport.matches("[a-zA-Z0-9]{6,9}");  // alphanumeric, 6–9 characters
            }
            
            

    
    private void FirstNameTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstNameTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstNameTXTActionPerformed

    private void phnOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phnOTPActionPerformed
        // TODO add your handling code here:
        
                
    }//GEN-LAST:event_phnOTPActionPerformed

    
    
    public static void main(String args[]) {
        
        CreateAccount ca = new CreateAccount();
        
        java.awt.EventQueue.invokeLater(() -> {
            new CreateAccount().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBtn;
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JPasswordField ConfirmPassJField;
    private javax.swing.JComboBox<String> CountryCB;
    private com.toedter.calendar.JDateChooser DOB;
    private javax.swing.JRadioButton FemaleBtn;
    private javax.swing.JTextField FirstNameTXT;
    private javax.swing.JTextField LastNameTXT;
    private javax.swing.JRadioButton MaleBtn;
    private javax.swing.JButton NxtBtn;
    private javax.swing.JPasswordField PassJField;
    private javax.swing.JTextField PassportTXT;
    private javax.swing.JTextField PhnTXT;
    private javax.swing.JTextField UserNameTXT;
    private javax.swing.JCheckBox agreeCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton phnOTP;
    // End of variables declaration//GEN-END:variables

   
}
