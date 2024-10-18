package com.example.lasalleapp.models

data class Student(
    val name: String,
    val career: String,
    val semester: Int,
    val subjects: List<Subject>
){
    val accumulatedAverage: Float
        get()= subjects.map { it.averageGrade }
            .average().toFloat()
}
