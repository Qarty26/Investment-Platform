import Assets.Asset;
import Platforms.CryptoExchange;
import Platforms.Exchange;
import Platforms.StockExchange;


import java.util.Vector;

public class Main {


    public static void main(String[] args) {



        Vector<Exchange> exchanges = new Vector<>();

        CryptoExchange a = new CryptoExchange("Binance", false, true, true, true, true);
        CryptoExchange b = new CryptoExchange("Coinbase", true, true, true, false, true);
        CryptoExchange c = new CryptoExchange("KuCoin", false, true, true, true, false);
        StockExchange d = new StockExchange("TradeVille", false, true, 10, 18);
        StockExchange e = new StockExchange("Trading212", true, true, 16, 22);
        StockExchange f = new StockExchange("eToro", true, true, 14, 23);

        exchanges.add(a);
        exchanges.add(b);
        exchanges.add(c);
        exchanges.add(d);
        exchanges.add(e);
        exchanges.add(f);

        String pair = "btc";
        Asset as = new Asset();
        System.out.println(as.getPrice(pair));

    }
}