package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraResponse;

public interface CalcularDetalhesAcaoService {

    Double calcularDistanciaObjetivo(Double objetivo, Double participacaoAtual);

    Double calcularParticipacaoAtual(AcaoCarteiraResponse acaoCarteira, Double totalParticipacaoAtual);

    Double calcularPatrimonioAtualizado(AcaoCarteira acaoCarteira);

}
