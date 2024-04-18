package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Club(
    val clubId:String="",
    val clubStatus:String="",
    val clubName: String = "",
    val clubPhoto: String = "",
    val clubManagerId: String = "",
    val clubCategory: String = "",
    val clubDescription:String = "",
    val newClubName: String = "",
    val newClubPhoto: String = "",
    val newClubDescription:String = ""
) : Serializable