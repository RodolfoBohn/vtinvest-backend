package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Movimentacao;
import br.com.vtinvest.vtinvest.mapper.MovimentacaoMapper;
import br.com.vtinvest.vtinvest.repository.MovimentacaoRepository;
import br.com.vtinvest.vtinvest.representation.carteira.HistoricoMovimentacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarHistoricoMovimentacaoServiceImpl implements  ListarHistoricoMovimentacaoService{

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    MovimentacaoMapper movimentacaoMapper;

    @Override
    public List<HistoricoMovimentacaoResponse> listarHistoricoMovimentacao(Integer idCarteira) {

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByCarteiraId(idCarteira);

        return movimentacaoMapper.toResponse(movimentacoes);
    }
}
