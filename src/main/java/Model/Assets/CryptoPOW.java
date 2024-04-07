package Model.Assets;

import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.CryptoExchange;

import java.util.Scanner;

public class CryptoPOW extends Crypto implements ReadUpdateInterface {

    private int cutRate;
    private int cutAmount;
    private double coinsPerBlock;

    //#################### SETTERS AND GETTERS ############################################
    public int getCutRate() {
        return cutRate;
    }

    public void setCutRate(int cutRate) {
        this.cutRate = cutRate;
    }

    public int getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(int cutAmount) {
        this.cutAmount = cutAmount;
    }

    public double getCoinsPerBlock() {
        return coinsPerBlock;
    }

    public void setCoinsPerBlock(double coinsPerBlock) {
        this.coinsPerBlock = coinsPerBlock;
    }


    //#################### CONSTRUCTORS ############################################


    public CryptoPOW() {
        super();
        this.cutRate = 0;
        this.cutAmount = 0;
        this.coinsPerBlock = 0;
    }

    public CryptoPOW(String name, String symbol, String issuer, String industry, double price,
                     double marketCapitalization, Boolean fixedTokens, Boolean smartContracts,
                     String blockchain, int tokensIssued, int transactionSeconds,
                     int cutRate, int cutAmount, double coinsPerBlock) {

        super(name, symbol, issuer, industry, price, marketCapitalization, fixedTokens, smartContracts,
              blockchain, tokensIssued, transactionSeconds);
        this.cutRate = cutRate;
        this.cutAmount = cutAmount;
        this.coinsPerBlock = coinsPerBlock;
    }

    @Override
    public void read() {
        super.read(); // Call the read() method of the parent class (Crypto)
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter cut rate: ");
        setCutRate(sc.nextInt());

        System.out.print("Enter cut amount: ");
        setCutAmount(sc.nextInt());

        System.out.print("Enter coins per block: ");
        setCoinsPerBlock(sc.nextDouble());
    }

    @Override
    public void update() {
        super.update(); // Call the update() method of the parent class (Crypto)
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update cut rate? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new cut rate: ");
            setCutRate(sc.nextInt());
        }

        System.out.print("Do you want to update cut amount? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new cut amount: ");
            setCutAmount(sc.nextInt());
        }

        System.out.print("Do you want to update coins per block? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new coins per block: ");
            setCoinsPerBlock(sc.nextDouble());
        }
    }

}
