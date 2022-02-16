package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CadastrarCarteiraRequest {

    @NotEmpty(message = "O nome da carteira é obrigatório")
    private String nome;

    private List<@Valid CadastrarAcaoCarteiraRequest> acoes;

}
