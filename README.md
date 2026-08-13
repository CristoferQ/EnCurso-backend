# EnCurso - Plataforma de Gestión de Cursos

## Título y Descripción

**EnCurso** es un proyecto integrador autónomo que implementa una plataforma de gestión de cursos, permite la administración de usuarios, cursos e inscripciones de estudiantes, manteniendo una clara separación de responsabilidades entre las capas de dominio, aplicación e infraestructura.

---

## Diagrama de Paquetes (Arquitectura Limpia)

```
src/main/java/com/encurso/
│
├── domain/                          ← Capa de Dominio (Reglas de Negocio Puras)
│   ├── entity/
│   │   ├── User.java               (Entidad de Usuario)
│   │   ├── Course.java             (Entidad de Curso)
│   │   ├── Enrollment.java         (Entidad de Inscripción)
│   │   └── UserLoginRequest.java   (DTO de Autenticación)
│   │
│   ├── repository/                 (Interfaces de Persistencia)
│   │   ├── UserRepository.java
│   │   ├── CourseRepository.java
│   │   └── EnrollmentRepository.java
│   │
│   ├── valueObject/                (Objetos de Valor)
│   │   ├── Email.java              (Email con validación)
│   │   └── Password.java           (Contraseña con validación)
│   │
│   └── exception/                  (Excepciones de Dominio)
│       ├── ValidationException.java
│       ├── UserNotFoundException.java
│       ├── UserAlreadyExistsException.java
│       ├── CourseAlreadyExistsException.java
│       ├── EnrollmentAlreadyExistsException.java
│       └── InvalidEmailException.java
│
├── application/                     ← Capa de Aplicación (Casos de Uso)
│   ├── useCase/
│   │   ├── RegisterUserUseCase.java
│   │   ├── CreateCourseUseCase.java
│   │   ├── CreateEnrollmentUseCase.java
│   │   ├── CancelEnrollmentUseCase.java
│   │   └── GetEnrollmentUseCase.java
│   │
│   └── service/                    (Servicios de Aplicación)
│       ├── UserService.java
│       ├── CourseService.java
│       └── EnrollmentService.java
│
└── persistence/                     ← Capa de Infraestructura (Adaptadores)
    ├── InMemoryUserRepository.java
    ├── InMemoryCourseRepository.java
    └── InMemoryEnrollmentRepository.java
```

### Separación de Capas

| Capa | Responsabilidad                                     |
|------|-----------------------------------------------------|
| **Domain** | Reglas de negocio puras, sin dependencias externas  |
| **Application** | Orquestación de casos de uso y servicios |
| **Persistence** | Implementación de persistencia en memoria/BD |

---

## Instrucciones de Ejecución de Pruebas

### Requisitos Previos
- **Java 17+**
- **Maven 3.6+**

### Compilar y Verificar el Proyecto
```bash
mvn clean compile
```
Este comando:
- Limpia cualquier compilación anterior
- Compila el código fuente verificando la sintaxis
- Valida la estructura del proyecto

### Ejecutar la Suite de Pruebas Unitarias
```bash
mvn test
```
Este comando ejecuta toda la suite de pruebas que valida:
- Desacoplamiento entre capas
- Casos de uso aislados con mocks
- Validaciones de dominio
- Persistencia en memoria

### Ver Reporte de Cobertura
```bash
mvn clean test jacoco:report
```
Visualizar el reporte en: `target/site/jacoco/index.html`

---

## Cobertura de Pruebas

**Estadísticas Actuales:**
- **Líneas**: 98.43%
- **Ramas**: 92.86%
- **Métodos**: 98.67%
- **Clases**: 87.50%
- **Total Combinado**: 96.66%

**Tests**: 95+ casos de prueba unitaria