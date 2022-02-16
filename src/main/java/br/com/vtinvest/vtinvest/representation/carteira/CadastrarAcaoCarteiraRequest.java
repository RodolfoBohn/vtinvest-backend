package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CadastrarAcaoCarteiraRequest {

    @NotNull(message = "O id da ação é obrigatório")
    private Integer id;

    private Integer quantidade;

    @NotNull(message = "O objetivo é obrigatório")
    private Double objetivo;

}
