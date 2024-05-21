package Persistence;

import Exceptions.InvalidDataException;
import Service.Audit;
import Service.DatabaseConnection;
import Model.Platforms.StockExchange;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockExchangeRepository implements GenericRepository<StockExchange> {

    private final DatabaseConnection db;
    private final Audit audit;

    public StockExchangeRepository(DatabaseConnection db, Audit audit) {
        this.db = db;
        this.audit = audit;
    }

    @Override
    public void add(StockExchange entity) throws InvalidDataException {
        String sql = "INSERT INTO StockExchange (idExchange, name, allowDemo, requireKYC, openingHour, closingHour) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdExchange());
            stmt.setString(2, entity.getName());
            stmt.setInt(3, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(4, entity.getRequireKYC() ? 1 : 0);
            stmt.setString(5, String.valueOf(entity.getOpeningHour()));
            stmt.setString(6, String.valueOf(entity.getCloseHour()));
            stmt.executeUpdate();
            audit.logOperation("add");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StockExchange get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM StockExchange WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                audit.logOperation("get");
                return extractStockExchangeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<StockExchange> getAll() {
        ArrayList<StockExchange> exchangeList = new ArrayList<>();
        String sql = "SELECT * FROM StockExchange";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StockExchange exchange = extractStockExchangeFromResultSet(rs);
                exchangeList.add(exchange);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        audit.logOperation("get");
        return exchangeList;
    }

    @Override
    public void update(StockExchange entity) throws InvalidDataException {
        String sql = "UPDATE StockExchange SET name = ?, allowDemo = ?, requireKYC = ?, openingHour = ?, closingHour = ? WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(3, entity.getRequireKYC() ? 1 : 0);
            stmt.setString(4, String.valueOf(entity.getOpeningHour()));
            stmt.setString(5, String.valueOf(entity.getCloseHour()));
            stmt.setInt(6, entity.getIdExchange());
            stmt.executeUpdate();
            audit.logOperation("update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(StockExchange entity) {
        String sql = "DELETE FROM StockExchange WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdExchange());
            stmt.executeUpdate();
            audit.logOperation("delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) AS count FROM StockExchange";
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

    private StockExchange extractStockExchangeFromResultSet(ResultSet rs) throws SQLException {
        return new StockExchange(
                rs.getInt("idExchange"),
                rs.getString("name"),
                rs.getInt("allowDemo") == 1,
                rs.getInt("requireKYC") == 1,
                Integer.parseInt(rs.getString("openingHour")),
                Integer.parseInt(rs.getString("closingHour"))
        );
    }
}
