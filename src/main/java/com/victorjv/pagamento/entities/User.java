package com.victorjv.pagamento.entities;

import com.victorjv.pagamento.entities.enums.TYPE;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_carteira")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String document;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private Double balance = 0.0;
    @Column(nullable = false)
    private Integer type;


    public User(){
    }

    public User(Long id, String name, String document, String email, String password, Double balance, TYPE cod) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.balance = balance;
        setTypePrivate(cod);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getType() {
        return type;
    }

    public void setType(int type){
        this.type = type;
    }


    private void setTypePrivate(TYPE type) {
        if (type != null){
            this.type = type.getCod();
        }
    }
    
    public void debit(Double amount){
        
        if (balance < amount){
            throw new RuntimeException("Saldo insuficiente");
        }
        this.balance -= amount;

    }

    public void credit(Double amount){
        this.balance += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
