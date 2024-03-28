package DataStoring;

import Assets.Asset;
import Assets.Transaction;
import Helpers.Pair;

import java.util.Objects;
import java.util.Vector;



public class Wallet {
    private Vector<Pair<Asset,Double>> spot;
    private Vector<Pair<Asset,Double>> earn;
    private Vector<Transaction> history;
    private Double balance;

    public Wallet() {

    }

    public Integer checkExistence(Vector<Pair<Asset,Double>> subWallet, String asset){

        for(int i=0; i<subWallet.size(); i++)
            if(Objects.equals(subWallet.get(i).getFirst().getSymbol(), asset))
                return i;

        return null;
    }
    public Boolean checkAllowedToMove(Vector<Pair<Asset,Double>> subWallet, String asset, double size){

        for(var x : subWallet)
            if(x.getSecond() >= size)
                return true;
        return false;
    }
    public Boolean spotToEarn(String asset, double size){

        Integer position = checkExistence(spot,asset);
        if(position == null)
            return false;
        Boolean enoughSize = checkAllowedToMove(spot,asset,size);
        if(!enoughSize)
            return false;
        spot.get(position).setSecond(  spot.get(position).getSecond() - size  );

        position = checkExistence(earn,asset);
        if(position == null) {
            Asset a = new Asset(asset);
            Pair p = new Pair(a,size);
            earn.add(p);
            return true;
        }
        else {
            earn.get(position).setSecond(  earn.get(position).getSecond() + size  );
            return true;
        }
    }

    public Boolean earnToSpot(String asset, double size){

        Integer position = checkExistence(earn,asset);
        if(position == null)
            return false;
        Boolean enoughSize = checkAllowedToMove(earn,asset,size);
        if(!enoughSize)
            return false;
        earn.get(position).setSecond(  earn.get(position).getSecond() - size  );

        position = checkExistence(spot,asset);
        if(position == null) {
            Asset a = new Asset(asset);
            Pair p = new Pair(a,size);
            spot.add(p);
            return true;
        }
        else {
            spot.get(position).setSecond(  spot.get(position).getSecond() + size  );
            return true;
        }
    }

    public Boolean Trade(String symbol, String type, Double size){
        if(type.equalsIgnoreCase("BUY"))
            size = Math.abs(size);
        else if(type.equalsIgnoreCase("SELL"))
            size = - Math.abs(size);
        else return false;


        return true;
    }
}
