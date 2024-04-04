package Model.Assets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Asset {
    private int idAsset;

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

    public double getPrice() throws IOException {
        String url = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol.toUpperCase();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            double price = jsonResponse.getDouble("price");
            return price;
        } else {
            System.out.println("Error response code: " + responseCode);
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = errorReader.readLine()) != null) {
                response.append(inputLine);
            }
            errorReader.close();

            System.out.println("Error response: " + response.toString());
            throw new IOException("HTTP error code: " + responseCode);
        }
    }


}