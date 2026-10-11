package cl.novabank.prestamos.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import cl.novabank.prestamos.model.Prestamo;
import cl.novabank.prestamos.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping("/solicitud")
    public ResponseEntity<Prestamo> solicitar(@Valid @RequestBody Prestamo prestamo) {
        Prestamo creado = service.solicitarPrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<Prestamo>> listarPorCliente(@PathVariable String rut) {
        return ResponseEntity.ok(service.listarPorRutCliente(rut));
    }

    @PutMapping("/{id}/evaluar")
    public ResponseEntity<Prestamo> evaluar(@PathVariable Long id,
            @RequestParam boolean aprobar) {
        return service.evaluarPrestamo(id, aprobar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
