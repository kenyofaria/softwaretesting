package br.edu.ifg;

import br.edu.ifg.repository.DataBase;
import br.edu.ifg.repository.UserRepository;
import br.edu.ifg.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UserServiceIT {
    private static Connection connection;
    private static UserRepository usuarioRepository;
    private static UserService subject;

    @BeforeAll
    public static void init() {
        connection = DataBase.getConnection();
        usuarioRepository = new UserRepository(connection);
        subject = new UserService(usuarioRepository);
    }

    @Test
    public void deveCadastrarUmNovoUsuario() {
        User usuario = new User("Kenyo Faria", "kfaria", "senhaimensa");
        User usuarioCriado = subject.save(usuario);
        Assertions.assertNotNull(usuarioCriado);
        Assertions.assertNotNull(usuarioCriado.getId());
        Assertions.assertEquals(usuario.getName(), usuarioCriado.getName());
        Assertions.assertEquals(usuario.getLogin(), usuarioCriado.getLogin());
    }

    @Test
    public void deveObterUmUsuario() {
        User usuario = new User("Kenyo Faria", "kfaria", "senhaimensa");
        User usuarioCriado = subject.save(usuario);
        User usuarioExistente = subject.get(usuarioCriado.getId());
        Assertions.assertNotNull(usuarioExistente);
        Assertions.assertEquals(usuario.getName(), usuarioExistente.getName());
        Assertions.assertEquals(usuario.getLogin(), usuarioExistente.getLogin());
    }
}
