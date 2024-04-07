package Model.Assets;

import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.CryptoExchange;

import java.util.Scanner;

public class Crypto extends Asset implements ReadUpdateInterface {

    protected Boolean fixedTokens;
    protected Boolean smartContracts;
    protected String blockchain;
    protected int tokensIssued;
    protected int transactionSeconds;


    //#################### SETTERS AND GETTERS ############################################
    public Boolean getFixedTokens() {
        return fixedTokens;
    }

    public void setFixedTokens(Boolean fixedTokens) {
        this.fixedTokens = fixedTokens;
    }

    public Boolean getSmartContracts() {
        return smartContracts;
    }

    public void setSmartContracts(Boolean smartContracts) {
        this.smartContracts = smartContracts;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public int getTokensIssued() {
        return tokensIssued;
    }

    public void setTokensIssued(int tokensIssued) {
        this.tokensIssued = tokensIssued;
    }

    public int getTransactionSeconds() {
        return transactionSeconds;
    }

    public void setTransactionSeconds(int transactionSeconds) {
        this.transactionSeconds = transactionSeconds;
    }


    //#################### CONSTRUCTORS ############################################
    public Crypto() {
        super();
        this.fixedTokens = false;
        this.smartContracts = false;
        this.blockchain = "-";
        this.tokensIssued = 0;
        this.transactionSeconds = 0;
    }

    public Crypto(String name, String symbol, String issuer, String industry, double price,
                  double marketCapitalization, Boolean fixedTokens, Boolean smartContracts,
                  String blockchain, int tokensIssued, int transactionSeconds) {

        super(name, symbol, issuer, industry, price, marketCapitalization);
        this.fixedTokens = fixedTokens;
        this.smartContracts = smartContracts;
        this.blockchain = blockchain;
        this.tokensIssued = tokensIssued;
        this.transactionSeconds = transactionSeconds;
    }



    @Override
    public void read() {
        super.read(); // Call the read() method of the parent class (Asset)
        Scanner sc = new Scanner(System.in);

        System.out.print("Does it have fixed tokens? (true/false): ");
        setFixedTokens(sc.nextBoolean());

        System.out.print("Does it support smart contracts? (true/false): ");
        setSmartContracts(sc.nextBoolean());

        System.out.print("Enter blockchain: ");
        setBlockchain(sc.nextLine());

        System.out.print("Enter tokens issued: ");
        setTokensIssued(sc.nextInt());

        System.out.print("Enter transaction seconds: ");
        setTransactionSeconds(sc.nextInt());
    }

    @Override
    public void update() {
        super.update(); // Call the update() method of the parent class (Asset)
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update fixed tokens? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Does it have fixed tokens? (true/false): ");
            setFixedTokens(sc.nextBoolean());
        }

        System.out.print("Do you want to update smart contracts? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Does it support smart contracts? (true/false): ");
            setSmartContracts(sc.nextBoolean());
        }

        System.out.print("Do you want to update blockchain? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new blockchain: ");
            setBlockchain(sc.nextLine());
        }

        System.out.print("Do you want to update tokens issued? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new tokens issued: ");
            setTokensIssued(sc.nextInt());
        }

        System.out.print("Do you want to update transaction seconds? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new transaction seconds: ");
            setTransactionSeconds(sc.nextInt());
        }
    }
}
