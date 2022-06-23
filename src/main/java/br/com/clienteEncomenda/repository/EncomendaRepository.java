package br.com.clienteEncomenda.repository;
import br.com.clienteEncomenda.model.EncomendaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<EncomendaModel, Long> {
    @Query(value= "SELECT * FROM Encomenda ORDER BY id", nativeQuery = true)
    Page<EncomendaModel> findAll(Pageable pageable);
}
