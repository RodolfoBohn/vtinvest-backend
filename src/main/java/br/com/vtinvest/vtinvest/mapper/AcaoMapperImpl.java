package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.representation.acoes.AcaoResponse;
import org.springframework.stereotype.Component;

@Component
public class AcaoMapperImpl implements AcaoMapper{
    @Override
    public AcaoResponse toResponse(Acao acao) {
        AcaoResponse response = new AcaoResponse();

        response.setId(acao.getId());
        response.setNome(acao.getCodigo());

        return response;
    }
}
