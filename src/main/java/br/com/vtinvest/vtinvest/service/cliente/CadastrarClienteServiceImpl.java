package br.com.vtinvest.vtinvest.service.cliente;

import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.exception.UsuarioJaCadastradoException;
import br.com.vtinvest.vtinvest.mapper.ClienteMapper;
import br.com.vtinvest.vtinvest.repository.ClienteRepository;
import br.com.vtinvest.vtinvest.representation.cliente.CadastrarClienteRequest;
import br.com.vtinvest.vtinvest.utils.PasswordEnconder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class CadastrarClienteServiceImpl implements CadastrarClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    private PasswordEnconder enconder = new PasswordEnconder();

    @Override
    public String cadastrarCliente(CadastrarClienteRequest cadastrarClienteRequest) {

        Cliente cliente = clienteMapper.toDomain(cadastrarClienteRequest);

        if(clienteRepository.findByLogin(cliente.getLogin()) != null) throw new UsuarioJaCadastradoException("Login já cadastrado");
        cliente.setPermissao("ROLE_USER");
        cliente.setSenha(enconder.senhaCriptografada(cadastrarClienteRequest.getSenha()));
        clienteRepository.save(cliente);

        return ("Cliente cadastrado com sucesso!");

    }
}
