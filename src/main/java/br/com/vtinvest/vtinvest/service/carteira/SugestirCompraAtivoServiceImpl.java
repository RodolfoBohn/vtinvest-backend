package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.mapper.CarteiraMapper;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraSugestaoCompra;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoCarteiraResponse;
import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraResponse;
import br.com.vtinvest.vtinvest.representation.carteira.DetalhesCarteiraSugestaoCompraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SugestirCompraAtivoServiceImpl implements SugestirCompraAtivoService {

    @Autowired
    DetalharCarteiraService detalharCarteiraService;

    @Autowired
    CarteiraMapper carteiraMapper;

    @Override
    public DetalhesCarteiraSugestaoCompraResponse gerarSugestaoCompra(Integer idCarteira, Double valor) {

        if(valor<0) {
            throw new RegraDeNegocioException("O valor para sugestão de aporte não pode ser negativo");
        }

        Double contadorPercentualAcoesNegativas = 0.0;
        List<AcaoCarteiraSugestaoCompra> acoesComSugestaoInterno = new ArrayList<>();


        DetalhesCarteiraResponse response = detalharCarteiraService.detalharCarteira(idCarteira);

        //soma o valor total após o investimento
        Double totalInvestidoEValor = response.getTotalInvestido() + valor;

        //Calcula o percentual futuro e a distancia do objetivo futuro de todas as ações
        //Se o calor for negativo, adiciona no contador
        for(AcaoCarteiraResponse acaoCarteiraResponse : response.getAcoes()) {
            AcaoCarteiraSugestaoCompra sugestao = new AcaoCarteiraSugestaoCompra();

            //copia o que é de relevante da acao pra sugestao
            sugestao.setId(acaoCarteiraResponse.getId());
            sugestao.setIdAtivo(acaoCarteiraResponse.getIdAtivo());
            sugestao.setNome(acaoCarteiraResponse.getNome());
            sugestao.setCotacaoAtualAtivo(acaoCarteiraResponse.getCotacaoAtualAtivo());
            sugestao.setQuantidade(acaoCarteiraResponse.getQuantidade());
            sugestao.setPatrimonioAtualizado(acaoCarteiraResponse.getPatrimonioAtualizado());
            sugestao.setParticipacaoAtual(acaoCarteiraResponse.getParticipacaoAtual());
            sugestao.setObjetivo(acaoCarteiraResponse.getObjetivo());
            sugestao.setDistanciaObjetivo(acaoCarteiraResponse.getDistanciaObjetivo());
            sugestao.setAcaoAtiva(acaoCarteiraResponse.isAcaoAtiva());
            sugestao.setSegmento(acaoCarteiraResponse.getSegmento());
            sugestao.setSetor(acaoCarteiraResponse.getSetor());
            sugestao.setSubsetor(acaoCarteiraResponse.getSubsetor());

            //calcula a participação futura daquela ação, e a distancia do objetivo futura
            //se a distancia futura for negativa, adiciona ao contador

            Double participacaoFutura = sugestao.getPatrimonioAtualizado() * 100 / totalInvestidoEValor;
            Double distanciaObjetivoFutura = participacaoFutura - sugestao.getObjetivo();

            if(distanciaObjetivoFutura < 0) {
                contadorPercentualAcoesNegativas += distanciaObjetivoFutura;
                sugestao.setDistanciaObjetivoFutura(distanciaObjetivoFutura);
            } else {
                sugestao.setDistanciaObjetivoFutura(0.0);
            }

            acoesComSugestaoInterno.add(sugestao);
        }

        //após passar pelas ações e calcular o objetivo futuro, passa novamente para verificar as negativas
        //se negativa, calcula o percentual, o valor de compra e a quantidade de compra
        for(AcaoCarteiraSugestaoCompra sugestao : acoesComSugestaoInterno) {
            if(sugestao.getDistanciaObjetivoFutura() < 0) {
                Double percentualDeCompra = sugestao.getDistanciaObjetivoFutura() * 100 / contadorPercentualAcoesNegativas;
                Double valorDeCompra = valor * percentualDeCompra/100;
                Integer quantidadeCompra = (int)(valorDeCompra/sugestao.getCotacaoAtualAtivo());
                sugestao.setQuantidadeCompra(quantidadeCompra);
            } else {
                sugestao.setQuantidadeCompra(0);
            }
        }

        return carteiraMapper.toSugestaoCompra(
                response.getId(),
                response.getNome(),
                response.getIdCliente(),
                response.getTotalInvestido(),
                acoesComSugestaoInterno);
    }
}
