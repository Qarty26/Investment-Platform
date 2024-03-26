package Platforms;

public class CryptoExchange extends Exchange {

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
}
