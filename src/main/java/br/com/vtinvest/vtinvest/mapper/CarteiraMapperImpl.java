package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.representation.carteira.*;
import br.com.vtinvest.vtinvest.service.carteira.CalcularDetalhesAcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarteiraMapperImpl implements CarteiraMapper{

    @Autowired
    AcaoCarteiraMapper acaoCarteiraMapper;

    @Autowired
    CalcularDetalhesAcaoService calcularDetalhesAcaoService;

    @Override
    public Carteira toDomain(CadastrarCarteiraRequest cadastrarCarteiraRequest, Cliente cliente) {
        return new Carteira(cadastrarCarteiraRequest.getNome(), cliente);
    }

    @Override
    public DetalhesCarteiraResponse toDetailsResponse(Carteira carteira) {
        DetalhesCarteiraResponse response = new DetalhesCarteiraResponse();
        List<AcaoCarteiraResponse> acoesResponse = new ArrayList<>();
        Double contadorParticipacaoAtual = 0.0;

        response.setId(carteira.getId());
        response.setIdCliente(carteira.getCliente().getId());
        response.setNome(carteira.getNome());



        //adiciona o patrimônio atualizado para todos
        //soma no contador o valor do patrimônio para posterior cálculo da participação atual
        for(AcaoCarteira acaoRecebida : carteira.getAcoesCompradas()) {
            if(acaoRecebida.isAtivo()) {
                AcaoCarteiraResponse acaoResponse = acaoCarteiraMapper.toResponse(acaoRecebida);
                Double patrimonioAtualizado = calcularDetalhesAcaoService.calcularPatrimonioAtualizado(acaoRecebida);
                acaoResponse.setPatrimonioAtualizado(patrimonioAtualizado);
                contadorParticipacaoAtual += patrimonioAtualizado;

                acoesResponse.add(acaoResponse);
            }
        }

        response.setTotalInvestido(contadorParticipacaoAtual);

        //calcula a participação atual e a distância do objetivo
        for(int i = 0; i < acoesResponse.size(); i++) {
            AcaoCarteiraResponse acaoCarteiraResponse = acoesResponse.get(i);

            Double participacaoAtual = calcularDetalhesAcaoService.calcularParticipacaoAtual(
                    acaoCarteiraResponse,
                    contadorParticipacaoAtual
            );
            acaoCarteiraResponse.setParticipacaoAtual(participacaoAtual);

            Double distanciaObjetivo = calcularDetalhesAcaoService.calcularDistanciaObjetivo(
                    acaoCarteiraResponse.getObjetivo(),
                    acaoCarteiraResponse.getParticipacaoAtual()
            );
            acaoCarteiraResponse.setDistanciaObjetivo(distanciaObjetivo);

            acoesResponse.set(i, acaoCarteiraResponse);
        }

        response.setAcoes(acoesResponse);

        return response;
    }

    @Override
    public List<ListarCarteiraResponse> toList(List<Carteira> carteiras) {
        List<ListarCarteiraResponse> response = new ArrayList<>();

        for(Carteira carteira : carteiras) {
            if(carteira.isAtivo()) {
                ListarCarteiraResponse carteiraResponse = new ListarCarteiraResponse();
                carteiraResponse.setId(carteira.getId());
                carteiraResponse.setNome(carteira.getNome());
                response.add(carteiraResponse);
            }
        }

        return response;
    }

    @Override
    public DetalhesCarteiraSugestaoCompraResponse toSugestaoCompra(
            Integer id,
            String nome,
            Integer idCliente,
            Double totalInvestido,
            List<AcaoCarteiraSugestaoCompra> acoes) {

        DetalhesCarteiraSugestaoCompraResponse detalhesResponse = new DetalhesCarteiraSugestaoCompraResponse();

        detalhesResponse.setId(id);
        detalhesResponse.setNome(nome);
        detalhesResponse.setIdCliente(idCliente);
        detalhesResponse.setTotalInvestido(totalInvestido);
        detalhesResponse.setAcoes(acoes);
        detalhesResponse.setPossuiSugestao(true);

        return detalhesResponse;
    }
}
