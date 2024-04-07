package Model.Platforms;

import Model.Helpers.ReadUpdateInterface;

import java.util.Scanner;

public class StockExchange extends Exchange implements ReadUpdateInterface {

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


    @Override
    public void read() {
        super.read(); // call the read() method of the parent class (Exchange)
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter opening hour: ");
        setOpeningHour(sc.nextInt());

        System.out.print("Enter closing hour: ");
        setCloseHour(sc.nextInt());
    }

    @Override
    public void update() {
        super.update(); // call the update() method of the parent class (Exchange)
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update opening hour? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new opening hour: ");
            setOpeningHour(sc.nextInt());
        }

        System.out.print("Do you want to update closing hour? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new closing hour: ");
            setCloseHour(sc.nextInt());
        }
    }
}
