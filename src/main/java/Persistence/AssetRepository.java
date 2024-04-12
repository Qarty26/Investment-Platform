package Persistence;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Service.DatabaseConnection;

import java.util.*;

public class AssetRepository implements GenericRepository<Asset> {

    private final Set<Asset> assets = new TreeSet<>(Comparator.comparing(Asset::getSymbol));
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
        throw new InvalidDataException("Current data set does not support this operation");
    }

    public void erase()
    {
        assets.clear();
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
        if (!assets.remove(asset)) {
            throw new InvalidDataException("Asset not found for update.");
        }
        assets.add(asset);
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