package br.edu.ifg;

public class Usuario {
    private Long id;
    private String nome;
    private String senha;
    private String login;

    public Usuario(Long id, String nome, String senha, String login) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Usuario) obj).id) ;
    }
}
