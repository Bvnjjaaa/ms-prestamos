package cl.duoc.jv0101.caso04.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.jv0101.caso04.prestamos.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
