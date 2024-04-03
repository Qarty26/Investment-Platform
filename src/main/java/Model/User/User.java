package Model.User;

import java.util.Vector;

public class User {

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

    //#################### CONSTRUCTORS ############################################


    public User() {
        this.name = "-";
        this.nickName = "-";
        this.email = "-";
        this.balance = 0.0;
        this.accounts = new Vector<>();
    }

    public User(String name, String nickName, String email, Double balance) {
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
}
