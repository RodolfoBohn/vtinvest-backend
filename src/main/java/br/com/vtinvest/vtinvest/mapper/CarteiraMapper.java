package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.representation.carteira.*;

import java.util.List;

public interface CarteiraMapper {

    Carteira toDomain(CadastrarCarteiraRequest cadastrarCarteiraRequest, Cliente cliente);

    DetalhesCarteiraResponse toDetailsResponse(Carteira carteira);

    List<ListarCarteiraResponse> toList(List<Carteira> carteiras);

    DetalhesCarteiraSugestaoCompraResponse toSugestaoCompra(
            Integer id,
            String nome,
            Integer idCliente,
            Double totalInvestido,
            List<AcaoCarteiraSugestaoCompra> acoes);

}
