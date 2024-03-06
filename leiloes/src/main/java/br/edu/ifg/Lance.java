package br.edu.ifg;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Lance {
    private Usuario usuario;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Lance() {
    }

    public Lance(Usuario usuario, BigDecimal valor, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
