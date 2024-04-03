package Model.Assets;

import Model.Platforms.CryptoExchange;

public class CryptoPOW extends Crypto{

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
                     String blockchain, int tokensIssued, int transactionSeconds, CryptoExchange exchange,
                     int cutRate, int cutAmount, double coinsPerBlock) {

        super(name, symbol, issuer, industry, price, marketCapitalization, fixedTokens, smartContracts,
              blockchain, tokensIssued, transactionSeconds, exchange);
        this.cutRate = cutRate;
        this.cutAmount = cutAmount;
        this.coinsPerBlock = coinsPerBlock;
    }
}
