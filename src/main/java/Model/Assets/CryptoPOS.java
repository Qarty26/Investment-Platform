package Model.Assets;

import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.CryptoExchange;
import java.util.Scanner;

public class CryptoPOS extends Crypto implements ReadUpdateInterface {

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
                     String blockchain, int tokensIssued, int transactionSeconds,
                     double apr, double stakingDuration) {
        super(name, symbol, issuer, industry, price, marketCapitalization, fixedTokens, smartContracts,
              blockchain, tokensIssued, transactionSeconds);
        this.apr = apr;
        this.stakingDuration = stakingDuration;
    }

    @Override
    public void read() {
        super.read();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter APR (Annual Percentage Rate): ");
        setApr(sc.nextDouble());

        System.out.print("Enter minimum stake requirement: ");
        setMinStakeRequirement(sc.nextDouble());

        System.out.print("Enter staking duration: ");
        setStakingDuration(sc.nextDouble());
    }

    @Override
    public void update() {
        super.update();
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update APR? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new APR (Annual Percentage Rate): ");
            setApr(sc.nextDouble());
        }

        System.out.print("Do you want to update minimum stake requirement? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new minimum stake requirement: ");
            setMinStakeRequirement(sc.nextDouble());
        }

        System.out.print("Do you want to update staking duration? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new staking duration: ");
            setStakingDuration(sc.nextDouble());
        }
    }


}
