package View;

import Exceptions.InvalidDataException;
import Model.Assets.Asset;
import Model.Helpers.ReadUpdateInterface;
import Model.Platforms.Exchange;
import Model.User.User;
import Persistence.*;
import Service.Audit;
import Service.DatabaseConnection;
import Service.UserExchangeService;

import java.io.IOException;
import java.sql.SQLException;
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
    private UserExchangeService userExchangeService;


    private ConsoleApp() {
        db = DatabaseConnection.getInstance();
        audit = Audit.getInstance();
        assetRepository = new AssetRepository(db);
        exchangeRepository = new ExchangeRepository(db);
        userRepository = new UserRepository(db);
        walletRepository = new WalletRepository(db);
        userExchangeService = new UserExchangeService(userRepository,exchangeRepository,walletRepository);
    }

    public static ConsoleApp getInstance() throws SQLException, ClassNotFoundException {
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
        System.out.println("5. Other functions");
        System.out.println("9. Leave");
        System.out.print("Enter your choice: ");
    }


    public void printCreateFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
        System.out.println("9. Leave");
    }
    public void createFunctions() throws InvalidDataException
    {
        Scanner sc = new Scanner(System.in);
        int option;
        Boolean inMenu = true;

        while (inMenu) {
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
                    break;
                }
                case 2:
                {
                    Exchange e = new Exchange();
                    e.read();

                    exchangeRepository.add(e);
                    break;
                }
                case 3:
                {
                    Asset a = new Asset();
                    a.read();

                    assetRepository.add(a);
                    break;
                }
                case 9:
                {
                    inMenu = false;
                    break;
                }
                default:
                {
                    System.out.println("Invalid option");
                    break;
                }
            }
        }
    }
    public void printUpdateFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
        System.out.println("9. Leave");
    }
    public void updateFunctions()
    {
        Scanner sc = new Scanner(System.in);
        int option;
        Boolean inMenu = true;

        while (inMenu) {
            printUpdateFunctions();
            System.out.println("Enter option# ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                {
                    updateService(userRepository);
                    break;
                }
                case 2:
                {
                    updateService(exchangeRepository);
                    break;
                }
                case 3:
                {
                    updateService(assetRepository);
                    break;
                }
                case 9:
                {
                    inMenu = false;
                    break;
                }
                default:
                {
                    System.out.println("INVALID OPTION");
                    break;
                }
            }
        }
    }
    public <T extends ReadUpdateInterface> void updateService(GenericRepository<T> repository)
    {
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
        System.out.println("9. Leave");
    }
    public void printDeleteFunctions()
    {
        System.out.println("1. User");
        System.out.println("2. Exchange");
        System.out.println("3. Asset");
        System.out.println("9. Leave");
    }
    public void deleteFunctions() throws InvalidDataException
    {
        Scanner sc = new Scanner(System.in);
        int option;
        Boolean inMenu = true;

        while (inMenu) {
            printDeleteFunctions();
            System.out.println("Enter option");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                {
                    deleteService(userRepository);
                    break;
                }
                case 2:
                {
                    deleteService(exchangeRepository);
                    break;
                }
                case 3:
                {
                    deleteService(assetRepository);
                    break;
                }
                case 9:
                {
                    inMenu = false;
                    break;
                }
                default:
                {
                    System.out.println("INVALID OPTION");
                    break;
                }
            }
        }
    }
    public <T extends ReadUpdateInterface> void deleteService(GenericRepository<T> repository) throws InvalidDataException
    {

            System.out.println(Arrays.toString(repository.getAll().toArray()));
            System.out.println("Enter ID: ");

            Scanner sc = new Scanner(System.in);
            T entity = repository.get(sc.nextInt());
            sc.nextLine();

            repository.delete(entity);
    }
    public void readFunctions()
    {
        Scanner sc = new Scanner(System.in);
        int option;
        Boolean inMenu = true;

        while (inMenu) {
            printReadFunctions();
            System.out.println("Enter option# ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                {
                    System.out.println(Arrays.toString(userRepository.getAll().toArray()));
                    break;
                }
                case 2:
                {
                    System.out.println(Arrays.toString(exchangeRepository.getAll().toArray()));
                    break;
                }
                case 3:
                {
                    System.out.println(Arrays.toString(assetRepository.getAll().toArray()));
                    break;
                }
                case 9:
                {
                    inMenu = false;
                    break;
                }
                default:
                {
                    System.out.println("INVALID OPTION");
                    break;
                }
            }
        }
    }

    public void printOtherFunctions()
    {
        System.out.println("1. Enable seeders");
        System.out.println("2. Erase all data");
        System.out.println("3. Simulation mode");
        System.out.println("9. Leave");
    }


    public void otherFunctions() throws InvalidDataException, IOException {
        Scanner scanner = new Scanner(System.in);

        Boolean inMenu = true;
        while (inMenu)
        {
            printOtherFunctions();
            System.out.printf("Enter option: ");
            int choice = scanner.nextInt();
            switch(choice)
            {
                case 1:
                {
                    userRepository.seeder();
                    exchangeRepository.seeder();
                    break;
                }
                case 2:
                {
                    userRepository.erase();
                    exchangeRepository.erase();
                    assetRepository.erase();
                    walletRepository.erase();
                    break;
                }
                case 3:
                {
                    userExchangeService.simulation();
                    break;
                }
                case 9:
                {
                    inMenu = false;
                    break;
                }
                default:
                {
                    System.out.println("INVALID OPTION");
                    break;
                }
            }
        }
    }

    public void start() throws InvalidDataException, IOException {
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
                    readFunctions();
                    break;
                case 3:
                    System.out.println("Update Functions selected.");
                    updateFunctions();
                    break;
                case 4:
                    System.out.println("Delete Functions selected.");
                    deleteFunctions();
                    break;
                case 5:
                    System.out.println("Other selected.");
                    otherFunctions();
                    break;
                case 9:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
