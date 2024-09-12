package br.edu.ifg;

/**
 * As the goal of the project is teaching undergraduate students to automate tests.
 * So, there were no concerns with some aspects related to the O.O
 */
public class User {
    private Long id;
    private String name;
    private String pass;
    private String login;

    public User(Long id, String name, String login, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.login = login;
    }

    public User(String name, String login, String pass) {
        this.name = name;
        this.pass = pass;
        this.login = login;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((User) obj).id) ;
    }
}
