package Model.DataStoring;

import Model.Assets.Asset;
import Model.Assets.Transaction;
import Model.Helpers.Pair;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;



public class Wallet {

    int idWallet;
    private Vector<Pair<Asset,Double>> spot;
    private Vector<Pair<Asset,Double>> earn;
    private Vector<Transaction> history;
    private Double balance;


    //#################### SETTERS AND GETTERS ############################################
    public Double getBalance() {
        return balance;
    }

    public Boolean updateBalance(Double cash){

        if(this.balance + cash < 0)
            return false;

        this.balance += cash;
        return true;
    }

    public Vector<Pair<Asset, Double>> getSpot() {
        return spot;
    }

    public Vector<Pair<Asset, Double>> getEarn() {
        return earn;
    }

    public Vector<Transaction> getHistory() {
        return history;
    }


    public int getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setSpot(Vector<Pair<Asset, Double>> spot) {
        this.spot = spot;
    }

    public void setEarn(Vector<Pair<Asset, Double>> earn) {
        this.earn = earn;
    }

    public void setHistory(Vector<Transaction> history) {
        this.history = history;
    }

    //#################### CONSTRUCTORS ############################################
    public Wallet() {
        spot = new Vector<>();
        earn = new Vector<>();
        history = new Vector<>();
        balance = 0.0;
    }

    public Wallet(int id, double balance)
    {
        this.idWallet = id;
        this.balance = balance;
        this.spot = new Vector<>();
        this.earn = new Vector<>();
        this.history = new Vector<>();
    }

    //returns the index where it was found
    public Integer checkExistence(Vector<Pair<Asset,Double>> subWallet, String asset){

        for(int i=0; i<subWallet.size(); i++)
            if(Objects.equals(subWallet.get(i).getFirst().getSymbol(), asset))
                return i;

        return null;
    }

    //returns true if the symbol exists, and it's size is bigger than the parameter
    public Boolean checkAllowedToMove(Vector<Pair<Asset,Double>> subWallet, String symbol, Double size){

        for(var x : subWallet)
            if(x.getFirst().getSymbol().equalsIgnoreCase(symbol) && x.getSecond() >= size)
                return true;
        return false;
    }

    //move asset from spot to earn
    public Boolean spotToEarn(String symbol, Double size){

        symbol = symbol.toUpperCase();
        Integer position = checkExistence(spot,symbol);
        if(position == null)
            return false;

        Boolean enoughSize = checkAllowedToMove(spot,symbol,size);
        if(!enoughSize)
        {
            System.out.println("nope");
            return false;
        }
        spot.get(position).setSecond(  spot.get(position).getSecond() - size  );

        position = checkExistence(earn,symbol);
        if(position == null) {
            Asset a = new Asset(symbol);
            Pair p = new Pair(a,size);
            earn.add(p);
            return true;
        }
        else {
            earn.get(position).setSecond(  earn.get(position).getSecond() + size  );
            return true;
        }
    }

    //move asset from earn to spot
    public Boolean earnToSpot(String symbol, Double size){

        symbol = symbol.toUpperCase();
        Integer position = checkExistence(earn,symbol);
        if(position == null)
            return false;
        Boolean enoughSize = checkAllowedToMove(earn,symbol,size);
        if(!enoughSize)
            return false;
        earn.get(position).setSecond(  earn.get(position).getSecond() - size  );

        position = checkExistence(spot,symbol);
        if(position == null) {
            Asset a = new Asset(symbol);
            Pair p = new Pair(a,size);
            spot.add(p);
            return true;
        }
        else {
            spot.get(position).setSecond(  spot.get(position).getSecond() + size  );
            return true;
        }
    }

    //changes the value of a spot pair
    //if it doesn't exist: add it
    //the value can be negative, it's checked in updateBalance()
    public Boolean addToSpot(String symbol,Double size){
        Integer position = checkExistence(spot,symbol);
        if(position == null){
            Asset a = new Asset(symbol);
            Pair p = new Pair(a,size);
            spot.add(p);
            return true;
        }
        else {
            spot.get(position).setSecond( spot.get(position).getSecond() + size );
            return true;
        }

    }


    //trade function, only types eligible are Buy and Sell
    //cash is made positive and adapted corresponding to the type
    //history is updated
    public Boolean Trade(String symbol, String type, Double cash) throws IOException {

        symbol = symbol.toUpperCase();
        type = type.toUpperCase();

        Asset asset = new Asset(symbol);

        if(type.equalsIgnoreCase("BUY")){
            cash = Math.abs(cash);
            if(cash > balance)
                return false;

            Double price = asset.getPrice();
            if(price == null || price == 0)
                return false;

            Double size = cash / price;
            if(!updateBalance(-cash))
                return false;
            addToSpot(symbol,size);

            Transaction transaction = new Transaction(100,symbol,price,size,type);
            history.add(transaction);

            return true;
        }

        else if(type.equalsIgnoreCase("SELL")) {
            cash = - Math.abs(cash);

            Double price = asset.getPrice();
            if(price == null || price == 0)
                return false;

            Double size = cash / price;
            checkAllowedToMove(spot,symbol,size);
            if(!updateBalance(-cash))
                return false;
            addToSpot(symbol,size);

            Transaction transaction = new Transaction(100,symbol,price,size,type);
            history.add(transaction);

            return true;
        }

        else return false;

    }

    public void printTransactions()
    {
        for(var x: history)
            System.out.println(x.toString());
    }

    public void printSubWallet(Vector<Pair<Asset,Double>> subWallet)
    {
        for(var x: subWallet)
            System.out.println(x.getFirst().getSymbol() + " size: " + x.getSecond());
    }

    public double getTotalValue() throws IOException {
        double value = 0.0;
        for(var x : spot)
            value+=x.getSecond() * x.getFirst().getPrice();

        for(var x : earn)
            value+=x.getSecond() * x.getFirst().getPrice();

        return value;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "idWallet=" + idWallet +
                ", spot=" + spot +
                ", earn=" + earn +
                ", history=" + history +
                ", balance=" + balance +
                '}';
    }
}
