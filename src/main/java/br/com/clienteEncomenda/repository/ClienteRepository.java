package br.com.clienteEncomenda.repository;
import br.com.clienteEncomenda.model.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    @Query(value = "SELECT * FROM Cliente ORDER BY id", nativeQuery = true)
    Page<ClienteModel> findAll(Pageable pageable);
}
