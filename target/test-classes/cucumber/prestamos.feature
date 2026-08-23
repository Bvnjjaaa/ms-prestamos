# language: es
Característica: Servicio Préstamos (microservicio prestamos del caso caso04)
  Los escenarios validan el contrato REST del microservicio alineado a sus endpoints.

  Escenario: el listado del recurso responde 200
    Dado el servicio "Préstamos" está disponible
    Cuando consulto el listado de "prestamos"
    Entonces el listado responde con código 200

  Escenario: ciclo de vida completo del recurso
    Dado un nuevo "prestamo" con nombre "hola-cucumber"
    Cuando consulto el "prestamo" recién creado
    Entonces el recurso tiene nombre "hola-cucumber" y código 200
    Cuando actualizo el "prestamo" con nombre "cucumber-actualizado"
    Entonces el recurso queda con nombre "cucumber-actualizado" y código 200
    Cuando elimino el "prestamo"
    Entonces la eliminación responde con código 204
    Y al consultar el "prestamo" eliminado responde 404
