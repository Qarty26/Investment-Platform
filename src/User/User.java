package User;

import java.util.Vector;

public class User {

    private String name;
    private String nickName;
    private String email;
    private int balance;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //#################### CONSTRUCTORS ############################################


    public User() {
        this.name = "-";
        this.nickName = "-";
        this.email = "-";
        this.balance = 0;
    }

    public User(String name, String nickName, String email, int balance) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.balance = balance;
    }
}
