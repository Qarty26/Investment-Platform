package Assets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Asset {

    protected String name;
    protected String symbol;
    protected String issuer;
    protected String industry;
    protected double price;
    protected double marketCapitalization;


    //#################### SETTERS AND GETTERS ############################################
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(double marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    //#################### CONSTRUCTORS ############################################


    public Asset() {
        this.name = "-";
        this.symbol = "-";
        this.issuer = "-";
        this.industry = "-";
        this.price = 0;
        this.marketCapitalization = 0;
    }

    public Asset(String symbol) {
        this.name = "-";
        this.symbol = symbol;
        this.issuer = "-";
        this.industry = "-";
        this.price = 0;
        this.marketCapitalization = 0;
    }

    public Asset(String name, String symbol, String issuer, String industry, double price,
                 double marketCapitalization) {

        this.name = name;
        this.symbol = symbol;
        this.issuer = issuer;
        this.industry = industry;
        this.price = price;
        this.marketCapitalization = marketCapitalization;
    }


    //unfinished
    public Double getPrice(String symbol) throws IOException {

        String endpoint = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol;

        URL url = new URL(endpoint);

        // Create HttpURLConnection object
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Check if the response code is OK (200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Create BufferedReader to read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            // Read response line by line
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close BufferedReader
            reader.close();

            // Extract the price from the response string
            String responseBody = response.toString();
            String priceString = responseBody.split("\"price\":\"")[1].split("\"")[0];
            double price = Double.parseDouble(priceString);

            // Print the price
            System.out.println("Price: " + price);
            connection.disconnect();
            return price;
        } else {

            connection.disconnect();
            return null;
        }
    }
}
