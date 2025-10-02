# Equipo-futbol
ARQUITECTURA EN CAPAS

[JSP/HTML] ← Usuario ve esto
↓
[SERVLETS] ← Controla las peticiones HTTP
↓
[SERVICIOS] ← Lógica de negocio
↓
[DAO] ← Acceso a datos
↓
[ENTIDADES JPA] ← Representan las tablas
↓
[BASE DE DATOS] ← MariaDB

ENTIDADES (Capa de Persistencia)
¿Qué son?
Clases Java que representan tablas de la base de datos.

Anotaciones principales:
Anotación           Significado
@Entity             Marca la clase como entidad JPA
@Table(name="...")  Define el nombre de la tabla en BD
@Id                 Marca el campo como clave primaria
@GeneratedValue     Auto-genera el ID (auto-increment)
@Column             Configura la columna (nombre, nullable, longitud)
@OneToMany          Relación 1:N (un equipo → muchos jugadores)
@ManyToOne          Relación N:1 (muchos jugadores → un equipo)
@JoinColumn         Define la columna de la clave foránea
FetchType.LAZY      Carga los datos solo cuando se necesitan
cascade             Propaga operaciones (ej: borrar equipo → borra jugadores)

CAPA DAO (Data Access Object)
¿Qué hacen?
Encapsulan todas las operaciones de base de datos (CRUD: Create, Read, Update, Delete).

¿Por qué GenericDAO?
Para no repetir código. 
Todas las operaciones básicas (crear, actualizar, eliminar, buscar) se heredan.

JPQL (Java Persistence Query Language)
Es como SQL pero usa nombres de clases en lugar de tablas

CAPA DE SERVICIOS
¿Qué hacen?
Contienen la lógica de negocio del sistema.
Validan datos, coordinan múltiples DAOs y aplican reglas.

¿Por qué separar interfaz e implementación?

Flexibilidad: Puedes cambiar la implementación sin tocar el resto del código
Testing: Facilita crear versiones de prueba
Buenas prácticas: Programar contra interfaces, no implementaciones

CAPA DE PRESENTACIÓN - SERVLETS
¿Qué son?
Clases Java que manejan peticiones HTTP (GET, POST) y conectan la web con tu lógica.

@WebServlet                 Registra el servlet en Tomcat
urlPatterns = {"/equipos"}  URL que atiende este servlet

CLASE UTIL - JPAUtil.java
¿Qué hace?
Crea y gestiona el EntityManagerFactory (conexión a BD).

¿Por qué es importante?

EntityManagerFactory es costoso de crear
Se crea una sola vez y se reutiliza
Cada operación de BD necesita un EntityManager nuevo.

// UN equipo tiene MUCHOS jugadores
@OneToMany(mappedBy = "equipo")
private List<Jugadores> jugadores;

// MUCHOS jugadores pertenecen a UN equipo
@ManyToOne
@JoinColumn(name = "id_equipo")
private Equipos equipo;

// LAZY: Carga solo cuando accedes (más eficiente)
@ManyToOne(fetch = FetchType.LAZY)
private Equipos equipo;

// EAGER: Carga siempre (puede ser lento)
@ManyToOne(fetch = FetchType.EAGER)
private Equipos equipo;

// Si borras el equipo, borra sus jugadores
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
private List<Jugadores> jugadores;

1. Usuario llena formulario en crear.jsp
   └─> <form method="post" action="/equipos">

2. Navegador envía POST a EquiposServlet
   └─> doPost() captura el request

3. Servlet llama al servicio
   └─> equiposService.crearEquipo(nombre)

4. Servicio valida y llama al DAO
   └─> equiposDAO.crear(equipo)

5. DAO usa JPA para insertar en BD
   └─> em.persist(equipo)

6. Hibernate genera SQL automáticamente
   └─> INSERT INTO equipos (nombre) VALUES (?)

7. Servlet redirige a lista
   └─> response.sendRedirect("/equipos")

8. Usuario ve la lista actualizada
   └─> listar.jsp muestra todos los equipos

+Public     (público)Accesible desde cualquier lugar
-Private    (privado)Solo accesible dentro de la clase
#Protected  Accesible en la clase y subclases.

El DAO usa la entidad Equipos para hacer consultas.

Rombo vacío = Agregación (débil):
    El contenedor puede existir sin el contenido. 
    Si borras el equipo, los jugadores pueden seguir existiendo.
Rombo relleno = Composición (fuerte):
    El contenedor posee el contenido. 
    Si borras el partido, los goles también se borran.

Estereotipo      Significado
<<interface>>   Es una interfaz Java
<<abstract>>    Es una clase abstracta
<<utility>>     Clase con métodos estáticos (como JPAUtil)
<<HttpServlet>> Es un servlet


Símbolos clave:

|| = Uno
o{ = Cero o muchos
PK = Clave primaria
FK = Clave foránea

▲ (triángulo) = Herencia/Implementación
───> (línea sólida) = Asociación/Usa
....> (línea punteada) = Dependencia
"1" * = Cardinalidad

REGLAS MNEMOTÉCNICAS
ER (Base de datos):

Muchas patas (}{) = Muchos registros
Una pata (||) = Un solo registro
Círculo (o) = Puede ser cero

