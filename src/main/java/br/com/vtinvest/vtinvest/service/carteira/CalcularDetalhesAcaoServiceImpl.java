package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraResponse;
import org.springframework.stereotype.Service;

@Service
public class CalcularDetalhesAcaoServiceImpl implements CalcularDetalhesAcaoService{
    @Override
    public Double calcularDistanciaObjetivo(Double objetivo, Double participacaoAtual) {
        return participacaoAtual - objetivo;
    }

    @Override
    public Double calcularParticipacaoAtual(AcaoCarteiraResponse acaoCarteira, Double totalParticipacaoAtual) {
        if (totalParticipacaoAtual == 0.0) return 0.0;
       return acaoCarteira.getPatrimonioAtualizado() / totalParticipacaoAtual*100;
    }

    @Override
    public Double calcularPatrimonioAtualizado(AcaoCarteira acaoCarteira) {
        return acaoCarteira.getQuantidadeAtual() * acaoCarteira.getAcao().getCotacao();
    }
}
