package cl.duoc.jv0101.caso04.prestamos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.duoc.jv0101.caso04.prestamos.model.Prestamo;
import cl.duoc.jv0101.caso04.prestamos.repository.PrestamoRepository;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;

    public PrestamoService(PrestamoRepository repository) {
        this.repository = repository;
    }

    public List<Prestamo> findAll() {
        return repository.findAll();
    }

    public Optional<Prestamo> findById(Long id) {
        return repository.findById(id);
    }

    public Prestamo create(Prestamo recurso) {
        return repository.save(recurso);
    }

    public Optional<Prestamo> update(Long id, Prestamo datos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setCliente(datos.getCliente());
            existente.setMonto(datos.getMonto());
            return repository.save(existente);
        });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(existente -> {
            repository.delete(existente);
            return true;
        }).orElse(false);
    }
}
