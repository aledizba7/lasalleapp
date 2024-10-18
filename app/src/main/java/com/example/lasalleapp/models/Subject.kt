package com.example.lasalleapp.models

data class Subject(
    val id: Int,
    val name: String,
    val averageGrade: Float,
    val partialGrades: List<PartialGrade> = emptyList()
)

val subjects = listOf(
    Subject(
        1,
        "Plataforma Android",
        9.0f,
        partialGrades = listOf(
            PartialGrade(1,8.5f),
            PartialGrade(2, 9.0f),
            PartialGrade(3, 9.5f)
        )
    ),
    Subject(
        2,
        "Procesamiento de Imágenes",
        8.8f,
        partialGrades = listOf(
            PartialGrade(1, 8.0f),
            PartialGrade(2, 9.0f),
            PartialGrade(3, 9.0f)
        )
    ),
    Subject(
        3,
        "Redes de Área Local",
        7.5f,
        partialGrades = listOf(
            PartialGrade(1, 7.0f),
            PartialGrade(2, 8.0f),
            PartialGrade(3, 7.5f)
        )
    ),
    Subject(
        4,
        "Modelos Abstractos",
        7.0f,
        partialGrades = listOf(
            PartialGrade(1, 6.5f),
            PartialGrade(2, 7.5f),
            PartialGrade(3, 7.0f)
        )
    ),
    Subject(
        5,
        "Administración de Proyectos",
        9.5f,
        partialGrades = listOf(
            PartialGrade(1, 9.0f),
            PartialGrade(2, 10.0f),
            PartialGrade(3, 9.5f)
        )
    )
)