package Model.Platforms;

import Model.Helpers.ReadUpdateInterface;

import java.util.Scanner;

public class CryptoExchange extends Exchange implements ReadUpdateInterface {

    private Boolean allowLeverage;
    private Boolean ICOs;
    private Boolean cardPlans;


//#################### SETTERS AND GETTERS ############################################

    public Boolean getAllowLeverage() {
        return allowLeverage;
    }

    public void setAllowLeverage(Boolean allowLeverage) {
        this.allowLeverage = allowLeverage;
    }

    public Boolean getICOs() {
        return ICOs;
    }

    public void setICOs(Boolean ICOs) {
        this.ICOs = ICOs;
    }

    public Boolean getCardPlans() {
        return cardPlans;
    }

    public void setCardPlans(Boolean cardPlans) {
        this.cardPlans = cardPlans;
    }

//#################### CONSTRUCTORS ############################################

    public CryptoExchange(){

        super();
        this.allowLeverage = false;
        this.ICOs = false;
        this.cardPlans = false;
    }

    public CryptoExchange(String name, Boolean allowDemo, Boolean requireKYC,Boolean allowLeverage, Boolean ICOs, Boolean cardPlans) {
        super(name,allowDemo,requireKYC);
        this.allowLeverage = allowLeverage;
        this.ICOs = ICOs;
        this.cardPlans = cardPlans;
    }

    @Override
    public void read() {
        super.read();
        Scanner sc = new Scanner(System.in);

        System.out.print("Allow leverage? (true/false): ");
        setAllowLeverage(sc.nextBoolean());

        System.out.print("Support ICOs? (true/false): ");
        setICOs(sc.nextBoolean());

        System.out.print("Offer card plans? (true/false): ");
        setCardPlans(sc.nextBoolean());
    }

    @Override
    public void update() {
        super.update();
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update allowLeverage? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Allow leverage? (true/false): ");
            setAllowLeverage(sc.nextBoolean());
        }

        System.out.print("Do you want to update ICOs? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Support ICOs? (true/false): ");
            setICOs(sc.nextBoolean());
        }

        System.out.print("Do you want to update cardPlans? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Offer card plans? (true/false): ");
            setCardPlans(sc.nextBoolean());
        }
    }
}
