import Assets.Asset;
import Platforms.CryptoExchange;
import Platforms.Exchange;
import Platforms.StockExchange;
import User.User;
import User.Account;


import java.util.Vector;

public class Main {


    public static void main(String[] args) {


        Vector<Exchange> exchanges = new Vector<>();
        Vector<User> users = new Vector<>();


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

        User user1 = new User("Mincu Adrian", "skpha", "skpha13@gmail.com", 2000.0);
        User user2 = new User("Dogaru Mihai", "matoka", "matoka26@gmail.com", 1500.0);
        User user3 = new User("Mircea Razvan", "qarty", "qarty99@gmail.com", 5000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);

        for (int i = 0; i < users.size(); i++)
            for (int j = 0; j <= 3; j++)
                if (i != j) {
                    Account account = new Account(exchanges.get(j));
                    users.get(i).addAccount(account);
                }

        System.out.println(users.get(0).getBalance());
        System.out.println(users.get(0).getAccounts().get(0).getWallet().getBalance());
        System.out.println("------");
        users.get(0).trasferBalanceToExchange(1000.0,"coinbase");
        System.out.println(users.get(0).getBalance());
        System.out.println(users.get(0).getAccounts().get(0).getWallet().getBalance());


//        String pair = "btc";
//        Asset as = new Asset(pair);
//        System.out.println(as.getPrice());

    }
}