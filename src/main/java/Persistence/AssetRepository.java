package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Service.Audit;
import Service.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AssetRepository implements GenericRepository<Asset> {

    private final Set<Asset> assets = new TreeSet<>(Comparator.comparing(Asset::getSymbol));
    private final DatabaseConnection db;
    private final Audit audit;



    public AssetRepository(DatabaseConnection db, Audit audit) {
        this.db = db;
        this.audit = audit;
    }


    public void add_old(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot add null asset.");
        }
        assets.add(asset);
    }

    @Override
    public void add(Asset entity){
        String sql = """
                        INSERT INTO Asset (idAsset, name, symbol, issuer, industry, marketCapitalization, price)
                        VALUES (?, ?, ?, ?, ?, ?, ?);
                     """;
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSymbol());
            stmt.setString(4, entity.getIssuer());
            stmt.setString(5, entity.getIndustry());
            stmt.setDouble(6, entity.getMarketCapitalization());
            stmt.setDouble(7, entity.getPricee());
            stmt.execute();
            audit.logOperation("add");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Asset get_old(int index) throws InvalidDataException {
        throw new InvalidDataException("Current data set does not support this operation");
    }


    @Override
    public Asset get(int index) throws InvalidDataException {
        String sql = """
                     SELECT idAsset, name, symbol,issuer,industry,price,marketCapitalization
                     FROM asset
                     WHERE idAsset = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, index);
            ResultSet rs = stmt.executeQuery();
            audit.logOperation("get");

            if (rs.next()) {
                return new Asset(
                        rs.getInt("idAsset"),
                        rs.getString("name"),
                        rs.getString("symbol"),
                        rs.getString("issuer"),
                        rs.getString("industry"),
                        rs.getInt("price"),
                        rs.getDouble("marketCapitalization")
                        );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void erase()
    {
        assets.clear();
    }


    public ArrayList<Asset> getAll_old() {
        ArrayList<Asset> allAssets = new ArrayList<>(assets);
        return allAssets;
    }

    @Override
    public ArrayList<Asset> getAll() {
        ArrayList<Asset> allAssets = new ArrayList<>(assets);
        String sql = """
                     SELECT idAsset, name, symbol,issuer,industry,price,marketCapitalization
                     FROM asset
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Asset a =  new Asset(
                            rs.getInt("idAsset"),
                            rs.getString("name"),
                            rs.getString("symbol"),
                            rs.getString("issuer"),
                            rs.getString("industry"),
                            rs.getInt("price"),
                            rs.getDouble("marketCapitalization")
                );
                allAssets.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        audit.logOperation("get");
        return allAssets;
    }


    public void update_old(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot update null asset.");
        }
        if (!assets.remove(asset)) {
            throw new InvalidDataException("Asset not found for update.");
        }
        assets.add(asset);
    }

    @Override
    public void update(Asset entity) throws InvalidDataException {
        String sql = """
                     UPDATE Asset
                     SET idAsset = ?, name = ?, symbol = ?, issuer = ?, industry = ?, price = ?, marketCapitalization = ?
                     WHERE idAsset = ?
                     """;

        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdAsset());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getSymbol());
            stmt.setString(4, entity.getIssuer());
            stmt.setString(5, entity.getIndustry());
            stmt.setDouble(6,entity.getPrice());
            stmt.setDouble(7, entity.getMarketCapitalization());
            stmt.executeUpdate();
            audit.logOperation("update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete_old(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot delete null asset.");
        }
        if (!assets.remove(asset)) {
            throw new InvalidDataException("Asset not found for deletion.");
        }
    }

    @Override
    public void delete(Asset entity) {
        String sql = """
                     DELETE FROM Asset
                     WHERE idAsset = ?
                     """;
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
        return assets.size();
    }
}