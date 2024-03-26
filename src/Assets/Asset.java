package Assets;

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
}
