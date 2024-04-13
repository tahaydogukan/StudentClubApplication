package com.tahayasindogukan.studentclubapplication.core.entitiy

import java.io.Serializable

data class Activity(
    val activityTitle: String = "",
    val activityContent:String="",
    val activityLocation:String="",
    val activityManager:String="",
    val activityAttachment:String="",
    val activityYear:String="",
    val activityMonth:String="",
    val activityDay:String="",
    val activityTags:String=""
) : Serializable