package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.repository.AcaoCarteiraRepository;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirCarteiraServiceImpl implements ExcluirCarteiraService{

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    AcaoCarteiraRepository acaoCarteiraRepository;

    @Override
    public String excluirCarteira(Integer idCarteira) {
        Carteira carteira = carteiraRepository.findById(idCarteira).orElse(null);

        if(carteira == null) {
            throw new CadastroNaoEncontradoException("Carteira não cadastrada");
        }

        for(AcaoCarteira acaoCarteira : carteira.getAcoesCompradas()) {
            if(acaoCarteira.getQuantidadeAtual() > 0) {
                throw new RegraDeNegocioException("Não é possível excluir carteira que contenha um valor investido");
            }
        }

        for(AcaoCarteira acaoCarteira:carteira.getAcoesCompradas()) {
                acaoCarteira.setAtivo(false);
                acaoCarteira.setObjetivo(0.0);
            acaoCarteiraRepository.save(acaoCarteira);
        }

        carteira.setAtivo(false);
        carteiraRepository.save(carteira);

        return "Carteira excluída com sucesso";
    }
}
