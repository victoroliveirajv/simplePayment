package com.victorjv.pagamento.entities;

import com.victorjv.pagamento.entities.enums.TIPO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome_completo;
    @Column(unique = true)
    private String cpf_cnpj;
    @Column(unique = true)
    private String email;
    private String senha;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Integer tipo;

    public Carteira(){
    }

    public Carteira(Long id, String nome_completo, String cpf_cnpj, String email, String senha, BigDecimal saldo, TIPO cod) {
        this.id = id;
        this.nome_completo = nome_completo;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        setTipo(cod);
    }

    public Long getId() {
        return id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Integer getTipo() {
        return tipo;
    }

    private void setTipo(TIPO tipo) {
        if (tipo != null){
            this.tipo = tipo.getCod();
        }
    }
}
