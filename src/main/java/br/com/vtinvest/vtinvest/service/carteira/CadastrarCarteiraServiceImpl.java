package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.acao.Acao;
import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.exception.RegraDeNegocioException;
import br.com.vtinvest.vtinvest.mapper.CarteiraMapper;
import br.com.vtinvest.vtinvest.repository.AcaoCarteiraRepository;
import br.com.vtinvest.vtinvest.repository.AcaoRepository;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import br.com.vtinvest.vtinvest.repository.ClienteRepository;
import br.com.vtinvest.vtinvest.representation.carteira.CadastrarAcaoCarteiraRequest;
import br.com.vtinvest.vtinvest.representation.carteira.CadastrarCarteiraRequest;
import br.com.vtinvest.vtinvest.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarCarteiraServiceImpl implements CadastrarCarteiraService{

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    AcaoCarteiraRepository acaoCarteiraRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarteiraMapper carteiraMapper;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    public String cadastrarCarteira(CadastrarCarteiraRequest cadastrarCarteiraRequest) {

        String login = customUserDetailService.get();

        Cliente cliente = clienteRepository.findByLogin(login);

        if (cliente == null) {
            throw new CadastroNaoEncontradoException("Cliente não está cadastrado");
        }

        Carteira carteira = carteiraMapper.toDomain(cadastrarCarteiraRequest, cliente);

        if(carteiraRepository.existsByNomeIgnoreCaseAndClienteIdAndIsAtivoTrue(carteira.getNome(), carteira.getCliente().getId())) {
            throw new RegraDeNegocioException("Esta carteira já existe para este usuário");
        }

        if(cadastrarCarteiraRequest.getAcoes() != null && cadastrarCarteiraRequest.getAcoes().size() != 0) {
            Double contador = 0.0;
            for(CadastrarAcaoCarteiraRequest acaoAdicionada : cadastrarCarteiraRequest.getAcoes()) {
                Acao acao = acaoRepository.findById(acaoAdicionada.getId()).orElse(null);
                if(acao == null) {
                    throw new CadastroNaoEncontradoException("Ação não está cadastrada");
                }
                contador += acaoAdicionada.getObjetivo();
            }

            if(contador !=100.0) {
                throw new RegraDeNegocioException("A soma dos objetivos deve ser de 100%");
            }

            carteira.setAtivo(true);
            carteiraRepository.save(carteira);

            for(CadastrarAcaoCarteiraRequest acaoAdicionada : cadastrarCarteiraRequest.getAcoes()) {
                Acao acao = acaoRepository.findById(acaoAdicionada.getId()).orElse(null);
                AcaoCarteira acaoCarteira = new AcaoCarteira();
                acaoCarteira.setAcao(acao);
                acaoCarteira.setAtivo(true);
                acaoCarteira.setQuantidadeAtual(acaoAdicionada.getQuantidade() == null ? 0 : acaoAdicionada.getQuantidade());
                acaoCarteira.setObjetivo(acaoAdicionada.getObjetivo());
                acaoCarteira.setCarteira(carteira);

                acaoCarteiraRepository.save(acaoCarteira);

            }
        } else {

            carteira.setAtivo(true);
            carteiraRepository.save(carteira);
        }

        return "Carteira cadastrada com sucesso!";
    }
}
