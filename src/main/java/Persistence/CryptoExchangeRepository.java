package Persistence;

import Exceptions.InvalidDataException;
import Model.Platforms.CryptoExchange;
import Service.Audit;
import Service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CryptoExchangeRepository implements GenericRepository<CryptoExchange> {

    private final DatabaseConnection db;
    private final Audit audit;

    public CryptoExchangeRepository(DatabaseConnection db, Audit audit) {
        this.db = db;
        this.audit = audit;
    }

    @Override
    public void add(CryptoExchange entity) throws InvalidDataException {
        String sql = "INSERT INTO CryptoExchange (idExchange, name, allowDemo, requireKYC, allowLeverage, ICOs, cardPlans) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdExchange());
            stmt.setString(2, entity.getName());
            stmt.setInt(3, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(4, entity.getRequireKYC() ? 1 : 0);
            stmt.setInt(5, entity.getAllowLeverage() ? 1 : 0);
            stmt.setInt(6, entity.getICOs() ? 1 : 0);
            stmt.setInt(7, entity.getCardPlans() ? 1 : 0);
            stmt.executeUpdate();
            audit.logOperation("add");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CryptoExchange get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM CryptoExchange WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                audit.logOperation("get");
                return extractCryptoExchangeFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<CryptoExchange> getAll() {
        ArrayList<CryptoExchange> exchangeList = new ArrayList<>();
        String sql = "SELECT * FROM CryptoExchange";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CryptoExchange exchange = extractCryptoExchangeFromResultSet(rs);
                exchangeList.add(exchange);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        audit.logOperation("get");
        return exchangeList;
    }

    @Override
    public void update(CryptoExchange entity) throws InvalidDataException {
        String sql = "UPDATE CryptoExchange SET name = ?, allowDemo = ?, requireKYC = ?, allowLeverage = ?, ICOs = ?, cardPlans = ? WHERE idExchange = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getAllowDemo() ? 1 : 0);
            stmt.setInt(3, entity.getRequireKYC() ? 1 : 0);
            stmt.setInt(4, entity.getAllowLeverage() ? 1 : 0);
            stmt.setInt(5, entity.getICOs() ? 1 : 0);
            stmt.setInt(6, entity.getCardPlans() ? 1 : 0);
            stmt.setInt(7, entity.getIdExchange());
            stmt.executeUpdate();
            audit.logOperation("update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CryptoExchange entity) {
        String sql = "DELETE FROM CryptoExchange WHERE idExchange = ?";
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
        String sql = "SELECT COUNT(*) AS count FROM CryptoExchange";
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

    private CryptoExchange extractCryptoExchangeFromResultSet(ResultSet rs) throws SQLException {
        return new CryptoExchange(
                rs.getInt("idExchange"),
                rs.getString("name"),
                rs.getInt("allowDemo") == 1,
                rs.getInt("requireKYC") == 1,
                rs.getInt("allowLeverage") == 1,
                rs.getInt("ICOs") == 1,
                rs.getInt("cardPlans") == 1
        );
    }
}
