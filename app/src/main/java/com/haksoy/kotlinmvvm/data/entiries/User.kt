package com.haksoy.kotlinmvvm.data.entiries

import java.io.Serializable
import java.util.*

data class User(
    val createdAt: Date,
    val name: String,
    val surname: String,
    val number: String,
    val department: String,
    val company_name: String,
    val email: String,
    val id: String
) : Serializable