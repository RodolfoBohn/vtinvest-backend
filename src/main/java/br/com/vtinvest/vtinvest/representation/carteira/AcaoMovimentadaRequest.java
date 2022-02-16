package br.com.vtinvest.vtinvest.representation.carteira;

import br.com.vtinvest.vtinvest.domain.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AcaoMovimentadaRequest {

    @NotNull(message = "O id da ação da é obrigatório")
    private Integer idAcaoCarteira;

    @Valid
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "A quantidade de ações é obrigatória")
    private Integer quantidadeAcoes;
}
