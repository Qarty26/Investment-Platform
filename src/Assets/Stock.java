package Assets;

import Platforms.StockExchange;

public class Stock extends Asset{

    private String market;
    private double dividendRate;
    private StockExchange exchange;

    //#################### SETTERS AND GETTERS ############################################
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getDividendRate() {
        return dividendRate;
    }

    public void setDividendRate(double dividendRate) {
        this.dividendRate = dividendRate;
    }

    public StockExchange getExchange() {
        return exchange;
    }

    public void setExchange(StockExchange exchange) {
        this.exchange = exchange;
    }

    //#################### CONSTRUCTORS ############################################
    public Stock() {
        super();
        this.market = "-";
        this.dividendRate=0;
        this.exchange=null;
    }

    public Stock(String market, double dividendRate, StockExchange exchange) {
        this.market = market;
        this.dividendRate = dividendRate;
        this.exchange = exchange;
    }
}
