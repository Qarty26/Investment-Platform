package Persistence;

import Exceptions.InvalidDataException;
import Model.Platforms.CryptoExchange;
import Model.Platforms.Exchange;
import Model.Platforms.StockExchange;
import Service.DatabaseConnection;

import java.util.ArrayList;
import java.util.Vector;


public class ExchangeRepository implements GenericRepository<Exchange>{

    private final DatabaseConnection db;

    public ExchangeRepository(DatabaseConnection db) {
        this.db = db;
    }

    public Vector<Exchange> exchanges = new Vector<>();
    public void seeder()
    {
        CryptoExchange a = new CryptoExchange(1,"Binance", false, true, true, true, true);
        CryptoExchange b = new CryptoExchange(2,"Coinbase", true, true, true, false, true);
        CryptoExchange c = new CryptoExchange(3,"KuCoin", false, true, true, true, false);
        StockExchange d = new StockExchange(4,"TradeVille", false, true, 10, 18);
        StockExchange e = new StockExchange(5,"Trading212", true, true, 16, 22);
        StockExchange f = new StockExchange(6,"eToro", true, true, 14, 23);

        exchanges.add(a);
        exchanges.add(b);
        exchanges.add(c);
        exchanges.add(d);
        exchanges.add(e);
        exchanges.add(f);
    }

    public void erase()
    {
        exchanges.clear();
    }


    @Override
    public void add(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot add null exchange.");
        }
        exchanges.add(exchange);
    }

    @Override
    public Exchange get(int index) throws InvalidDataException {
        if (index < 0 || index >= exchanges.size()) {
            throw new InvalidDataException("Invalid index for getting exchange.");
        }
        return exchanges.get(index);
    }

    @Override
    public ArrayList<Exchange> getAll() {
        ArrayList<Exchange> allAssets = new ArrayList<>(exchanges);
        return allAssets;
    }

    @Override
    public void update(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot update null exchange.");
        }
        int index = exchanges.indexOf(exchange);
        if (index == -1) {
            throw new InvalidDataException("Exchange not found for update.");
        }
        exchanges.set(index, exchange);
    }

    @Override
    public void delete(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot delete null exchange.");
        }
        if (!exchanges.remove(exchange)) {
            throw new InvalidDataException("Exchange not found for deletion.");
        }

    }

    @Override
    public int getSize() {
        return exchanges.size();
    }
}
