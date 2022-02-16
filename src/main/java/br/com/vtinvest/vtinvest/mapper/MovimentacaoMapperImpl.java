package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.Movimentacao;
import br.com.vtinvest.vtinvest.representation.carteira.HistoricoMovimentacaoResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovimentacaoMapperImpl implements MovimentacaoMapper{
    @Override
    public List<HistoricoMovimentacaoResponse> toResponse(List<Movimentacao> movimentacaoList) {
        List<HistoricoMovimentacaoResponse> response = new ArrayList<>();

        for(Movimentacao movimentacao : movimentacaoList) {
            HistoricoMovimentacaoResponse historicoResponse = new HistoricoMovimentacaoResponse();
            historicoResponse.setData(movimentacao.getData());
            historicoResponse.setTipoMovimentacao(movimentacao.getMovimentacao());
            historicoResponse.setNomeAcao(movimentacao.getAcao().getAcao().getCodigo());
            historicoResponse.setQuantidade(movimentacao.getQuantidade());

            response.add(historicoResponse);
        }

        return response;
    }
}
