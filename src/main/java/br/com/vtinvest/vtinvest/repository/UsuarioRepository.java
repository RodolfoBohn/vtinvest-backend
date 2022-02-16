package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.pessoa.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Pessoa, Integer> {

    Pessoa findByLogin(String login);

    Pessoa findByCpf(String cpf);

}
