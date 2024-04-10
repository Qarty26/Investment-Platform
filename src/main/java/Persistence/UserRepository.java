package Persistence;

import Exceptions.InvalidDataException;
import Model.User.User;
import Service.DatabaseConnection;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class UserRepository implements GenericRepository<User> {

    private final Vector<User> users = new Vector<>();
    private final DatabaseConnection db;

    public UserRepository(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> allUsers = new ArrayList<>(users);
        return allUsers;
    }


    //to be finished
    public void simulation()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the index of an existing user");
        for(int i = 0; i<users.size(); i++)
            System.out.println(i + users.get(i).getNickName());
        System.out.println("");
        int index = scanner.nextInt();
        User user = users.get(index);
    }

    public void seeder()
    {
        User user1 = new User("Mincu Adrian", "skpha", "skpha13@gmail.com", 2000.0);
        User user2 = new User("Dogaru Mihai", "matoka", "matoka26@gmail.com", 1500.0);
        User user3 = new User("Mircea Razvan", "qarty", "qarty99@gmail.com", 5000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public void erase()
    {
        users.clear();
    }


    @Override
    public void add(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("Cannot add null user.");
        }
        users.add(user);
    }

    @Override
    public User get(int index) throws InvalidDataException {
        if (index < 0 || index >= users.size()) {
            throw new InvalidDataException("Invalid index for getting user.");
        }
        return users.get(index);
    }

    @Override
    public void update(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("Cannot update null user.");
        }
        int index = users.indexOf(user);
        if (index == -1) {
            throw new InvalidDataException("User not found for update.");
        }
        users.set(index, user);
    }

    @Override
    public void delete(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("Cannot delete null user.");
        }
        if (!users.remove(user)) {
            throw new InvalidDataException("User not found for deletion.");
        }
    }

    @Override
    public int getSize() {
        return users.size();
    }
}