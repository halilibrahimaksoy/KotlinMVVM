package com.haksoy.kotlinmvvm.data.entiries

import java.io.Serializable
import java.util.*

data class User(
    val createdAt: Date,
    var name: String,
    var surname: String,
    var number: String,
    var department: String,
    var company_name: String,
    var email: String,
    val id: String
) : Serializable