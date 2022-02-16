package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.representation.carteira.HistoricoMovimentacaoResponse;

import java.util.List;

public interface ListarHistoricoMovimentacaoService {

    List<HistoricoMovimentacaoResponse> listarHistoricoMovimentacao(Integer idCarteira);
}
