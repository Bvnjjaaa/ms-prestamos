package cl.novabank.prestamos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.novabank.prestamos.model.Prestamo;
import cl.novabank.prestamos.repository.PrestamoRepository;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;

    public PrestamoService(PrestamoRepository repository) {
        this.repository = repository;
    }

    public Prestamo solicitarPrestamo(Prestamo prestamo) {
        prestamo.setMontoCuota(calcularMontoCuota(
                prestamo.getMontoSolicitado(),
                prestamo.getNumeroCuotas(),
                prestamo.getTasaInteres()));
        prestamo.setEstado(Prestamo.ESTADO_SOLICITADO);
        return repository.save(prestamo);
    }

    public Optional<Prestamo> evaluarPrestamo(Long id, boolean aprobar) {
        return repository.findById(id).map(prestamo -> {
            prestamo.setEstado(aprobar ? Prestamo.ESTADO_APROBADO : Prestamo.ESTADO_RECHAZADO);
            return repository.save(prestamo);
        });
    }

    public Optional<Prestamo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Prestamo> listarPorRutCliente(String rutCliente) {
        return repository.findByRutCliente(rutCliente);
    }

    public List<Prestamo> listarTodos() {
        return repository.findAll();
    }

    private Double calcularMontoCuota(Double monto, Integer cuotas, Double tasaInteres) {
        if (monto == null || cuotas == null || cuotas <= 0) {
            return 0.0;
        }
        double tasa = tasaInteres == null ? 0.0 : tasaInteres;
        if (tasa == 0.0) {
            return redondear(monto / cuotas);
        }
        double cuota = (monto * tasa) / (1 - Math.pow(1 + tasa, -cuotas));
        return redondear(cuota);
    }

    private Double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
