package airline.ticket.reservation.system;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LoginOption extends javax.swing.JFrame {

    
    public LoginOption() {
        initComponents();
        setLocationRelativeTo(null);
    }

   
    @SuppressWarnings("unchecked")
             
     void enterJLabel(JLabel btn){
         
         btn.setSize(300,80);
         
     }        
    
     void isEnter(JPanel btn){
        btn.setBackground(new Color(255,255,255));
        btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102,102,102), 10, false));
    }
    
    void isExit(JPanel btn){
        btn.setBackground(new Color(255,255,255));
        btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,51, 51), 10, false));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BasePanel = new javax.swing.JPanel();
        UserJP = new javax.swing.JPanel();
        UserJL = new javax.swing.JLabel();
        AdminJP = new javax.swing.JPanel();
        AdminJL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(900, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BasePanel.setBackground(new java.awt.Color(0, 51, 51));

        UserJP.setBackground(new java.awt.Color(255, 255, 255));
        UserJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 10));
        UserJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UserJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserJPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserJPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserJPMouseExited(evt);
            }
        });

        UserJL.setBackground(new java.awt.Color(255, 255, 255));
        UserJL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        UserJL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        UserJL.setText("  User Login");

        javax.swing.GroupLayout UserJPLayout = new javax.swing.GroupLayout(UserJP);
        UserJP.setLayout(UserJPLayout);
        UserJPLayout.setHorizontalGroup(
            UserJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserJL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        UserJPLayout.setVerticalGroup(
            UserJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserJL, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        AdminJP.setBackground(new java.awt.Color(255, 255, 255));
        AdminJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 10));
        AdminJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AdminJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminJPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AdminJPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AdminJPMouseExited(evt);
            }
        });

        AdminJL.setBackground(new java.awt.Color(255, 255, 255));
        AdminJL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AdminJL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N
        AdminJL.setText("  Admin Login");

        javax.swing.GroupLayout AdminJPLayout = new javax.swing.GroupLayout(AdminJP);
        AdminJP.setLayout(AdminJPLayout);
        AdminJPLayout.setHorizontalGroup(
            AdminJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AdminJL, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        AdminJPLayout.setVerticalGroup(
            AdminJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminJL, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BasePanelLayout = new javax.swing.GroupLayout(BasePanel);
        BasePanel.setLayout(BasePanelLayout);
        BasePanelLayout.setHorizontalGroup(
            BasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BasePanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(BasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UserJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AdminJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        BasePanelLayout.setVerticalGroup(
            BasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BasePanelLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(UserJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AdminJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        getContentPane().add(BasePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserJPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserJPMouseEntered
       
        JPanel enter = UserJP;
        isEnter(enter);
        
    }//GEN-LAST:event_UserJPMouseEntered

    private void UserJPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserJPMouseExited
        
        JPanel exit = UserJP;
        isExit(exit);
        
    }//GEN-LAST:event_UserJPMouseExited

    private void UserJPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserJPMouseClicked
        
        LoginPage sp = new LoginPage();
        setVisible(false);
        sp.setVisible(true);
        
    }//GEN-LAST:event_UserJPMouseClicked

    private void AdminJPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminJPMouseEntered
        
        JPanel enter = AdminJP;
        isEnter(enter);
        
    }//GEN-LAST:event_AdminJPMouseEntered

    private void AdminJPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminJPMouseExited
        
        JPanel exit = AdminJP;
        isExit(exit);
        
    }//GEN-LAST:event_AdminJPMouseExited

    private void AdminJPMouseClicked(java.awt.event.MouseEvent evt) {
    AdminLoginDialog loginDialog = new AdminLoginDialog(this);
    loginDialog.setVisible(true);
    if (loginDialog.isSucceeded()) {
        // Open AdminPage if login successful
        AdminPage ap = new AdminPage();
        setVisible(false);
        ap.setVisible(true);
    }
}

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginOption().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdminJL;
    private javax.swing.JPanel AdminJP;
    private javax.swing.JPanel BasePanel;
    private javax.swing.JLabel UserJL;
    private javax.swing.JPanel UserJP;
    // End of variables declaration//GEN-END:variables
}
