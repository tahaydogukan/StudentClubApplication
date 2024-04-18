package com.tahayasindogukan.studentclubapplication.core.entitiy

import android.content.Context
import java.io.Serializable

data class Request(
    val documentId:String="",
    val title: String="",
    val content: String="",
    val eventGoals: String="",
    val agenda: String="",
    val startDate: String="",
    val endDate: String="",
    val manager:String="",

    val newTitle: String="",
    val newManager:String="",
    val newContent:String="",
    val newAttachment: String="",
    val newStartDate: String="",
    val newEndDate: String="",
    val newLocation: String="",
    val newWebPlatform: String="",
    val newContacts: String="",

    val attachment: String="",
    val location: String="",
    val webPlatform: String="",
    val contacts: String="",
    val isForm:Boolean=true,
    val isPost:Boolean=false,
    val status:Int=1,
    val clubName:String="",
) : Serializable {
}