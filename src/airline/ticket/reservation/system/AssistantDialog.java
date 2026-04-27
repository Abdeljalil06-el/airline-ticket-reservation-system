package airline.ticket.reservation.system;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.json.JSONObject;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AssistantDialog extends JDialog {
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField userInputField;
    private JButton sendButton, micButton, clearButton;

    private Recognizer recognizer;
    private Model model;

    private Voice voice;
    private DialogflowClient dialogflowClient;
    private LoginPage signupPage;

    private JLabel typingLabel;

    public AssistantDialog(LoginPage signupPage) {
        super(signupPage, "Assistant", true);
        this.signupPage = signupPage;

        setSize(500, 500);
        setLocationRelativeTo(signupPage);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initSpeechSystems();
        initUI();
    }

    private void initSpeechSystems() {
        try {
            model = new Model("E:/Java/Airline Ticket Reservation System/New folder/Language_Model/vosk-model-small-en-us-0.15");
            recognizer = new Recognizer(model, 16000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dialogflowClient = new DialogflowClient(
                    "flightassistant-vdqa",
                    "E:/Java/Airline Ticket Reservation System/New folder/flightassistant-vdqa-6b3ec0791464.json"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        // --- TOP PANEL with Clear button ---
        JPanel topPanel = new JPanel(new BorderLayout());
        clearButton = new JButton("🧹");
        JPanel clearPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        clearPanel.add(clearButton);
        clearPanel.setOpaque(false);
        topPanel.add(clearPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        clearButton.addActionListener(e -> {
            chatPanel.removeAll();
            chatPanel.revalidate();
            chatPanel.repaint();
        });

        // --- CHAT PANEL ---
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Typing label inside chat area
        typingLabel = new JLabel("Assistant is typing...");
        typingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        typingLabel.setForeground(Color.GRAY);
        typingLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        typingLabel.setVisible(false);
        chatPanel.add(typingLabel);

        // --- INPUT AREA (Bottom Panel) ---
        JPanel inputPanel = new JPanel(new BorderLayout());
        userInputField = new JTextField();
        userInputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(userInputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        micButton = new JButton("🎤");
        sendButton = new JButton("Send");

        buttonPanel.add(micButton);
        buttonPanel.add(sendButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // Event listeners
        sendButton.addActionListener(e -> sendMessage());
        micButton.addActionListener(e -> listenToMic());
        userInputField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = userInputField.getText().trim();
        if (!message.isEmpty()) {
            addMessageBubble("You", message, new Color(220, 248, 198), FlowLayout.RIGHT);
            userInputField.setText("");
            respondToMessage(message);
        }
    }

    private void respondToMessage(String message) {
        typingLabel.setVisible(true);
        scrollToBottom();

        new Thread(() -> {
            try {
                DetectIntentResponse response = dialogflowClient.detectIntent(message);
                String reply = DialogflowClient.extractSignupData(response, signupPage);

                SwingUtilities.invokeLater(() -> {
                    typingLabel.setVisible(false);
                    addMessageBubble("Assistant", reply, new Color(240, 240, 240), FlowLayout.LEFT);
                    new Thread(() -> speakText(reply)).start();
                });
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    typingLabel.setVisible(false);
                    addMessageBubble("Assistant", "⚠️ Sorry, something went wrong.", Color.PINK, FlowLayout.LEFT);
                });
            }
        }).start();
    }

    private void listenToMic() {
        new Thread(() -> {
            try {
                if (recognizer != null) recognizer.close();
                recognizer = new Recognizer(model, 16000);

                byte[] audioData = MicrophoneRecorder.record(5);
                String recognizedText = recognizeSpeech(audioData);

                if (recognizedText != null && !recognizedText.isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        addMessageBubble("You", recognizedText, new Color(220, 248, 198), FlowLayout.RIGHT);
                        respondToMessage(recognizedText);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String recognizeSpeech(byte[] audioData) {
        try {
            recognizer.acceptWaveForm(audioData, audioData.length);
            String result = recognizer.getResult();
            JSONObject json = new JSONObject(result);
            return json.getString("text");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void speakText(String text) {
        if (voice == null) {
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            VoiceManager vm = VoiceManager.getInstance();
            voice = vm.getVoice("kevin16");

            if (voice == null) {
                System.err.println("kevin16 voice not found");
                return;
            }

            voice.allocate();
        }
        voice.speak(text);
    }

    private void addMessageBubble(String sender, String message, Color bgColor, int alignment) {
        JPanel bubblePanel = new JPanel(new FlowLayout(alignment, 10, 0));
        bubblePanel.setOpaque(false);

        JTextArea bubble = new JTextArea(sender + ": " + message);
        bubble.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bubble.setLineWrap(true);
        bubble.setWrapStyleWord(true);
        bubble.setEditable(false);
        bubble.setBackground(bgColor);
        bubble.setBorder(new EmptyBorder(8, 10, 8, 10));
        bubble.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));

        bubblePanel.add(bubble);
        chatPanel.add(bubblePanel);
        chatPanel.add(Box.createVerticalStrut(5)); // Smaller vertical gap

        chatPanel.revalidate();
        scrollToBottom();
    }

    private void scrollToBottom() {
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage signupPage = new LoginPage();
            signupPage.setVisible(true);
            AssistantDialog dialog = new AssistantDialog(signupPage);
            dialog.setVisible(true);
        });
    }
}
