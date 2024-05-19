package com.example.appdam.retrofit.model.activity.oneActivity

data class OneActivity(
    val activityprice: Int,
    val creator: Creator,
    val date: String,
    val description: String,
    val displacement: String,
    val endTime: String,
    val hourlyPrice: Int,
    val id: Int,
    val img: String,
    val location: String,
    val material: String,
    val name: String,
    val province: String,
    val startTime: String,
    val summary: String,
    val total: Int,
    val users: List<User>,
    val zone: String
)