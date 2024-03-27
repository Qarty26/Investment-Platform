package User;

import DataStoring.Wallet;
import Platforms.Exchange;

public class Account {

    private Wallet wallet;
    private Exchange exchange;
    private Integer balance;

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    //#################### CONSTRUCTORS ############################################

    public Account(Wallet wallet, Exchange exchange, Integer balance) {
        this.wallet = wallet;
        this.exchange = exchange;
        this.balance = balance;
    }
}
