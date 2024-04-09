package Persistence;

import Exceptions.InvalidDataException;
import Model.Platforms.Exchange;
import Service.DatabaseConnection;

import java.util.ArrayList;
import java.util.Vector;


public class ExchangeRepository implements GenericRepository<Exchange>{

    private final DatabaseConnection db;

    public ExchangeRepository(DatabaseConnection db) {
        this.db = db;
    }

    Vector<Exchange> exchanges = new Vector<>();

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
