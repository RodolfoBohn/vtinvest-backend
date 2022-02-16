package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.pessoa.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Cliente findByEmailPrimario(String email);

    Cliente findByLogin(String login);

}
