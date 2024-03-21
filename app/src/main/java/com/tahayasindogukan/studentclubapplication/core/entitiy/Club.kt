package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Club(
    val name: String = "",
    val photo: String = "",
    val category: String = ""
) : Serializable