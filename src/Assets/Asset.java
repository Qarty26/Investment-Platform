package Assets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.json.JSONObject;

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
    public Double getPrice() {
//        try {
//            String symbol = getName().toUpperCase();
//            String endpoint = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol;
//            URL url = new URL(endpoint);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
////            JSONObject jsonResponse = new JSONObject(response.toString());
////            return Double.parseDouble(jsonResponse.getString("price"));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null; // Handle error gracefully in your application
//        }



        //to be removed, don't ask
        return 0.0;
    }


}