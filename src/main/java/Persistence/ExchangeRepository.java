package Persistence;

import Exceptions.InvalidDataException;
import Model.Platforms.CryptoExchange;
import Model.Platforms.Exchange;
import Model.Platforms.StockExchange;
import Service.DatabaseConnection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public void add_old(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot add null exchange.");
        }
        exchanges.add(exchange);
    }

    public Exchange get_old(int index) throws InvalidDataException {
        if (index < 0 || index >= exchanges.size()) {
            throw new InvalidDataException("Invalid index for getting exchange.");
        }
        return exchanges.get(index);
    }

    public ArrayList<Exchange> getAll_old() {
        ArrayList<Exchange> allAssets = new ArrayList<>(exchanges);
        return allAssets;
    }

    public void update_old(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot update null exchange.");
        }
        int index = exchanges.indexOf(exchange);
        if (index == -1) {
            throw new InvalidDataException("Exchange not found for update.");
        }
        exchanges.set(index, exchange);
    }


    public void delete_old(Exchange exchange) throws InvalidDataException {
        if (exchange == null) {
            throw new InvalidDataException("Cannot delete null exchange.");
        }
        if (!exchanges.remove(exchange)) {
            throw new InvalidDataException("Exchange not found for deletion.");
        }

    }

    public int getSize_old() {
        return exchanges.size();
    }

    @Override
    public void add(Exchange entity) throws InvalidDataException {
        String sql = "INSERT INTO Exchange (idExchange, name, allowDemo, requireKYC) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdExchange());
            stmt.setString(2, entity.getName());
            stmt.setInt(3, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(4, entity.getRequireKYC() ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Exchange get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM Exchange WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractExchangeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Exchange> getAll() {
        ArrayList<Exchange> exchangeList = new ArrayList<>();
        String sql = "SELECT * FROM Exchange";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exchange exchange = extractExchangeFromResultSet(rs);
                exchangeList.add(exchange);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exchangeList;
    }

    @Override
    public void update(Exchange entity) throws InvalidDataException {
        String sql = "UPDATE Exchange SET name = ?, allowDemo = ?, requireKYC = ? WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(3, entity.getRequireKYC() ? 1 : 0);
            stmt.setInt(4, entity.getIdExchange());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Exchange entity) {
        String sql = "DELETE FROM Exchange WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdExchange());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) AS count FROM Exchange";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private Exchange extractExchangeFromResultSet(ResultSet rs) throws SQLException {
        return new Exchange(
                rs.getInt("idExchange"),
                rs.getString("name"),
                rs.getInt("allowDemo") == 1,
                rs.getInt("requireKYC") == 1
        );
    }
}
