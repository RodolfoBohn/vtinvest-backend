package br.com.vtinvest.vtinvest.service.acoes;

import br.com.vtinvest.vtinvest.representation.acoes.AcaoResponse;

import java.util.List;

public interface ListarAcoesService {

    List<AcaoResponse> listarAcoes();
}
