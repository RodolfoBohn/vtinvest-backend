package br.com.vtinvest.vtinvest.domain.pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
@Table(name= "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Integer id;

    @Column(name = "email_primario", unique = true)
    private String emailPrimario;

    @Column(name = "email_secundario", unique = true)
    private String emailSecundario;


    @Column(name = "senha")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "token_usuario")
    private String tokenUsuario;

    @Column(name="cpf")
    private String cpf;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="permissao")
    private String permissao;

    public Pessoa(String emailPrimario, String emailSecundario, String senha, String nome, String sobrenome, String tokenUsuario, String cpf, String login) {
        this.emailPrimario = emailPrimario;
        this.emailSecundario = emailSecundario;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tokenUsuario = tokenUsuario;
        this.cpf = cpf;
        this.login = login;
    }
}
