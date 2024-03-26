package DataStoring;

import Assets.Asset;
import Assets.Transaction;

import java.util.Vector;

public class Wallet {
    private Vector<Asset> spot;
    private Vector<Asset> earn;
    private Vector<Transaction> history;
}
