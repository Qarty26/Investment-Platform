package Persistence;

import Exceptions.InvalidDataException;
import Model.DataStoring.Wallet;
import Model.Platforms.Exchange;
import Model.User.User;
import Service.DatabaseConnection;

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



}
