# Evaluación Curso Java Backend 17

> Se agrego postman collection para pruebas

+ Listar estudiantes ordenados de forma descendente por edad usando programación
funcional

    [localhost:8080/api/students](localhost:8080/api/students)

> Se agrego u parametro opcional para ordenar ya sea descendente(DESC) o ascendente(ASC) por defecto es ascendente por nombre

    [localhost:8080/api/students?order=DESC](localhost:8080/api/students?order=DESC)

+ Mostrar la relación de cursos matriculados y sus estudiantes correspondientes
usando programación funcional (sugerencia, usar un Map<K,V>)

    [localhost:8080/api/enrollments/list](localhost:8080/api/enrollments/list)

```
{
    "Matematicas": [
        "Williams"
    ],
    "Programación": [
        "Williams",
        "Pepe",
        "Jose"
    ]
}
```



