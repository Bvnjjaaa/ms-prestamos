package cl.novabank.prestamos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.novabank.prestamos.model.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByRutCliente(String rutCliente);

    List<Prestamo> findByEstado(String estado);
}
