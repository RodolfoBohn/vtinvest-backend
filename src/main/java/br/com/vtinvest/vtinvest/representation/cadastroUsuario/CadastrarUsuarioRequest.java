package br.com.vtinvest.vtinvest.representation.cadastroUsuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CadastrarUsuarioRequest {

    @NotNull(message = "O campo CPF é obrigatório")
    private String Cpf;
}
