package br.edu.ifg;

import br.edu.ifg.repository.DataBase;
import br.edu.ifg.repository.UserRepository;
import br.edu.ifg.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void deveCadastrarUmNovoUsuario() {
        User user = new User("Kenyo Faria", "kfaria", "senhaimensa");
        User createdUser = subject.save(user);
        usersToBeDeleted.add(createdUser.getId());
        Assertions.assertNotNull(createdUser);
        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(user.getName(), createdUser.getName());
        Assertions.assertEquals(user.getLogin(), createdUser.getLogin());
    }

    @Test
    public void deveObterUmUsuario() {
        User usuario = new User("Kenyo Faria", "kfaria", "senhaimensa");
        User usuarioCriado = subject.save(usuario);
        usersToBeDeleted.add(usuarioCriado.getId());
        User usuarioExistente = subject.get(usuarioCriado.getId());
        Assertions.assertNotNull(usuarioExistente);
        Assertions.assertEquals(usuario.getName(), usuarioExistente.getName());
        Assertions.assertEquals(usuario.getLogin(), usuarioExistente.getLogin());
    }

    @Test
    public void deveObterListaUsuarios() {
        User kenyo = new User("Kenyo Faria", "kfaria", "senhaimensa");
        User bruno = new User("Bruno", "kfaria", "senhaimensa");
        User eduardo = new User("Eduardo", "kfaria", "senhaimensa");

        User kenyoCreated = subject.save(kenyo);
        User brunoCreated = subject.save(bruno);
        User eduardoCreated = subject.save(eduardo);
        usersToBeDeleted.addAll(Arrays.asList(kenyoCreated.getId(), brunoCreated.getId(), eduardoCreated.getId()));

        List<User> usuariosCadastrados = subject.list();

        Assertions.assertEquals(3, usuariosCadastrados.size());
    }

    @AfterEach
    public void tearDown() {
        usersToBeDeleted.forEach(u -> {
            subject.delete(u);
        });
    }
}
