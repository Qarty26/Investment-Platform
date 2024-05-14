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
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WalletRepository implements GenericRepository<Wallet>{

    private final DatabaseConnection db;
    public AssetRepository assetRepository;

    public WalletRepository(DatabaseConnection db) {
        this.db = db;
        this.assetRepository = new AssetRepository(db);
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
    }

    public Wallet getWallet(int id){
        String sql = "SELECT * FROM Wallet WHERE idWallet = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractWalletFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteWallet(Wallet entity)
    {
        String sql = "DELETE FROM Wallet WHERE idWallet = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdWallet());
            stmt.executeUpdate();


            deleteSubWallet("Spot", entity.getIdWallet());
            deleteSubWallet("Earn", entity.getIdWallet());
            deleteSubWallet("History", entity.getIdWallet());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSubWallet(String subWallet, int idWallet)
    {
        if(!(subWallet == "Spot" || subWallet == "Earn" || subWallet == "History"))
        {
            System.out.println("Unable to delete subWallet");
            return;
        }

        String sql = "DELETE FROM " + subWallet + " WHERE idWallet  = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, idWallet);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Wallet extractWalletFromResultSet(ResultSet rs) throws SQLException {
        return new Wallet(
                rs.getInt("idWallet"),
                rs.getDouble("balance")
        );
    }


    public void updateWallet(Wallet entity) throws InvalidDataException {
        String sql = "UPDATE Wallet SET balance = ? WHERE idWallet = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setDouble(1, entity.getBalance());
            stmt.setInt(2, entity.getIdWallet());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void fetchFromDb() throws SQLException, InvalidDataException {

        try
        {
            String sql = "SELECT w.idWallet, w.balance, s.idSpot, s.idAsset, s.sizee AS spotSize, e.idSpot AS earnIdSpot, e.idAsset AS earnIdAsset, e.sizee AS earnSize " +
                    "FROM Wallet w " +
                    "LEFT JOIN Spot s ON w.idWallet = s.idWallet " +
                    "LEFT JOIN Earn e ON w.idWallet = e.idWallet " +
                    "LEFT JOIN History h ON w.idWallet = h.idWallet";


            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            Map<Integer, Wallet> walletMap = new HashMap<>();

            while (resultSet.next()) {
                int idWallet = resultSet.getInt("idWallet");
                double balance = resultSet.getDouble("balance");

                if (!walletMap.containsKey(idWallet)) {
                    Wallet wallet = new Wallet();
                    wallet.setIdWallet(idWallet);
                    wallet.setBalance(balance);
                    wallet.setSpot(new Vector<>());
                    wallet.setEarn(new Vector<>());
                    wallet.setHistory(new Vector<>());
                    walletMap.put(idWallet, wallet);
                }

                // Process Spot details
                int idSpot = resultSet.getInt("idSpot");
                int spotIdAsset = resultSet.getInt("idAsset");
                double spotSize = resultSet.getDouble("spotSize");

                if (idSpot != 0) {
                    Asset spotAsset = assetRepository.get(spotIdAsset);
                    Pair<Asset, Double> spotEntry = new Pair<>(spotAsset, spotSize);
                    walletMap.get(idWallet).getSpot().add(spotEntry);
                }

                int earnIdSpot = resultSet.getInt("earnIdSpot");
                int earnIdAsset = resultSet.getInt("earnIdAsset");
                double earnSize = resultSet.getDouble("earnSize");

                if (earnIdSpot != 0) {
                    Asset earnAsset = assetRepository.get(earnIdAsset);
                    Pair<Asset, Double> earnEntry = new Pair<>(earnAsset, earnSize);
                    walletMap.get(idWallet).getEarn().add(earnEntry);
                }

                // You can similarly process History details here if needed
            }


            wallets.addAll(walletMap.values());

        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

    }

}








