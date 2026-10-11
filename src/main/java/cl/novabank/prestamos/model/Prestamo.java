package cl.novabank.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    public static final String ESTADO_SOLICITADO = "SOLICITADO";
    public static final String ESTADO_APROBADO = "APROBADO";
    public static final String ESTADO_RECHAZADO = "RECHAZADO";
    public static final String ESTADO_PAGADO = "PAGADO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El RUT del cliente es obligatorio")
    @Column(nullable = false)
    private String rutCliente;

    @NotNull(message = "El monto solicitado es obligatorio")
    @Column(nullable = false)
    private Double montoSolicitado;

    @NotNull(message = "El numero de cuotas es obligatorio")
    @Column(nullable = false)
    private Integer numeroCuotas;

    @NotNull(message = "La tasa de interes es obligatoria")
    @Column(nullable = false)
    private Double tasaInteres;

    @Column
    private Double montoCuota;

    @Column
    private String estado;
}
