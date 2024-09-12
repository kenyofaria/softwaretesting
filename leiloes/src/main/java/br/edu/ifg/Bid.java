package br.edu.ifg;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid {
    private User usuario;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Bid() {
    }

    public Bid(User usuario, BigDecimal valor, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public User getUsuario() {
        return usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
