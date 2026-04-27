package airline.ticket.reservation.system;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

public class MicrophoneRecorder {
    public static byte[] record(int seconds) throws Exception {
        AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            throw new LineUnavailableException("Microphone not supported");
        }

        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        long end = System.currentTimeMillis() + seconds * 1000;

        while (System.currentTimeMillis() < end) {
            int count = microphone.read(buffer, 0, buffer.length);
            if (count > 0) {
                out.write(buffer, 0, count);
            }
        }

        microphone.stop();
        microphone.close();
        return out.toByteArray();
    }
}
