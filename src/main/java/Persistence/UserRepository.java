package Persistence;

import Exceptions.InvalidDataException;
import Model.User.User;
import Service.Audit;
import Service.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserRepository implements GenericRepository<User> {

    private final Vector<User> users = new Vector<>();
    private final DatabaseConnection db;
    private final Audit audit;

    public UserRepository(DatabaseConnection db, Audit audit) {
        this.db = db;
        this.audit = audit;
    }


    public ArrayList<User> getAll_old() {
        ArrayList<User> allUsers = new ArrayList<>(users);
        return allUsers;
    }


    public void seeder()
    {
        User user1 = new User(1,"Mincu Adrian", "skpha", "skpha13@gmail.com", 2000.0);
        User user2 = new User(2,"Dogaru Mihai", "matoka", "matoka26@gmail.com", 1500.0);
        User user3 = new User(3,"Mircea Razvan", "qarty", "qarty99@gmail.com", 5000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public void erase()
    {
        users.clear();
    }



    public void add_old(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("Cannot add null user.");
        }
        users.add(user);
    }
    public User get_old(int index) throws InvalidDataException {
        if (index < 0 || index >= users.size()) {
            throw new InvalidDataException("Invalid index for getting user.");
        }
        return users.get(index);
    }


    public void update_old(User user) throws InvalidDataException {
        if (user == null) {
            throw new InvalidDataException("Cannot update null user.");
        }
        int index = users.indexOf(user);
        if (index == -1) {
            throw new InvalidDataException("User not found for update.");
        }
        users.set(index, user);
    }

    public void delete_old(User user) throws InvalidDataException {
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


    @Override
    public void add(User user) {
        String sql = "INSERT INTO Userr (idUser, name, nickname, email, balance) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, user.getIdUser());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getNickName());
            stmt.setString(4, user.getEmail());
            stmt.setDouble(5, user.getBalance());
            stmt.executeUpdate();
            audit.logOperation("add");
        } catch (SQLException e) {
            throw new RuntimeException("Error saving User to database", e);
        }
    }

    // Method to retrieve a User by ID
    @Override
    public User get(int userId) {
        String sql = "SELECT idUser, name, nickname, email, balance FROM Userr WHERE idUser = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idUser = rs.getInt("idUser");
                String name = rs.getString("name");
                String nickName = rs.getString("nickName");
                String email = rs.getString("email");
                Double balance = rs.getDouble("balance");
                audit.logOperation("get");
                return new User(idUser, name, nickName, email, balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching User by ID", e);
        }
        return null;
    }

    // Method to update an existing User in the database
    public void update(User user) {
        String sql = "UPDATE Userr SET name = ?, nickname = ?, email = ?, balance = ? WHERE idUser = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getNickName());
            stmt.setString(3, user.getEmail());
            stmt.setDouble(4, user.getBalance());
            stmt.setInt(5, user.getIdUser());
            stmt.executeUpdate();
            audit.logOperation("update");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating User in database", e);
        }
    }

    // Method to delete a User from the database by ID
    @Override
    public void delete(User entity) {
        String sql = "DELETE FROM Userr WHERE idUser = ?";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            stmt.setInt(1, entity.getIdUser());
            stmt.executeUpdate();
            audit.logOperation("delete");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting User from database", e);
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT idUser, name, nickname, email, balance FROM Userr";
        try {
            PreparedStatement stmt = db.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String name = rs.getString("name");
                String nickName = rs.getString("nickname");
                String email = rs.getString("email");
                Double balance = rs.getDouble("balance");
                User user = new User(idUser, name, nickName, email, balance);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching Users from database", e);
        }
        audit.logOperation("get");
        return users;
    }
}