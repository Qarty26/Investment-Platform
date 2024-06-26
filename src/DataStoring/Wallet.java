package DataStoring;

import Assets.Asset;
import Assets.Transaction;
import Helpers.Pair;

import java.util.Objects;
import java.util.Vector;



public class Wallet {
    final Vector<Pair<Asset,Double>> spot;
    final Vector<Pair<Asset,Double>> earn;
    final Vector<Transaction> history;
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

    //#################### CONSTRUCTORS ############################################
    public Wallet() {
        spot = new Vector<>();
        earn = new Vector<>();
        history = new Vector<>();
        balance = 0.0;
    }

    //returns the index where it was found
    public Integer checkExistence(Vector<Pair<Asset,Double>> subWallet, String asset){

        for(int i=0; i<subWallet.size(); i++)
            if(Objects.equals(subWallet.get(i).getFirst().getSymbol(), asset))
                return i;

        return null;
    }

    //returns true if the symbol exists and it's size is bigger than the paramter
    public Boolean checkAllowedToMove(Vector<Pair<Asset,Double>> subWallet, String symbol, Double size){

        for(var x : subWallet)
            if(x.getFirst().getName().equalsIgnoreCase(symbol) && x.getSecond() >= size)
                return true;
        return false;
    }

    //move asset from spot to earn
    public Boolean spotToEarn(String symbol, Double size){

        Integer position = checkExistence(spot,symbol);
        if(position == null)
            return false;
        Boolean enoughSize = checkAllowedToMove(spot,symbol,size);
        if(!enoughSize)
            return false;
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
    public Boolean Trade(String symbol, String type, Double cash){


        Asset asset = new Asset(symbol);

        if(type.equalsIgnoreCase("BUY")){
            cash = Math.abs(cash);
            if(cash < balance)
                return false;

            Double price = asset.getPrice();
            if(price == null || price == 0)
                return false;

            Double size = cash / price;
            if(!updateBalance(-cash))
                return false;
            addToSpot(symbol,size);

            Transaction transaction = new Transaction(symbol,price,size,type);
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
            if(!updateBalance(cash))
                return false;
            addToSpot(symbol,size);

            Transaction transaction = new Transaction(symbol,price,size,type);
            history.add(transaction);

            return true;
        }

        else return false;

    }
}
