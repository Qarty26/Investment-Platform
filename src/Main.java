import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Model.Platforms.CryptoExchange;
import Model.Platforms.Exchange;
import Model.Platforms.StockExchange;
import Model.User.User;
import Model.User.Account;
import View.ConsoleApp;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

public class Main {

    private static ConsoleApp consoleApp;


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidDataException, IOException {

        consoleApp = ConsoleApp.getInstance();
        consoleApp.start();








    }
}