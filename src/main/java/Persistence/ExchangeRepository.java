package Persistence;

import Model.Platforms.Exchange;
import Service.DatabaseConnection;



public class ExchangeRepository implements GenericRepository<Exchange>{

    private final DatabaseConnection db;

    public ExchangeRepository(DatabaseConnection db) {
        this.db = db;
    }



    @Override
    public void add(Exchange exchange){

    }

    @Override
    public Exchange get(int index){
        return null;
    }

    @Override
    public void update(Exchange exchange) {

    }

    @Override
    public void delete(Exchange exchange) {

    }

    @Override
    public int getSize() {
        return 0;
//        return exchanges.size();
    }
}
