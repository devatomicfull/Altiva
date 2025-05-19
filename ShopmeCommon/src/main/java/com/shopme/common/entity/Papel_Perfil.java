package com.shopme.common.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "papel_perfis")
public class Papel_Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(length = 40, nullable = false, unique = true)
    private String nome;

    @Column(length = 150, nullable = false)
    private String descricao;

    // contrutores
    public Papel_Perfil() {
    }

    public Papel_Perfil(Integer id) {
        this.id = id;
    }

    public Papel_Perfil(String nome) {
        this.nome = nome;
    }

    public Papel_Perfil(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }


    // getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Papel_Perfil that = (Papel_Perfil) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
