package com.yah.job_api.network

import com.yah.job_api.model.Jobs
import retrofit2.http.GET

interface NetworkInterface {
    //parameter for Reed Job API
    //change keyword for each career
    @GET("/api/1.0/search?keywords=web-developer")
//    suspend fun getJobs() : Jobs
    suspend fun getJobs() : List<Jobs>

}