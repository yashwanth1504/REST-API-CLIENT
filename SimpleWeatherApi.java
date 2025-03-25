import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class SimpleWeatherAPI {
    private static final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        try {
            // Build the request URI
            URI uri = new URI(API_URL + "?q=London&appid=" + API_KEY + "&units=metric");
            URL url = uri.toURL(); // Convert URI to URL

            // Connect to the API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check for successful response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Display the raw response
                System.out.println("Weather Data: " + response.toString());
            } else {
                System.out.println("Failed to retrieve data. HTTP Status: " + responseCode);
            }

            connection.disconnect(); // Close the connection
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
