package br.edu.ifg;

import br.edu.ifg.repository.DataBase;
import br.edu.ifg.repository.UserRepository;
import br.edu.ifg.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceIT {
    private static Connection connection;
    private static UserRepository usuarioRepository;
    private static UserService subject;
    private static final List<Long> usersToBeDeleted = new ArrayList<>();

    @BeforeAll
    public static void init() {
        connection = DataBase.getConnection();
        usuarioRepository = new UserRepository(connection);
        subject = new UserService(usuarioRepository);
    }

    @Test
    public void shouldCreateUser() {
        User user = new User("Kenyo Faria", "kfaria", "strong pass");
        User createdUser = subject.save(user);
        usersToBeDeleted.add(createdUser.getId());
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getLogin(), createdUser.getLogin());
    }

    @Test
    public void shouldFetchUser() {
        User user = new User("Kenyo Faria", "kfaria", "strong pass");
        User createdUser = subject.save(user);
        usersToBeDeleted.add(createdUser.getId());
        User existingUser = subject.get(createdUser.getId());
        assertNotNull(existingUser);
        assertEquals(user.getName(), existingUser.getName());
        assertEquals(user.getLogin(), existingUser.getLogin());
    }

    @Test
    public void shouldListUsers() {
        User kenyo = new User("Kenyo Faria", "kfaria", "strong pass");
        User bruno = new User("Bruno", "bruno", "strong pass");
        User edward = new User("Edward", "edward", "strong pass");

        User kenyoCreated = subject.save(kenyo);
        User brunoCreated = subject.save(bruno);
        User eduardoCreated = subject.save(edward);
        usersToBeDeleted.addAll(Arrays.asList(kenyoCreated.getId(), brunoCreated.getId(), eduardoCreated.getId()));

        List<User> existingUsers = subject.list();

        assertEquals(3, existingUsers.size());
        assertTrue(existingUsers.stream().anyMatch(u -> u.getLogin().equals(kenyoCreated.getLogin())));
        assertTrue(existingUsers.stream().anyMatch(u -> u.getLogin().equals(brunoCreated.getLogin())));
        assertTrue(existingUsers.stream().anyMatch(u -> u.getLogin().equals(eduardoCreated.getLogin())));
    }

    @AfterEach
    public void tearDown() {
        usersToBeDeleted.forEach(u -> {
            subject.delete(u);
        });
    }
}
