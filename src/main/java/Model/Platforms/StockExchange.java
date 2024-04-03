package Model.Platforms;

public class StockExchange extends Exchange{

    private int openingHour;
    private int closeHour;

//#################### SETTERS AND GETTERS ############################################
    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(int closeHour) {
        this.closeHour = closeHour;
    }

//#################### CONSTRUCTORS ############################################


    public StockExchange() {
        this.openingHour = 0;
        this.closeHour = 0;
    }

    public StockExchange(String name, Boolean allowDemo, Boolean requireKYC, int openingHour, int closeHour) {
        super(name, allowDemo, requireKYC);
        this.openingHour = openingHour;
        this.closeHour = closeHour;
    }
}
