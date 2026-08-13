# EnCurso

Resumen:  
EnCurso es un proyecto que consiste en una plataforma de gestión de cursos, el objetivo es permitir la administración de usuarios, cursos y las inscripciones de los estudiantes.

Arquitectura:  
Actualmente, la plataforma está compuesta por tres dominios principales:  
- User = Permite el registro de nuevos usuarios y gestiona el proceso de autenticación.

- Course = Permite la creación, consulta y gestión de los cursos.

- Enrol = Gestiona la inscripción de un usuario en un curso.

Comandos de Ejecución (ejecutar desde la raíz del proyecto):
- Para correr la suite de tests: mvn clean test
- Para generar el reporte de cobertura: mvn jacoco:report

Ruta de Evidencia:  
Abrir el reporte de cobertura en: target/site/jacoco/index.html