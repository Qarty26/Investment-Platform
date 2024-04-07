package Model.User;

import Model.DataStoring.Wallet;
import Model.Platforms.Exchange;

public class Account {

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

    //#################### CONSTRUCTORS ############################################


    public Account(Exchange exchange) {
        this.wallet = new Wallet();
        this.exchange = exchange;
    }

    public Account(Wallet wallet, Exchange exchange) {
        this.wallet = wallet;
        this.exchange = exchange;
    }
}
