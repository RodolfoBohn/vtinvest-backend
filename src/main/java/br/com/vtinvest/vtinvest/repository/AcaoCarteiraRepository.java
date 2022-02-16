package br.com.vtinvest.vtinvest.repository;

import br.com.vtinvest.vtinvest.domain.acao.AcaoCarteira;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoCarteiraRepository extends CrudRepository<AcaoCarteira, Integer> {

    @Query(value = "select sum(a.valor) from AcaoCarteira ac inner join Acao a where ac.id_acao = a.id where ac.id_carteira = ?1", nativeQuery = true)
    Double somaValoresAcoesDaCarteira(Integer idCarteira);

}
