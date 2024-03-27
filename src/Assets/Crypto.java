package Assets;

import Platforms.CryptoExchange;

public class Crypto extends Asset{

    protected Boolean fixedTokens;
    protected Boolean smartContracts;
    protected String blockchain;
    protected int tokensIssued;
    protected int transactionSeconds;
    protected CryptoExchange exchange;

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

    public CryptoExchange getExchange() {
        return exchange;
    }

    public void setExchange(CryptoExchange exchange) {
        this.exchange = exchange;
    }
    //#################### CONSTRUCTORS ############################################
    public Crypto() {
        super();
        this.fixedTokens = false;
        this.smartContracts = false;
        this.blockchain = "-";
        this.tokensIssued = 0;
        this.transactionSeconds = 0;
        this.exchange = null;
    }

    public Crypto(String name, String symbol, String issuer, String industry, double price,
                  double marketCapitalization, Boolean fixedTokens, Boolean smartContracts,
                  String blockchain, int tokensIssued, int transactionSeconds, CryptoExchange exchange) {

        super(name, symbol, issuer, industry, price, marketCapitalization);
        this.fixedTokens = fixedTokens;
        this.smartContracts = smartContracts;
        this.blockchain = blockchain;
        this.tokensIssued = tokensIssued;
        this.transactionSeconds = transactionSeconds;
        this.exchange = exchange;
    }
}
