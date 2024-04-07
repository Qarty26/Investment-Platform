package Model.Assets;

import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.StockExchange;

import java.util.Scanner;

public class Stock extends Asset implements ReadUpdateInterface {

    private String market;
    private double dividendRate;

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


    //#################### CONSTRUCTORS ############################################
    public Stock() {
        super();
        this.market = "-";
        this.dividendRate = 0;
    }

    public Stock(String market, double dividendRate, StockExchange exchange) {
        this.market = market;
        this.dividendRate = dividendRate;
    }


    @Override
    public void read() {
        super.read();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter market: ");
        setMarket(sc.nextLine());

        System.out.print("Enter dividend rate: ");
        setDividendRate(sc.nextDouble());

    }

    @Override
    public void update() {
        super.update();
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update market? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new market: ");
            setMarket(sc.nextLine());
        }

        System.out.print("Do you want to update dividend rate? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new dividend rate: ");
            setDividendRate(sc.nextDouble());
        }
    }
}
