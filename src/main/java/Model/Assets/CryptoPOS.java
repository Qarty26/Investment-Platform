package Model.Assets;

import Model.Platforms.CryptoExchange;

public class CryptoPOS extends Crypto {

    private double apr;
    private double minStakeRequirement;
    private double stakingDuration;

    //#################### SETTERS AND GETTERS ############################################
    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public double getMinStakeRequirement() {
        return minStakeRequirement;
    }

    public void setMinStakeRequirement(double minStakeRequirement) {
        this.minStakeRequirement = minStakeRequirement;
    }

    public double getStakingDuration() {
        return stakingDuration;
    }

    public void setStakingDuration(double stakingDuration) {
        this.stakingDuration = stakingDuration;
    }

    //#################### CONSTRUCTORS ############################################


    public CryptoPOS() {
        super();
        this.apr = 0;
        this.stakingDuration = 0;
    }

    public CryptoPOS(String name, String symbol, String issuer, String industry, double price,
                     double marketCapitalization, Boolean fixedTokens, Boolean smartContracts,
                     String blockchain, int tokensIssued, int transactionSeconds, CryptoExchange exchange,
                     double apr, double stakingDuration) {
        super(name, symbol, issuer, industry, price, marketCapitalization, fixedTokens, smartContracts,
              blockchain, tokensIssued, transactionSeconds, exchange);
        this.apr = apr;
        this.stakingDuration = stakingDuration;
    }
}
