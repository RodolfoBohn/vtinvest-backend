package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.Movimentacao;
import br.com.vtinvest.vtinvest.representation.carteira.HistoricoMovimentacaoResponse;

import java.util.List;

public interface MovimentacaoMapper {

    List<HistoricoMovimentacaoResponse> toResponse(List<Movimentacao> movimentacaoList);
}
