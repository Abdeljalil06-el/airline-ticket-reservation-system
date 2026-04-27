/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airline.ticket.reservation.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class OTPDialog {

    private String sentOTP;
    private int countdown = 60;
    private Timer timer;

    public void showOTPWindow(JFrame parentFrame, String phone) {
        if (!isPhoneValid(phone)) {
            JOptionPane.showMessageDialog(parentFrame, "Invalid phone number. Must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(parentFrame, "Phone Verification", true);
        dialog.setSize(350, 230);
        dialog.setLayout(new GridLayout(6, 1, 10, 5));
        dialog.setLocationRelativeTo(parentFrame);

        JLabel phoneLabel = new JLabel("Phone: " + phone, SwingConstants.CENTER);
        JButton sendOtpBtn = new JButton("Send OTP");
        JLabel timerLabel = new JLabel("", SwingConstants.CENTER);
        JTextField otpField = new JTextField();
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setEnabled(false);

        dialog.add(phoneLabel);
        dialog.add(sendOtpBtn);
        dialog.add(timerLabel);
        dialog.add(new JLabel("Enter OTP:", SwingConstants.CENTER));
        dialog.add(otpField);
        dialog.add(confirmBtn);

        sendOtpBtn.addActionListener(e -> {
            sentOTP = generateOTP();
            sendOtpToPhone(phone, sentOTP);  // Simulate sending
            startTimer(timerLabel, sendOtpBtn);
            confirmBtn.setEnabled(true);
        });

        confirmBtn.addActionListener(e -> {
            String enteredOTP = otpField.getText().trim();
            if (enteredOTP.equals(sentOTP)) {
                JOptionPane.showMessageDialog(dialog, "Phone verified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Invalid OTP. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void startTimer(JLabel label, JButton sendBtn) {
        sendBtn.setEnabled(false);
        countdown = 60;

        timer = new Timer(1000, e -> {
            countdown--;
            label.setText("You can resend OTP in: " + countdown + "s");
            if (countdown <= 0) {
                timer.stop();
                label.setText("You can now resend OTP.");
                sendBtn.setEnabled(true);
            }
        });
        timer.start();
    }

    private String generateOTP() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("01[3-9]\\d{8}");
    }

    private void sendOtpToPhone(String phone, String otp) {
        // Simulated SMS sending (replace with API like Twilio if needed)
        System.out.println("[OTP SENT] Phone: " + phone + ", OTP: " + otp);
    }
}

