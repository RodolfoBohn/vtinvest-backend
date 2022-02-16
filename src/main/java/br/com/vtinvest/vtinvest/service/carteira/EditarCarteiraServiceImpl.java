package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.repository.AcaoCarteiraRepository;
import br.com.vtinvest.vtinvest.repository.AcaoRepository;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import br.com.vtinvest.vtinvest.representation.carteira.EditarAcaoCarteiraRequest;
import br.com.vtinvest.vtinvest.representation.carteira.EditarCarteiraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarCarteiraServiceImpl implements EditarCarteiraService{
    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    AcaoCarteiraRepository acaoCarteiraRepository;

    @Autowired
    AcaoRepository acaoRepository;

    @Override
    public String editarCarteira(EditarCarteiraRequest editarCarteiraRequest) {
        Carteira carteira = carteiraRepository.findById(editarCarteiraRequest.getId()).orElse(null);

        if(carteira == null) throw new CadastroNaoEncontradoException("Carteira nao encontrada");

        if(!carteira.getNome().equalsIgnoreCase(editarCarteiraRequest.getNome())) {
            if(carteiraRepository.existsByNomeIgnoreCaseAndClienteIdAndIsAtivoTrue(editarCarteiraRequest.getNome(), carteira.getCliente().getId())) {
                throw new RegraDeNegocioException("Esta carteira já existe para este usuário");
            }
        }

        carteira.setNome(editarCarteiraRequest.getNome());
        if(editarCarteiraRequest.getAcoes() != null && editarCarteiraRequest.getAcoes().size() != 0) {
            Double contador = 0.0;
            for (EditarAcaoCarteiraRequest acaoAdicionada : editarCarteiraRequest.getAcoes()) {
                if (acaoAdicionada.isAcaoAtiva() == true) {
                    contador += acaoAdicionada.getObjetivo();
                }
            }

            if (contador != 100.0) {
                throw new RegraDeNegocioException("A soma dos objetivos deve ser de 100%");
            }

            for (EditarAcaoCarteiraRequest acaoAdicionada : editarCarteiraRequest.getAcoes()) {

                AcaoCarteira acaoCarteira = acaoAdicionada.getId() == null ? null : acaoCarteiraRepository.findById(acaoAdicionada.getId()).orElse(null);

                if (acaoCarteira == null) {
                    Acao acao = acaoRepository.findById(acaoAdicionada.getIdAtivo()).orElse(null);
                    if(acao == null) {
                        throw new CadastroNaoEncontradoException("Ativo não existe");
                    }
                    AcaoCarteira novaAcaoCarteira = new AcaoCarteira();
                    novaAcaoCarteira.setAcao(acao);
                    novaAcaoCarteira.setAtivo(acaoAdicionada.isAcaoAtiva());
                    novaAcaoCarteira.setQuantidadeAtual(acaoAdicionada.getQuantidade() == null ? 0 : acaoAdicionada.getQuantidade());
                    novaAcaoCarteira.setObjetivo(acaoAdicionada.getObjetivo());
                    novaAcaoCarteira.setCarteira(carteira);

                    acaoCarteiraRepository.save(novaAcaoCarteira);
                } else {
                    if (!acaoAdicionada.isAcaoAtiva()) {
                        acaoCarteira.setAtivo(acaoAdicionada.isAcaoAtiva());
                        acaoCarteira.setObjetivo(0.0);
                    } else {
                        acaoCarteira.setObjetivo(acaoAdicionada.getObjetivo());
                    }
                    acaoCarteiraRepository.save(acaoCarteira);
                }
            }
        }

        carteiraRepository.save(carteira);
        return "Carteira atualizada com sucesso";
    }
}
