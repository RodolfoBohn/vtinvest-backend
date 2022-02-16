package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.representation.carteira.ListarCarteiraResponse;

import java.util.List;

public interface ListarCarteirasService {

    //to-do: pegar o dado do usuario logado
    List<ListarCarteiraResponse> listarCarteiras();

}
