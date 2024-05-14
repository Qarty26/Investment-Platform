package Persistence;

import Exceptions.InvalidDataException;
import Model.DataStoring.Wallet;
import Model.Platforms.Exchange;
import Model.User.Account;
import Service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountRepository {

    private final DatabaseConnection db;

    public AccountRepository(DatabaseConnection db) {
        this.db = db;
    }

    public void add(Account account, int idUser) {
        String sql = "INSERT INTO Account (idAccount, idUser, idExchange, idWallet) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, account.getIdAccount());
            stmt.setInt(2, idUser); // Assuming idUser is part of Wallet
            stmt.setInt(3, account.getExchange().getIdExchange());
            stmt.setInt(4, account.getWallet().getIdWallet());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving Account to database", e);
        }
    }

    // Method to retrieve an Account by ID
    public Account getById(int accountId) {
        String sql = "SELECT idAccount, idExchange, idWallet FROM Account WHERE idAccount = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idAccount = rs.getInt("idAccount");
                int idExchange = rs.getInt("idExchange");
                int idWallet = rs.getInt("idWallet");

                // Fetch associated Wallet and Exchange from database
                WalletRepository walletRepository = new WalletRepository(db);
                Wallet wallet = walletRepository.getWallet(idWallet);

                ExchangeRepository exchangeRepository = new ExchangeRepository(db);
                Exchange exchange = exchangeRepository.get(idExchange);

                return new Account(idAccount, wallet, exchange);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching Account by ID", e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Account account) {
        String sql = "UPDATE Account SET idExchange = ?, idWallet = ? WHERE idAccount = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, account.getExchange().getIdExchange());
            stmt.setInt(2, account.getWallet().getIdWallet());
            stmt.setInt(3, account.getIdAccount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Account in database", e);
        }
    }

    // Method to delete an Account from the database by ID
    public void delete(int accountId) {
        String sql = "DELETE FROM Account WHERE idAccount = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Account from database", e);
        }
    }
}
