package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.CryptoPOW;
import Service.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CryptoPOWRepository implements GenericRepository<CryptoPOW> {

    private final DatabaseConnection db;

    public CryptoPOWRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(CryptoPOW entity) throws InvalidDataException {
        String sql = "INSERT INTO CryptoPOW (idAsset, name, symbol, issuer, industry, marketCapitalization, price, fixedTokens, smartContracts, blockchain, tokensIssued, transactionSeconds, cutRate, cutAmount, coinsPerBlock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setInt(13, entity.getCutRate());
            stmt.setInt(14, entity.getCutAmount());
            stmt.setDouble(15, entity.getCoinsPerBlock());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CryptoPOW get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM CryptoPOW WHERE idAsset = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCryptoPOWFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<CryptoPOW> getAll() {
        ArrayList<CryptoPOW> cryptoPOWList = new ArrayList<>();
        String sql = "SELECT * FROM CryptoPOW";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CryptoPOW cryptoPOW = extractCryptoPOWFromResultSet(rs);
                cryptoPOWList.add(cryptoPOW);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cryptoPOWList;
    }

    @Override
    public void update(CryptoPOW entity) throws InvalidDataException {
        String sql = "UPDATE CryptoPOW SET name = ?, symbol = ?, issuer = ?, industry = ?, marketCapitalization = ?, price = ?, fixedTokens = ?, smartContracts = ?, blockchain = ?, tokensIssued = ?, transactionSeconds = ?, cutRate = ?, cutAmount = ?, coinsPerBlock = ? WHERE idAsset = ?";
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
            stmt.setInt(12, entity.getCutRate());
            stmt.setInt(13, entity.getCutAmount());
            stmt.setDouble(14, entity.getCoinsPerBlock());
            stmt.setInt(15, entity.getIdAsset());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CryptoPOW entity) {
        String sql = "DELETE FROM CryptoPOW WHERE idAsset = ?";
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
        String sql = "SELECT COUNT(*) AS count FROM CryptoPOW";
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

    private CryptoPOW extractCryptoPOWFromResultSet(ResultSet rs) throws SQLException {
        return new CryptoPOW(
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
                rs.getInt("transactionSeconds"),
                rs.getInt("cutRate"),
                rs.getInt("cutAmount"),
                rs.getDouble("coinsPerBlock")
        );
    }

    private int boolToInt(Boolean value) {
        return value ? 1 : 0;
    }

    private Boolean intToBool(int value) {
        return value != 0;
    }
}
