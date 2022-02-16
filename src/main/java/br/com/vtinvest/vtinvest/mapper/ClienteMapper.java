package br.com.vtinvest.vtinvest.mapper;

import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import br.com.vtinvest.vtinvest.representation.cliente.CadastrarClienteRequest;

public interface ClienteMapper {
    Cliente toDomain(CadastrarClienteRequest cadastrarClienteRequest);
}
