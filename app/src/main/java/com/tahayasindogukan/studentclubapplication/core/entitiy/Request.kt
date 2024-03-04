package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Request(
    val name: String="",
    val surname: String="",
    val visibility: Int=0
) : Serializable {
}