package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.acao.Acao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends CrudRepository<Acao,Integer> {
}
