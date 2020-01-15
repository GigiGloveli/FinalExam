package com.example.finalexam.finalexam

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class db(
    var name: String = "",
    var surname: String = "",
    var age: String = "",
    var number: String? = "",
    var gender: String = "",
    var url: String = ""

)
