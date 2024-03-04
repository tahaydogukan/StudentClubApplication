package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Users(
    val email: String = "",
    val name: String = "",

) : Serializable {}