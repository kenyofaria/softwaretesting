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

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User save(@Nonnull User user) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO usuarios (nome, login, senha) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPass());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt(1);
            return new User((long) generatedId, user.getName(), user.getLogin(), user.getPass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User fetch(@Nonnull Long id) {
        //This way the resource preparedStatement has been closed at the final
        try(PreparedStatement ps = connection.prepareStatement("SELECT id, nome, login, senha FROM usuarios WHERE id = ?")){
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            User found = null;
            while (resultSet.next()) {
                found = buildUser(resultSet);
                break;
            }
            return found;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> list() {
        final List<User> existingUsers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, nome, login, senha FROM usuarios")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                existingUsers.add(buildUser(resultSet));
            }
            return existingUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        long userId = rs.getLong("id");
        String name = rs.getString("nome");
        String login = rs.getString("login");
        String password = rs.getString("senha");
        return new User(userId, name, login, password);
    }
}
