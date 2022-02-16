package br.com.vtinvest.vtinvest.service.carteira;

import br.com.vtinvest.vtinvest.domain.Carteira;
import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.exception.CadastroNaoEncontradoException;
import br.com.vtinvest.vtinvest.mapper.CarteiraMapper;
import br.com.vtinvest.vtinvest.repository.CarteiraRepository;
import br.com.vtinvest.vtinvest.repository.ClienteRepository;
import br.com.vtinvest.vtinvest.representation.carteira.ListarCarteiraResponse;
import br.com.vtinvest.vtinvest.security.UserDetailsImpl;
import br.com.vtinvest.vtinvest.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCarteirasServiceImpl implements ListarCarteirasService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CarteiraMapper carteiraMapper;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    public List<ListarCarteiraResponse> listarCarteiras() {

        String login = customUserDetailService.get();

        Cliente cliente = clienteRepository.findByLogin(login);

        if (cliente == null) {
            throw new CadastroNaoEncontradoException("Cliente não está cadastrado");
        }

        return carteiraMapper.toList(cliente.getCarteiras());
    }
}
