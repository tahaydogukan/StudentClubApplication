package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

class ClubManager(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone:String = "",
    val clubId: String = ""
) : Serializable {
}