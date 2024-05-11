package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Transaction;
import Service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionRepository implements GenericRepository<Transaction> {

    private final DatabaseConnection db;

    public TransactionRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(Transaction entity) throws InvalidDataException {
        String sql = "INSERT INTO Transaction (idTransaction, symbol, price, amount, type) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdTransaction());
            stmt.setString(2, entity.getSymbol());
            stmt.setDouble(3, entity.getPrice());
            stmt.setDouble(4, entity.getAmount());
            stmt.setString(5, entity.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction get(int id) throws InvalidDataException {
        String sql = "SELECT * FROM Transaction WHERE idTransaction = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTransactionFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Transaction> getAll() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM Transaction";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction transaction = extractTransactionFromResultSet(rs);
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactionList;
    }

    @Override
    public void update(Transaction entity) throws InvalidDataException {
        String sql = "UPDATE Transaction SET symbol = ?, price = ?, amount = ?, type = ? WHERE idTransaction = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, entity.getSymbol());
            stmt.setDouble(2, entity.getPrice());
            stmt.setDouble(3, entity.getAmount());
            stmt.setString(4, entity.getType());
            stmt.setInt(5, entity.getIdTransaction());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Transaction entity) {
        String sql = "DELETE FROM Transaction WHERE idTransaction = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdTransaction());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSize() {
        String sql = "SELECT COUNT(*) AS count FROM Transaction";
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

    private Transaction extractTransactionFromResultSet(ResultSet rs) throws SQLException {
        return new Transaction(
                rs.getInt("idTransaction"),
                rs.getString("symbol"),
                rs.getDouble("price"),
                rs.getDouble("amount"),
                rs.getString("type")
        );
    }
}
