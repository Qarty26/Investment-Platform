package Model.User;

import Model.Helpers.ReadUpdateInterface;

import java.util.Scanner;
import java.util.Vector;

public class User implements ReadUpdateInterface {

    private int idUser;
    private String name;
    private String nickName;
    private String email;
    private Double balance;
    private Vector<Account> accounts;


    //#################### SETTERS AND GETTERS ############################################

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public Boolean updateBalance(Double cash){

        if(this.balance + cash < 0)
            return false;

        this.balance += cash;
        return true;
    }

    public Vector<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) { this.accounts.add(account); }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    //#################### CONSTRUCTORS ############################################


    public User() {
        this.name = "-";
        this.nickName = "-";
        this.email = "-";
        this.balance = 0.0;
        this.accounts = new Vector<>();
    }

    public User(int id, String name, String nickName, String email, Double balance) {
        this.idUser = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.balance = balance;
        this.accounts = new Vector<>();
    }


    public Boolean trasferBalanceToExchange(Double size, String exchangeName)
    {
        for(int i=0; i<accounts.size(); i++)
        {
            if(accounts.get(i).getExchange().getName().equalsIgnoreCase(exchangeName.toLowerCase()))
            {
                if(updateBalance(-size)) {
                    accounts.get(i).getWallet().updateBalance(size);
                    return true;
                }
                else return false;

            }
        }

        return false;
    }

    @Override
    public void read() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter id: ");
        setIdUser(sc.nextInt());

        System.out.print("Enter name: ");
        setName(sc.nextLine());

        System.out.print("Enter nickname: ");
        setNickName(sc.nextLine());

        System.out.print("Enter email: ");
        setEmail(sc.nextLine());

        System.out.print("Enter balance: ");
        updateBalance(sc.nextDouble());
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to update the id? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new id: ");
            setIdUser(sc.nextInt());
        }

        System.out.print("Do you want to update the name? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new name: ");
            setName(sc.nextLine());
        }

        System.out.print("Do you want to update the nickname? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new nickname: ");
            setNickName(sc.nextLine());
        }

        System.out.print("Do you want to update the email? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter new email: ");
            setEmail(sc.nextLine());
        }

        System.out.print("Do you want to update the balance? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter how much you want to add/substract: ");
            updateBalance(sc.nextDouble());
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", accounts=" + accounts +
                '}';
    }
}
