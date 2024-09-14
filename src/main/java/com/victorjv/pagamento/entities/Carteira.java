package com.victorjv.pagamento.entities;

import com.victorjv.pagamento.entities.enums.TIPO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_carteira")
public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome_completo;
    @Column(unique = true, nullable = false)
    private String documento;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    private Double saldo = 0.0;
    @Column(nullable = false)
    private Integer tipo;


    public Carteira(){
    }

    public Carteira(Long id, String nome_completo, String documento, String email, String senha, Double saldo, TIPO cod) {
        this.id = id;
        this.nome_completo = nome_completo;
        this.documento = documento;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        setTipoPrivate(cod);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(int tipo){
        this.tipo = tipo;
    }


    private void setTipoPrivate(TIPO tipo) {
        if (tipo != null){
            this.tipo = tipo.getCod();
        }
    }
    
    public void debitar(Double valor){
        if (saldo < valor){
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo -= valor;

    }

    public void creditar(Double valor){
        this.saldo += valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carteira carteira)) return false;
        return Objects.equals(id, carteira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
