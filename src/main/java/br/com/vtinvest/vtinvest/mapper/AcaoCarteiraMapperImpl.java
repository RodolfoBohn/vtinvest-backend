package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraResponse;
import org.springframework.stereotype.Service;

@Service
public class AcaoCarteiraMapperImpl implements AcaoCarteiraMapper{
    @Override
    public AcaoCarteiraResponse toResponse(AcaoCarteira acaoCarteira) {
        AcaoCarteiraResponse response = new AcaoCarteiraResponse();

        response.setId(acaoCarteira.getId());
        response.setObjetivo(acaoCarteira.getObjetivo());
        response.setIdAtivo(acaoCarteira.getAcao().getId());
        response.setNome(acaoCarteira.getAcao().getCodigo());
        response.setCotacaoAtualAtivo(acaoCarteira.getAcao().getCotacao());
        response.setQuantidade(acaoCarteira.getQuantidadeAtual());
        response.setAcaoAtiva(acaoCarteira.isAtivo());
        response.setSetor(acaoCarteira.getAcao().getSegmento().getSubsetor().getSetor().getDescricao());
        response.setSubsetor(acaoCarteira.getAcao().getSegmento().getSubsetor().getDescricao());
        response.setSegmento(acaoCarteira.getAcao().getSegmento().getDescricao());
        response.setDistanciaObjetivo(null); //calculado
        response.setParticipacaoAtual(null); // calculado
        response.setPatrimonioAtualizado(null); // calculado

        return response;
    }
}
