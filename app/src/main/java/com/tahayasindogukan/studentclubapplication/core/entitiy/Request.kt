package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable
import java.util.Date

data class Request(
    val documentId:String="",
    val title: String="",
    val content: String="",
    val eventGoals: String="",
    val agenda: String="",
    val startDate: Date = Date(),
    val endDate: Date = Date(),
    val manager:String="",

    val newTitle: String="",
    val newManager:String="",
    val newContent:String="",
    val newAttachment: String="",
    val newStartDate: Date = Date(),
    val newEndDate: Date = Date(),
    val newLocation: String="",
    val newWebPlatform: String="",
    val newContacts: String="",

    val attachment: String="",
    val location: String="",
    val webPlatform: String="",
    val contacts: String="",
    val isForm:Boolean=true,
    val isPost:Boolean=false,
    val status: String = "1",
    val clubName:String="",
) : Serializable {
}