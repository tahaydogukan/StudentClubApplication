package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Club(
    val clubName: String = "",
    val clubPhoto: String = "",
    val clubManagerId: String = "",
    val clubCategory: String = "",
    val clubDescription:String = ""
) : Serializable