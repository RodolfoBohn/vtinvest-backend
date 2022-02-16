package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.representation.cliente.CadastrarClienteRequest;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperImpl implements ClienteMapper{
    @Override
    public Cliente toDomain(CadastrarClienteRequest cadastrarClienteRequest) {
        return new Cliente(
                cadastrarClienteRequest.getEmailPrimario(),
                cadastrarClienteRequest.getEmailSecundario(),
                cadastrarClienteRequest.getSenha(),
                cadastrarClienteRequest.getNome(),
                cadastrarClienteRequest.getSobrenome(),
                cadastrarClienteRequest.getToken(),
                cadastrarClienteRequest.getCpf(),
                cadastrarClienteRequest.getLogin()
        );
    }
}
