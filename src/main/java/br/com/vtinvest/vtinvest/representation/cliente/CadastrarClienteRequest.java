package br.com.vtinvest.vtinvest.representation.cliente;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CadastrarClienteRequest {

    @NotEmpty(message = "Campo e-mail primario é obrigatório")
    @Email
    private String emailPrimario;

    @NotEmpty(message = "Campo e-mail secundario é obrigatório")
    @Email
    private String emailSecundario;

    @NotEmpty(message = "Campo senha é obrigatório")
    private String senha;

    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @NotEmpty(message = "Campo sobrenome é obrigatório")
    private String sobrenome;

    @NotEmpty(message = "Campo cpf é obrigatório")
    private String cpf;

    @NotEmpty(message = "Campo rg é obrigatório")
    private String token;

    @NotEmpty(message = "Campo login é obrigatório")
    private String login;

}
