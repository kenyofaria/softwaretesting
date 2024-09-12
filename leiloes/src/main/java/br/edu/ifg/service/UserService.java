package br.edu.ifg.service;

import br.edu.ifg.User;
import br.edu.ifg.repository.UserRepository;

import javax.annotation.Nonnull;

/**
 * This class provides a bunch of services about users.
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        if (user.getName() == null || user.getName().isEmpty())
            throw new RuntimeException("User name is mandatory");
        if (user.getLogin() == null || user.getLogin().isEmpty())
            throw new RuntimeException("User login is mandatory");
        if (user.getPass() == null || user.getPass().isEmpty())
            throw new RuntimeException("Please provides a password ");
        return repository.save(user);
    }

    public User get(@Nonnull Long id) {
        return repository.fetch(id);
    }
}
