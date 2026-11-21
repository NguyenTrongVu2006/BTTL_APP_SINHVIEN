package com.ute.studentmvvm.network

import com.google.gson.annotations.SerializedName
import com.ute.studentmvvm.model.Student

data class StudentDto(
    val id: Int = 0,

    @SerializedName(value = "fullName", alternate = ["name"])
    val fullName: String = "",

    @SerializedName(value = "studentCode", alternate = ["code"])
    val studentCode: String = "",

    val email: String = "",
    val gpa: Double = 0.0,
    val major: String = "Công Nghệ Thông Tin"
)

fun StudentDto.toEntity(): Student {
    return Student(
        fullName = fullName,
        studentCode = studentCode,
        email = email,
        gpa = gpa,
        major = major
    )
}
