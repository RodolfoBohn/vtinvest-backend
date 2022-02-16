package br.com.vtinvest.vtinvest.representation.carteira;

import br.com.vtinvest.vtinvest.domain.TipoMovimentacao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoricoMovimentacaoResponse {

    private String nomeAcao;

    private Date data;

    private TipoMovimentacao tipoMovimentacao;

    private Integer quantidade;

}
