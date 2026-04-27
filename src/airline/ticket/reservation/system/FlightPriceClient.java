/*package airline.ticket.reservation.system;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class FlightPriceClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:5000/predict");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        // ** Make sure these exactly match your Flask API’s expected fields **
        json.put("Airline", "Biman Bangladesh Airlines");
        json.put("Source Name", "Hazrat Shahjalal International Airport, Dhaka");
        json.put("Destination Name", "Cox's Bazar Airport");
        json.put("Departure Date & Time", "2025-04-23 08:00:00");
        json.put("Arrival Date & Time",   "2025-04-23 09:30:00");
        json.put("Duration (hrs)", 1.5);
        json.put("Stopovers", 0);  // 0 for non‑stop, 1 for one stop, etc.
        json.put("Class", "Economy");
        json.put("Seasonality", "Regular");
        json.put("Days Before Departure", 1000);

        // send
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes("utf-8"));
        }

        int code = conn.getResponseCode();
        InputStream is = (code < 400) 
            ? conn.getInputStream() 
            : conn.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) {
            StringBuilder resp = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) resp.append(line);
            System.out.printf("HTTP %d → %s\n", code, resp.toString());
        }
    }
}*/
