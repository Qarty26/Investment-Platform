import Assets.Asset;
import Platforms.CryptoExchange;
import Platforms.Exchange;
import Platforms.StockExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class Main {
    public Double getPrice(String symbol) throws IOException {
        symbol = symbol.toUpperCase();
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

    public static void main(String[] args) throws IOException {



        Vector<Exchange> exchanges = new Vector<>();

        CryptoExchange a = new CryptoExchange("Binance", false, true, true, true, true);
        CryptoExchange b = new CryptoExchange("Coinbase", true, true, true, false, true);
        CryptoExchange c = new CryptoExchange("KuCoin", false, true, true, true, false);
        StockExchange d = new StockExchange("TradeVille", false, true, 10, 18);
        StockExchange e = new StockExchange("Trading212", true, true, 16, 22);
        StockExchange f = new StockExchange("eToro", true, true, 14, 23);

        exchanges.add(a);
        exchanges.add(b);
        exchanges.add(c);
        exchanges.add(d);
        exchanges.add(e);
        exchanges.add(f);

        String pair = "btc";
        Asset as = new Asset();
        System.out.println(as.getPrice(pair));

    }
}