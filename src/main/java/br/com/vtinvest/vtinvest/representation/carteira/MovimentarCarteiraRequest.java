package br.com.vtinvest.vtinvest.representation.carteira;

import br.com.vtinvest.vtinvest.domain.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class MovimentarCarteiraRequest {

    @NotNull(message = "O id da carteira é obrigatório")
    private Integer idCarteira;

    private List<AcaoMovimentadaRequest> acoesMovimentadas;

}
