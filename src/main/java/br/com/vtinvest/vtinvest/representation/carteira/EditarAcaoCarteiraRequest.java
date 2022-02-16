package br.com.vtinvest.vtinvest.representation.carteira;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EditarAcaoCarteiraRequest {

    private Integer id;

    @NotNull(message = "O id Ativo da ação é obrigatório")
    private Integer idAtivo;

    private Integer quantidade;

    @NotNull(message = "O objetivo é obrigatório")
    private Double objetivo;

    private boolean acaoAtiva;
}
