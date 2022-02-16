package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.Movimentacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Integer> {

    List<Movimentacao> findByCarteiraId(Integer idCarteira);
}
