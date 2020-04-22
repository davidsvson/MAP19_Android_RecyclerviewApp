package com.example.recyclerviewapp

object DataManager {
    val students = mutableListOf<Student>()

    init {
        createMockData()
    }

    fun createMockData() {
        var student = Student("Alessio", "MAP19")
        students.add(student)
        student = Student("Andreas", "MAP19")
        students.add(student)
        student = Student("Alexander", "MAP19")
        students.add(student)
        student = Student("Johannes", "MAP19")
        students.add(student)
        student = Student("Emil F", "MAP19")
        students.add(student)
        student = Student("Emil L", "MAP19")
        students.add(student)
    }
}