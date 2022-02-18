package com.yah.job_api.model

data class Jobs (
    //Data we will retreive from the API
    //Considering adding the jobUrl
    val jobTitle : String = "",
    val employerName : String = "",
    val locationName : String = "",
    val jobDescription : String = "",
    val jobUrl : String = ""

        )