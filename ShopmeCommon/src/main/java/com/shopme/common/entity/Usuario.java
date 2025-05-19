package com.shopme.common.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String senha;

    @Column(name = "primeiro_nome", length = 45, nullable = false)
    private String primeiroNome;

    @Column(name = "ultimo_nome", length = 45, nullable = false)
    private String ultimoNome;

    @Column(length = 64)
    private String fotos;

    private boolean ativo;

    /**
     *
     */
    @ManyToMany
    @JoinTable(
            name = "usuarios_papel_perfis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_perfil_id"))
    private Set<Papel_Perfil> papelPerfils = new HashSet<>();

    // contrutores
    public Usuario() {}

    public Usuario(String email, String senha, String primeiroNome, String ultimoNome) {
        this.email = email;
        this.senha = senha;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
    }

    // getters e setters

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Papel_Perfil> getPapelPerfils() {
        return papelPerfils;
    }

    public void setPapelPerfils(Set<Papel_Perfil> papelPerfils) {
        this.papelPerfils = papelPerfils;
    }

    public void addPapelPerfil(Papel_Perfil papelPerfil) {
        this.papelPerfils.add(papelPerfil);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", papelPerfils=" + papelPerfils +
                '}';
    }
}
