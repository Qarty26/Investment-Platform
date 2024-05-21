package Model.Assets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import Model.Helpers.ReadUpdateInterface;
import org.json.JSONObject;

public class Asset implements ReadUpdateInterface {

    protected int idAsset;

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
    public double getPricee() {
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

    public int getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(int idAsset) {
        this.idAsset = idAsset;
    }


//#################### CONSTRUCTORS ############################################


    public Asset() {
        this.idAsset = 0;
        this.name = "-";
        this.symbol = "-";
        this.issuer = "-";
        this.industry = "-";
        this.price = 0;
        this.marketCapitalization = 0;
    }

    public Asset(String symbol) {
        this.idAsset = 0;
        this.name = "-";
        this.symbol = symbol;
        this.issuer = "-";
        this.industry = "-";
        this.price = 0;
        this.marketCapitalization = 0;
    }

    public Asset(int idAsset,String name, String symbol, String issuer, String industry, double price,
                 double marketCapitalization) {
        this.idAsset = idAsset;
        this.name = name;
        this.symbol = symbol;
        this.issuer = issuer;
        this.industry = industry;
        this.price = price;
        this.marketCapitalization = marketCapitalization;
    }


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

    @Override
    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter asset id: ");
        setIdAsset(sc.nextInt());

        sc.nextLine();
        System.out.print("Enter asset name: ");
        setName(sc.nextLine());

        System.out.print("Enter asset symbol: ");
        setSymbol(sc.nextLine());

        System.out.print("Enter issuer: ");
        setIssuer(sc.nextLine());

        System.out.print("Enter industry: ");
        setIndustry(sc.nextLine());

        System.out.print("Enter price: ");
        setPrice(sc.nextDouble());

        System.out.print("Enter market capitalization: ");
        setMarketCapitalization(sc.nextDouble());
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update id? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new id: ");
            setIdAsset(sc.nextInt());
        }

        System.out.print("Do you want to update name? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new name: ");
            setName(sc.nextLine());
        }

        System.out.print("Do you want to update symbol? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new symbol: ");
            setSymbol(sc.nextLine());
        }

        System.out.print("Do you want to update issuer? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new issuer: ");
            setIssuer(sc.nextLine());
        }

        System.out.print("Do you want to update industry? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new industry: ");
            setIndustry(sc.nextLine());
        }

        System.out.print("Do you want to update price? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new price: ");
            setPrice(sc.nextDouble());
        }

        System.out.print("Do you want to update market capitalization? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new market capitalization: ");
            setMarketCapitalization(sc.nextDouble());
        }
    }

    @Override
    public String toString() {
        return "Asset{" +
                "idAsset=" + idAsset +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", issuer='" + issuer + '\'' +
                ", industry='" + industry + '\'' +
                ", price=" + price +
                ", marketCapitalization=" + marketCapitalization +
                '}';
    }
}