package com.victorjv.pagamento.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Carteira pagador;
    @ManyToOne
    private Carteira recebedor;
    private Double valor;
    private LocalDateTime momento;


    public Transacao() {
    }

    public Transacao(Long id, Carteira pagador, Carteira recebedor, Double valor, LocalDateTime momento) {
        this.id = id;
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.valor = valor;
        this.momento = momento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    public Carteira getPagador() {
        return pagador;
    }

    public void setPagador(Carteira pagador) {
        this.pagador = pagador;
    }

    public Carteira getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Carteira recebedor) {
        this.recebedor = recebedor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao transacao)) return false;
        return Objects.equals(id, transacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
