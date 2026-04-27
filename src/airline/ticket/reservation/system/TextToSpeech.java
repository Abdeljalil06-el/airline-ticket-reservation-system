/*package airline.ticket.reservation.system;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class TextToSpeech {
    private static Voice voice;

    // Initialize FreeTTS with a voice
    static {
        try {
            VoiceManager voiceManager = VoiceManager.getInstance();
            voice = voiceManager.getVoice("kevin16");  // You can change this to another voice name if needed
            if (voice != null) {
                voice.allocate();  // Allocate the voice for use
            } else {
                throw new RuntimeException("Voice not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize FreeTTS", e);
        }
    }

    // Method to speak the given text
    public static void speak(String text) {
        if (voice != null) {
            voice.speak(text);  // Speak the text
        } else {
            throw new RuntimeException("Voice is not initialized");
        }
    }
}
*/