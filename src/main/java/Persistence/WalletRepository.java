package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Model.Assets.Transaction;
import Model.DataStoring.Wallet;
import Model.Helpers.Pair;
import Model.Platforms.Exchange;
import Model.User.User;
import Service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class WalletRepository implements GenericRepository<Wallet>{

    private final DatabaseConnection db;

    public WalletRepository(DatabaseConnection db) {
        this.db = db;
    }

    Vector<Wallet> wallets = new Vector<>();

    public void erase()
    {
        wallets.clear();
    }

    public void printFunctions()
    {
        System.out.println("1. Trade");
        System.out.println("2. Move to earn");
        System.out.println("3. Move to spot");
        System.out.println("4. Print spot");
        System.out.println("5. Print earn");
        System.out.println("6. Show transactions");
        System.out.println("7. Show balance");
        System.out.println("8. Check unrealised profit");
        System.out.println("9. Leave");
    }


    @Override
    public void add(Wallet wallet) throws InvalidDataException {
        if (wallet == null) {
            throw new InvalidDataException("Cannot add null wallet.");
        }
        wallets.add(wallet);
    }

    @Override
    public Wallet get(int id) throws InvalidDataException {
        if (id < 0 || id >= wallets.size()) {
            throw new InvalidDataException("Invalid index for getting exchange.");
        }
        return wallets.get(id);
    }

    @Override
    public ArrayList<Wallet> getAll() {
        ArrayList<Wallet> allWallets = new ArrayList<>(wallets);
        return allWallets;
    }

    @Override
    public void update(Wallet wallet) throws InvalidDataException {
        if (wallet == null) {
            throw new InvalidDataException("Cannot update null exchange.");
        }
        int index = wallets.indexOf(wallet);
        if (index == -1) {
            throw new InvalidDataException("Exchange not found for update.");
        }
        wallets.set(index, wallet);
    }

    @Override
    public void delete(Wallet wallet) throws InvalidDataException {
        if (wallet == null) {
            throw new InvalidDataException("Cannot delete null wallet.");
        }
        if (!wallets.remove(wallet)) {
            throw new InvalidDataException("Wallet not found for deletion.");
        }
    }

    @Override
    public int getSize() {
        return wallets.size();
    }


    public void addWallet(Wallet wallet) {
        String insertWalletSql = "INSERT INTO Wallet (idWallet, balance) VALUES (?, ?)";
        try (PreparedStatement stmt = db.connection.prepareStatement(insertWalletSql)) {
            stmt.setInt(1, wallet.getIdWallet());
            stmt.setDouble(2, wallet.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        saveVector(wallet.getSpot(), "Spot", wallet.getIdWallet());
        saveVector(wallet.getEarn(), "Earn", wallet.getIdWallet());
        saveHistory(wallet.getHistory(), wallet.getIdWallet());
    }

    private void saveVector(Vector<Pair<Asset, Double>> vector, String tableName, int walletId) {
        String insertSql = "INSERT INTO " + tableName + " (idAsset, sizee, idWallet) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = db.connection.prepareStatement(insertSql)) {
            for (Pair<Asset, Double> pair : vector) {
                stmt.setInt(1, pair.getFirst().getIdAsset()); // Assuming getIdAsset exists in Asset class
                stmt.setDouble(2, pair.getSecond());
                stmt.setInt(3, walletId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveHistory(Vector<Transaction> history, int walletId) {
        String insertSql = "INSERT INTO History (idTransaction, idWallet) VALUES (?, ?)";
        try (PreparedStatement stmt = db.connection.prepareStatement(insertSql)) {
            for (Transaction transaction : history) {
                stmt.setInt(1, transaction.getIdTransaction());
                stmt.setInt(2, walletId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Wallet getWallet(int walletId) {
        String selectWalletSql = "SELECT * FROM Wallet WHERE idWallet = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(selectWalletSql)) {
            stmt.setInt(1, walletId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Wallet wallet = new Wallet();
                wallet.setIdWallet(rs.getInt("idWallet"));
                wallet.setBalance(rs.getDouble("balance"));
                wallet.setSpot(getVector("Spot", walletId));
                wallet.setEarn(getVector("Earn", walletId));
                wallet.setHistory(getHistory(walletId));
                return wallet;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Vector<Pair<Asset, Double>> getVector(String tableName, int walletId) {
        Vector<Pair<Asset, Double>> vector = new Vector<>();
        String selectSql = "SELECT * FROM " + tableName + " WHERE idWallet = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(selectSql)) {
            stmt.setInt(1, walletId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int assetId = rs.getInt("idAsset");
                double size = rs.getDouble("sizee");
/*                Asset asset = get(assetId);
                if (asset != null) {
                    Pair<Asset, Double> pair = new Pair<>(asset, size);
                    vector.add(pair);
                }*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }

    private Vector<Transaction> getHistory(int walletId) {
        Vector<Transaction> history = new Vector<>();
        String selectSql = "SELECT * FROM History WHERE idWallet = ?";
        try (PreparedStatement stmt = db.connection.prepareStatement(selectSql)) {
            stmt.setInt(1, walletId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int transactionId = rs.getInt("idTransaction");

/*                Transaction transaction = get(transactionId);
                history.add(transaction);*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return history;
    }



}
