package airline.ticket.reservation.system;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

import java.io.FileInputStream;
import java.util.Map;
import java.util.UUID;

public class DialogflowClient {
    private final SessionsClient sessionsClient;
    private final SessionName session;

    // Constructor to initialize Dialogflow client
    public DialogflowClient(String projectId, String jsonKeyPath) throws Exception {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonKeyPath));
        SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        this.sessionsClient = SessionsClient.create(sessionsSettings);
        this.session = SessionName.of(projectId, UUID.randomUUID().toString());
    }

    // Method to detect intent from user message
    public DetectIntentResponse detectIntent(String userMessage) throws Exception {
        TextInput textInput = TextInput.newBuilder()
                .setText(userMessage)
                .setLanguageCode("en")
                .build();

        QueryInput queryInput = QueryInput.newBuilder()
                .setText(textInput)
                .build();

        return sessionsClient.detectIntent(session, queryInput);
    }

    // Static method to extract signup data and update signupPage
    public static String extractSignupData(DetectIntentResponse response, LoginPage signupPage) {
        QueryResult result = response.getQueryResult();
        String intentDisplayName = result.hasIntent() ? result.getIntent().getDisplayName() : "unknown";

        // Check if the intent is related to filling signup form
        if ("fill_signup_form".equals(intentDisplayName)) {
            Struct params = result.getParameters();
            Map<String, Value> fields = params.getFieldsMap();

            String username = fields.getOrDefault("username", Value.newBuilder().setStringValue("").build()).getStringValue();
            String password = fields.getOrDefault("password", Value.newBuilder().setStringValue("").build()).getStringValue();

            // If username is not empty, set it
            if (!username.isEmpty()) {
                signupPage.setUsername(username);
            }

            // If password is not empty, set it
            if (!password.isEmpty()) {
                signupPage.setPassword(password);
            }

            // Check if both fields are filled
            if (!username.isEmpty() && !password.isEmpty()) {
                return "✅ Your username and password have been filled!";
            }

            // If neither field is filled, ask for both
            if (username.isEmpty() && password.isEmpty()) {
                return "📝 Please provide your username and password.";
            } 
            // If only the username is empty, ask for it
            else if (username.isEmpty()) {
                return "🔑 What's your username?";
            } 
            // If only the password is empty, ask for it
            else {
                return "🔐 What's your password?";
            }
        }

        // Default response if the intent is not 'fill_signup_form'
        return result.getFulfillmentText();
    }
}
