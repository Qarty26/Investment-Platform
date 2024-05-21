package Service;
import Exceptions.InvalidDataException;
import Model.DataStoring.Wallet;
import Model.Platforms.Exchange;
import Model.User.Account;
import Model.User.User;
import Persistence.*;

import java.io.IOException;
import java.util.Scanner;

public class UserExchangeService {

    public UserRepository userRepository;
    public ExchangeRepository exchangeRepository;
    public WalletRepository walletRepository;
    public UserExchangeService(UserRepository userRepository, ExchangeRepository exchangeRepository, WalletRepository walletRepository)
    {
        this.userRepository = userRepository;
        this.exchangeRepository = exchangeRepository;
        this.walletRepository = walletRepository;
    }


    public void simulation() throws InvalidDataException, IOException {


        if(userRepository.getSize() == 0 || exchangeRepository.getSize()==0)
        {
            System.out.println("There is no user or exchange, please consider enabling the seeder");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("###################################");
        System.out.println("Welcome to Simulation Mode");
        System.out.println(" ");


        System.out.println("Select the index of an existing user");
        for(var x: userRepository.getAll())
            System.out.println(x.getIdUser() + " " + x.getNickName());

        int index = scanner.nextInt();
        User user = userRepository.get(index);

        System.out.println("Let's create an account, please select an exchange");
        for(var x : exchangeRepository.getAll())
            System.out.println(x.getIdExchange() + " " + x.getName());


        index = scanner.nextInt();
        Exchange exchange = exchangeRepository.get(index);

        Account account = new Account(exchange);
        user.addAccount(account);
        System.out.println("Account created successfully!");

        System.out.println("Before we start, please add some money to your personal wallet, it's your last chance!");
        double cash = scanner.nextDouble();
        user.updateBalance(cash);
        System.out.println("Balance updated successfully!");

        System.out.println(" ");
        System.out.println("Now, let's move some money to the exchange, you have " + user.getBalance());
        cash = scanner.nextDouble();

        String exchangeName = exchange.getName();
        user.trasferBalanceToExchange(cash,exchangeName);
        System.out.println("All good, now let's play with some crypto");

        double initialBalance = account.getWallet().getBalance();
        boolean inMenu = true;
        while(inMenu)
        {
            walletRepository.printFunctions();
            System.out.printf("Enter option: ");
            int choice = scanner.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter the coin you want to trade");
                    scanner.nextLine();
                    String pair = scanner.nextLine();
                    
                    System.out.println("Do you want to BUY or SELL?");
                    String type = scanner.nextLine();
                    System.out.println("What is the size of your trade? You have " + account.getWallet().getBalance());
                    double size = scanner.nextDouble();
                    boolean success = account.getWallet().Trade(pair,type,size);
                    if(success)
                        System.out.println("Trade successful!");
                    else
                    {
                        System.out.println("Please provide the right info!");
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the coin you want to move to earn");
                    scanner.nextLine();
                    String pair = scanner.nextLine();
                    System.out.println("Enter the amount of cryptocurrency you want to move");
                    double size = scanner.nextDouble();
                    boolean success = account.getWallet().spotToEarn(pair,size);
                    if(success)
                        System.out.println("Move successful!");
                    else
                    {
                        System.out.println("Please provide the right info!");
                    }

                    break;
                }
                case 3:
                {
                    System.out.println("Enter the coin you want to move to spot");
                    scanner.nextLine();
                    String pair = scanner.nextLine();
                    System.out.println("Enter the amount of cryptocurrency you want to move");
                    double size = scanner.nextDouble();
                    double reward = (double) 10 /100 * size;
                    boolean success = account.getWallet().earnToSpot(pair,size);

                    if(success)
                        System.out.println("Move successful!");
                    else
                    {
                        System.out.println("Please provide the right info!");
                        break;
                    }

                    pair = pair.toUpperCase();
                    account.getWallet().addToSpot(pair, reward);
                    System.out.println("Reward granted");

                    break;
                }
                case 4:
                {
                    account.getWallet().printSubWallet(account.getWallet().getSpot());
                    break;
                }
                case 5:
                {
                    account.getWallet().printSubWallet(account.getWallet().getEarn());
                    break;
                }
                case 6:
                {
                    account.getWallet().printTransactions();
                    break;

                }
                case 7:
                {
                    System.out.println(account.getWallet().getBalance());
                    break;
                }
                case 8:
                {
                    double value = account.getWallet().getTotalValue() + account.getWallet().getBalance();
                    double profit = value - initialBalance;
                    System.out.println("Your unrealised profit: " + profit);
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


}
