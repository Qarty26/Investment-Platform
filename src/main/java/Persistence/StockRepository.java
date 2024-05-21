package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Stock;
import Service.Audit;
import Service.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockRepository implements GenericRepository<Stock> {

    private final DatabaseConnection db;
    private final Audit audit;

    public StockRepository(DatabaseConnection db, Audit audit) {
        this.db = db;
        this.audit = audit;
    }

    @Override
    public void add(Stock entity) throws InvalidDataException {
        String sql = "INSERT INTO Stock (idAsset, name, symbol, issuer, industry, marketCapitalization, price, market, dividedRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSymbol());
            stmt.setString(4, entity.getIssuer());
            stmt.setString(5, entity.getIndustry());
            stmt.setDouble(6, entity.getMarketCapitalization());
            stmt.setDouble(7, entity.getPrice());
            stmt.setString(8, entity.getMarket());
            stmt.setDouble(9, entity.getDividendRate());
            stmt.executeUpdate();
            audit.logOperation("add");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stock get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM Stock WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                audit.logOperation("get");
                return extractStockFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Stock> getAll() {
        ArrayList<Stock> stockList = new ArrayList<>();
        String sql = "SELECT * FROM Stock";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Stock stock = extractStockFromResultSet(rs);
                stockList.add(stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        audit.logOperation("get");
        return stockList;
    }

    @Override
    public void update(Stock entity) throws InvalidDataException {
        String sql = "UPDATE Stock SET name = ?, symbol = ?, issuer = ?, industry = ?, marketCapitalization = ?, price = ?, market = ?, dividedRate = ? WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getSymbol());
            stmt.setString(3, entity.getIssuer());
            stmt.setString(4, entity.getIndustry());
            stmt.setDouble(5, entity.getMarketCapitalization());
            stmt.setDouble(6, entity.getPrice());
            stmt.setString(7, entity.getMarket());
            stmt.setDouble(8, entity.getDividendRate());
            stmt.setInt(9, entity.getIdAsset());
            stmt.executeUpdate();
            audit.logOperation("update");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Stock entity) {
        String sql = "DELETE FROM Stock WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.executeUpdate();
            audit.logOperation("delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) AS count FROM Stock";
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

    private Stock extractStockFromResultSet(ResultSet rs) throws SQLException {
        return new Stock(
                rs.getInt("idAsset"),
                rs.getString("name"),
                rs.getString("symbol"),
                rs.getString("issuer"),
                rs.getString("industry"),
                rs.getDouble("price"),
                rs.getDouble("marketCapitalization"),
                rs.getString("market"),
                rs.getDouble("dividedRate")
        );
    }
}
