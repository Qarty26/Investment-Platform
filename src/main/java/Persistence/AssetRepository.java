package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Service.DatabaseConnection;

import java.util.ArrayList;
import java.util.Vector;

public class AssetRepository implements GenericRepository<Asset> {

    private final Vector<Asset> assets = new Vector<>();
    private final DatabaseConnection db;

    public AssetRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot add null asset.");
        }
        assets.add(asset);
    }

    @Override
    public Asset get(int index) throws InvalidDataException {
        if (index < 0 || index >= assets.size()) {
            throw new InvalidDataException("Invalid index for getting asset.");
        }
        return assets.get(index);
    }

    @Override
    public ArrayList<Asset> getAll() {
        ArrayList<Asset> allAssets = new ArrayList<>(assets);
        return allAssets;
    }

    @Override
    public void update(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot update null asset.");
        }
        int index = assets.indexOf(asset);
        if (index == -1) {
            throw new InvalidDataException("Asset not found for update.");
        }
        assets.set(index, asset);
    }

    @Override
    public void delete(Asset asset) throws InvalidDataException {
        if (asset == null) {
            throw new InvalidDataException("Cannot delete null asset.");
        }
        if (!assets.remove(asset)) {
            throw new InvalidDataException("Asset not found for deletion.");
        }
    }

    @Override
    public int getSize() {
        return assets.size();
    }
}