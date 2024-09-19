package br.edu.ifg.repository;

import br.edu.ifg.User;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class performs user persistence operations.
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class UserRepository {
    private final Connection connection;
    private PreparedStatement ps;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User save(@Nonnull User user) {
        try {
            this.ps = connection.prepareStatement("INSERT INTO usuarios (nome, login, senha) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, user.getName());
            this.ps.setString(2, user.getLogin());
            this.ps.setString(3, user.getPass());
            this.ps.executeUpdate();
            ResultSet rs = this.ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt(1);
            return new User((long) generatedId, user.getName(), user.getLogin(), user.getPass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User fetch(@Nonnull Long id) {
        try {
            this.ps = connection.prepareStatement("SELECT id, nome, login, senha FROM usuarios WHERE id = ?");
            this.ps.setLong(1, id);
            ResultSet resultSet = this.ps.executeQuery();
            User found = null;
            while (resultSet.next()) {
                long usuarioId = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                found = new User(usuarioId, nome, login, senha);
            }
            return found;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try {
            this.ps = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            this.ps.setLong(1, id);
            this.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> list() {
        final List<User> existingUsers = new ArrayList<>();
        try {
            this.ps = connection.prepareStatement("SELECT id, nome, login, senha FROM usuarios");
            ResultSet resultSet = this.ps.executeQuery();
            while (resultSet.next()) {
                long usuarioId = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                existingUsers.add(new User(usuarioId, nome, login, senha));
            }
            return existingUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
