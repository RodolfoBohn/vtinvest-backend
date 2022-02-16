package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraResponse;

public interface AcaoCarteiraMapper {

    AcaoCarteiraResponse toResponse(AcaoCarteira acaoCarteira);

}
