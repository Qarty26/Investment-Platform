package Model.User;

import Model.DataStoring.Wallet;
import Model.Platforms.Exchange;

public class Account {

    private int idAccount;
    private Wallet wallet;
    private Exchange exchange;
    //#################### SETTERS AND GETTERS ############################################


    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    //#################### CONSTRUCTORS ############################################


    public Account(Exchange exchange) {
        this.wallet = new Wallet();
        this.exchange = exchange;
    }

    public Account(int id, Wallet wallet, Exchange exchange) {
        this.idAccount = id;
        this.wallet = wallet;
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", wallet=" + wallet +
                ", exchange=" + exchange +
                '}';
    }
}
