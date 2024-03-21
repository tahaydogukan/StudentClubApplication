package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Activity(
    val name: String = "",
    val date: Int = 0,
    val year: Int = 0,
    val month: Int = 0,
    val day: Int = 0
) : Serializable