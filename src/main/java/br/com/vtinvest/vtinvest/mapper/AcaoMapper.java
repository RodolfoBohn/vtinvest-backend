package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.representation.acoes.AcaoResponse;

public interface AcaoMapper {

    AcaoResponse toResponse(Acao acao);

}
