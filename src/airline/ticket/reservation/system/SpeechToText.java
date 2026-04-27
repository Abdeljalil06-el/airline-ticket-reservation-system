/*package airline.ticket.reservation.system;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.vosk.Model;
import org.vosk.Recognizer;


public class SpeechToText {
    private static Recognizer recognizer;

    static {
        try {
            // Initialize Vosk Model with the correct path
            Model model = new Model("E:\\Java\\Airline Ticket Reservation System\\New folder\\Language_Model\\vosk-model-small-en-us-0.15");  // Replace with actual path
            recognizer = new Recognizer(model, 16000.0f);  // 16000 is the sample rate
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Vosk initialization failed", e);
        }
    }

    // Method to record audio and convert it to text
    public static String transcribeFromMic(int seconds) throws Exception {
        // Record audio from the microphone
        byte[] audioData = MicrophoneRecorder.record(seconds);

        // Send the recorded audio data to Vosk for transcription
        return transcribeAudio(audioData);
    }

    // Method to convert audio data to text using Vosk recognizer
    private static String transcribeAudio(byte[] audioData) {
        try {
            InputStream audioStream = new ByteArrayInputStream(audioData);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            
            // Convert the audio input stream to a format that Vosk can understand (e.g., 16-bit PCM)
            // Since Vosk requires raw PCM data, we need to handle the audio input accordingly.
            if (audioInputStream.getFormat().getSampleSizeInBits() != 16) {
                throw new UnsupportedAudioFileException("Only 16-bit PCM audio is supported");
            }

            // Set up recognizer with the audio stream
            recognizer.startRecognition(audioInputStream);
            StringBuilder transcribedText = new StringBuilder();

            // Read and process the audio data
            while (true) {
                String result = recognizer.getResult();
                if (result != null && !result.isEmpty()) {
                    transcribedText.append(result);
                }
                // Check if recognition is done
                if (recognizer.getStatus().equals(Recognizer.RecognizerStatus.DONE)) {
                    break;
                }
            }
            recognizer.stopRecognition();  // Stop the recognition process
            return transcribedText.toString();
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            return "Error in speech recognition";
        }
    }

    // You can use this method to test the functionality
    public static void main(String[] args) {
        try {
            String recognizedText = transcribeFromMic(5);  // Record for 5 seconds
            System.out.println("Recognized Text: " + recognizedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
