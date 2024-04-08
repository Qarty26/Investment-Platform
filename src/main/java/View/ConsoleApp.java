package View;

import Model.User.User;
import Persistence.AssetRepository;
import Persistence.ExchangeRepository;
import Persistence.UserRepository;
import Persistence.WalletRepository;
import Service.Audit;
import Service.DatabaseConnection;

import javax.xml.crypto.Data;
import java.lang.foreign.AddressLayout;

public class ConsoleApp {

    private static ConsoleApp instance;
    private DatabaseConnection db;
    private Audit audit;
    private AssetRepository assetRepository;
    private ExchangeRepository exchangeRepository;
    private UserRepository userRepository;
    private WalletRepository walletRepository;


    private ConsoleApp(){
        db = DatabaseConnection.getInstance();
        audit = Audit.getInstance();
        assetRepository = new AssetRepository(db);
        exchangeRepository = new ExchangeRepository(db);
        userRepository = new UserRepository(db);
        walletRepository = new WalletRepository(db);
    }

    public static ConsoleApp getInstance() {
        if (instance == null)
            instance = new ConsoleApp();

        return instance;
    }
    

}
