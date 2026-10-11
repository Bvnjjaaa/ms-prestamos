# ms-prestamos — Microservicio de Préstamos

Microservicio correspondiente al **caso NovaBank** (Banca digital / fintech) de la **Evaluación Parcial N°2**.

| | |
|---|---|
| **Asignatura** | JVY0101 — Java: Diseño y Construcción de Soluciones Nativas en Nube |
| **Stack** | Spring Boot 3.2.5 · Java 17 · Maven Wrapper · Spring Data JPA · H2 Database |
| **Arquitectura** | Capas (Controller, Service, Repository, Model) |
| **Puerto** | 8084 |

## Responsabilidad (SRP)

Gestiona el ciclo de vida de los préstamos de NovaBank: registra solicitudes calculando la cuota mediante la fórmula de amortización (monto, número de cuotas y tasa de interés), y permite evaluarlas para aprobarlas o rechazarlas, además de consultarlas por cliente. Su base de datos es una **H2 en memoria** (`jdbc:h2:mem:novabank_loans_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`), cumpliendo aislamiento de datos por dominio.

## Consola y Persistencia

Al ejecutar el servicio en el puerto `8084`, se puede acceder a la consola de persistencia H2:

- **H2 Console**: `http://localhost:8084/h2-console`
- **JDBC URL**: `jdbc:h2:mem:novabank_loans_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- **Usuario**: `sa`
- **Contraseña**: *(en blanco)*

## Endpoints Principales (`/api/prestamos`)

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/prestamos/solicitud` | Registra una solicitud validando RUT, monto, número de cuotas y tasa; calcula la cuota y asigna estado SOLICITADO; responde 201. |
| GET | `/api/prestamos` | Lista todos los préstamos registrados. |
| GET | `/api/prestamos/{id}` | Obtiene un préstamo por id; responde 404 si no existe. |
| GET | `/api/prestamos/cliente/{rut}` | Lista todos los préstamos de un cliente según su RUT. |
| PUT | `/api/prestamos/{id}/evaluar` | Evalúa un préstamo (`aprobar=true` o `false`), cambiando su estado a APROBADO o RECHAZADO; responde 404 si no existe. |

## Instrucciones de Clonación, Compilación y Ejecución Local

1. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd ms-prestamos
   ```

2. **Compilar y empaquetar con Maven Wrapper:**
   ```bash
   .\mvnw.cmd clean package -DskipTests
   ```

3. **Ejecutar el ejecutable .jar generado:**
   ```bash
   java -jar target/ms-prestamos-1.0.0.jar
   ```

## Cómo ejecutar las pruebas

```bash
.\mvnw.cmd test
```
