package View;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.Exchange;
import Model.User.User;
import Persistence.*;
import Service.Audit;
import Service.DatabaseConnection;

import javax.naming.OperationNotSupportedException;
import javax.xml.crypto.Data;
import java.lang.foreign.AddressLayout;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApp {

    private static ConsoleApp instance;
    private DatabaseConnection db;
    private Audit audit;
    private AssetRepository assetRepository;
    private ExchangeRepository exchangeRepository;
    private UserRepository userRepository;
    private WalletRepository walletRepository;


    private ConsoleApp() {
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

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create Functions");
        System.out.println("2. Read Functions");
        System.out.println("3. Update Functions");
        System.out.println("4. Delete Functions");
        System.out.println("5. Other");
        System.out.println("9. Leave");
        System.out.print("Enter your choice: ");
    }


    public void printCreateFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
    }

    public void createFunctions() throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (true) {
            printCreateFunctions();
            System.out.println("Enter option# ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                {
                    User u = new User();
                    u.read();

                    userRepository.add(u);
                }
                case 2:
                {
                    Exchange e = new Exchange();
                    e.read();

                    exchangeRepository.add(e);
                }
                case 3:
                {
                    Asset a = new Asset();
                    a.read();

                    assetRepository.add(a);
                }
            }
        }
    }

    public void printUpdateFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
    }

    public void updateFunctions() {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (true) {
            printUpdateFunctions();
            System.out.println("Enter option# ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> updateService(userRepository);
                case 2 -> updateService(exchangeRepository);
                case 3 -> updateService(assetRepository);
                case 9 -> {
                    return;
                }
                default -> System.out.println("~ INVALID OPTION");
            }
        }
    }

    public <T extends ReadUpdateInterface> void updateService(GenericRepository<T> repository) {
        try {
            System.out.println(Arrays.toString(repository.getAll().toArray()));
            System.out.println("Enter ID: ");

            Scanner sc = new Scanner(System.in);
            T entity = repository.get(sc.nextInt());
            sc.nextLine();

            entity.update();
            repository.update(entity);

        } catch ( InvalidDataException e) {
            System.out.println(e);
        }
    }

    public void printReadFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
    }





    public void printDeleteFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
    }

    public void deleteFunctions() throws InvalidDataException {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (true) {
            printDeleteFunctions();
            System.out.println("Enter option# ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> deleteService(userRepository);
                case 2 -> deleteService(exchangeRepository);
                case 3 -> deleteService(assetRepository);
                case 0 -> {
                    return;
                }
                default -> System.out.println("~ INVALID OPTION");
            }
        }
    }

    public <T extends ReadUpdateInterface> void deleteService(GenericRepository<T> repository) throws InvalidDataException {

            System.out.println(Arrays.toString(repository.getAll().toArray()));
            System.out.println("Enter ID: ");

            Scanner sc = new Scanner(System.in);
            T entity = repository.get(sc.nextInt());
            sc.nextLine();

            repository.delete(entity);
    }


    public void start() throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);

        Boolean inMenu = true;
        while (inMenu) {
            printMenu();

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Create Functions selected.");
                    createFunctions();
                    break;
                case 2:
                    System.out.println("Read Functions selected.");

                    break;
                case 3:
                    System.out.println("Update Functions selected.");

                    break;
                case 4:
                    System.out.println("Delete Functions selected.");

                    break;
                case 5:
                    System.out.println("Other selected.");
                case 9:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
