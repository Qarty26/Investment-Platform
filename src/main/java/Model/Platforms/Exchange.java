package Model.Platforms;

import Model.Helpers.ReadUpdateInterface;

import java.util.Scanner;

public class Exchange implements ReadUpdateInterface {

    protected String name;
    protected Boolean allowDemo;
    protected Boolean requireKYC;

//#################### SETTERS AND GETTERS ############################################
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowDemo() {
        return allowDemo;
    }

    public void setAllowDemo(Boolean allowDemo) {
        this.allowDemo = allowDemo;
    }

    public Boolean getRequireKYC() {
        return requireKYC;
    }

    public void setRequireKYC(Boolean requireKYC) {
        this.requireKYC = requireKYC;
    }

//#################### CONSTRUCTORS ############################################

    public Exchange() {
        this.name = "Unknown name";
        this.allowDemo = false;
        this.requireKYC = false;
    }
    public Exchange(String name, Boolean allowDemo, Boolean requireKYC) {
        this.name = name;
        this.allowDemo = allowDemo;
        this.requireKYC = requireKYC;
    }


    @Override
    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        setName(sc.nextLine());

        System.out.print("Allow demo? (true/false): ");
        setAllowDemo(sc.nextBoolean());

        System.out.print("Require KYC? (true/false): ");
        setRequireKYC(sc.nextBoolean());
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update the name? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new name: ");
            setName(sc.nextLine());
        }

        System.out.print("Do you want to update allowDemo? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Allow demo? (true/false): ");
            setAllowDemo(sc.nextBoolean());
        }

        System.out.print("Do you want to update requireKYC? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Require KYC? (true/false): ");
            setRequireKYC(sc.nextBoolean());
        }
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "name='" + name + '\'' +
                ", allowDemo=" + allowDemo +
                ", requireKYC=" + requireKYC +
                '}';
    }
}
