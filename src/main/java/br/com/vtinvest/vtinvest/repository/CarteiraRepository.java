package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.Carteira;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarteiraRepository extends CrudRepository<Carteira, Integer> {

    List<Carteira> findByClienteId(Integer id);

    boolean existsByNomeIgnoreCaseAndClienteIdAndIsAtivoTrue(String nome, Integer id);
}
