package Persistence;

import Exceptions.InvalidDataException;

import Model.Assets.Crypto;
import Service.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CryptoRepository implements GenericRepository<Crypto> {

    private final DatabaseConnection db;

    public CryptoRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(Crypto entity) throws InvalidDataException {
        String sql = "INSERT INTO Crypto (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSymbol());
            stmt.setString(4, entity.getIssuer());
            stmt.setString(5, entity.getIndustry());
            stmt.setDouble(6, entity.getMarketCapitalization());
            stmt.setDouble(7, entity.getPrice());
            stmt.setInt(8, boolToInt(entity.getFixedTokens()));
            stmt.setInt(9, boolToInt(entity.getSmartContracts()));
            stmt.setString(10, entity.getBlockchain());
            stmt.setInt(11, entity.getTokensIssued());
            stmt.setInt(12, entity.getTransactionSeconds());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Crypto get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM Crypto WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCryptoFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Crypto> getAll() {
        ArrayList<Crypto> cryptos = new ArrayList<>();
        String sql = "SELECT * FROM Crypto";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Crypto crypto = extractCryptoFromResultSet(rs);
                cryptos.add(crypto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cryptos;
    }

    @Override
    public void update(Crypto entity) throws InvalidDataException {
        String sql = "UPDATE Crypto SET name = ?, symbol = ?, issuer = ?, industry = ?, marketCapitalization = ?, price = ?, fixedTokens = ?, smartContracts = ?, blockchain = ?, tokensIssued = ?, transactionSeconds = ? WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getSymbol());
            stmt.setString(3, entity.getIssuer());
            stmt.setString(4, entity.getIndustry());
            stmt.setDouble(5, entity.getMarketCapitalization());
            stmt.setDouble(6, entity.getPrice());
            stmt.setInt(7, boolToInt(entity.getFixedTokens()));
            stmt.setInt(8, boolToInt(entity.getSmartContracts()));
            stmt.setString(9, entity.getBlockchain());
            stmt.setInt(10, entity.getTokensIssued());
            stmt.setInt(11, entity.getTransactionSeconds());
            stmt.setInt(12, entity.getIdAsset());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Crypto entity) {
        String sql = "DELETE FROM Crypto WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) AS count FROM Crypto";
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

    private Crypto extractCryptoFromResultSet(ResultSet rs) throws SQLException {
        return new Crypto(
                rs.getInt("idAsset"),
                rs.getString("name"),
                rs.getString("symbol"),
                rs.getString("issuer"),
                rs.getString("industry"),
                rs.getDouble("price"),
                rs.getDouble("marketCapitalization"),
                intToBool(rs.getInt("fixedTokens")),
                intToBool(rs.getInt("smartContracts")),
                rs.getString("blockchain"),
                rs.getInt("tokensIssued"),
                rs.getInt("transactionSeconds")
        );
    }

    private int boolToInt(Boolean value) {
        return value ? 1 : 0;
    }

    private Boolean intToBool(int value) {
        return value != 0;
    }
}
