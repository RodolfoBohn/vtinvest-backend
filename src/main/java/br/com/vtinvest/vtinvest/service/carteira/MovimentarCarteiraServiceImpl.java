package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.VtinvestApplication;
import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.Movimentacao;
import br.com.vtinvest.vtinvest.domain.TipoMovimentacao;
import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.repository.AcaoCarteiraRepository;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import br.com.vtinvest.vtinvest.repository.MovimentacaoRepository;
import br.com.vtinvest.vtinvest.representation.carteira.AcaoMovimentadaRequest;
import br.com.vtinvest.vtinvest.representation.carteira.MovimentarCarteiraRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MovimentarCarteiraServiceImpl implements MovimentarCarteiraService{

    Logger logger = LoggerFactory.getLogger(VtinvestApplication.class);

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    AcaoCarteiraRepository acaoCarteiraRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;



    @Override
    public String movimentarCarteira(MovimentarCarteiraRequest movimentarCarteiraRequest) {
        Carteira carteira = carteiraRepository.findById(movimentarCarteiraRequest.getIdCarteira()).orElse(null);
        if(carteira==null) throw new CadastroNaoEncontradoException("Carteira não cadastrada");

        for (AcaoMovimentadaRequest request : movimentarCarteiraRequest.getAcoesMovimentadas()) {
            AcaoCarteira acaoCarteira = acaoCarteiraRepository.findById(request.getIdAcaoCarteira()).orElse(null);

            if(carteira==null) throw new CadastroNaoEncontradoException("Carteira não cadastrada");

            if(acaoCarteira==null) throw new CadastroNaoEncontradoException("Ação não cadastrada em uma carteira");

            if(acaoCarteira.getCarteira().getId() != carteira.getId())
                throw new RegraDeNegocioException("A ação não pertence a esta carteira");

            if(request.getTipoMovimentacao() == TipoMovimentacao.COMPRA) {
                acaoCarteira.setQuantidadeAtual(
                    acaoCarteira.getQuantidadeAtual() +
                            request.getQuantidadeAcoes());
            } else {
                int quantidadeAtual = acaoCarteira.getQuantidadeAtual();
                if(request.getQuantidadeAcoes() > quantidadeAtual)
                throw new RegraDeNegocioException("Não é possível vender mais ações do que as existentes");

            acaoCarteira.setQuantidadeAtual(quantidadeAtual - request.getQuantidadeAcoes());
        }
            Movimentacao movimentacao = new Movimentacao();

            movimentacao.setCarteira(carteira);
            movimentacao.setAcao(acaoCarteira);
            movimentacao.setMovimentacao(request.getTipoMovimentacao());
            movimentacao.setData(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            movimentacao.setQuantidade(request.getQuantidadeAcoes());

            logger.info("Data atual, " + LocalDate.now());

            logger.info("Data que foi salvo, " + Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            movimentacaoRepository.save(movimentacao);
            acaoCarteiraRepository.save(acaoCarteira);
        }

        return "Movimentação registrada com sucesso!";
    }
}
