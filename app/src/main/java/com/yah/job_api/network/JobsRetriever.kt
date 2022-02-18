package com.yah.job_api.network

import com.yah.job_api.model.Jobs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.NetworkInterface



//Issue:
//getJob() wont import from NetworkInterface.kt

class JobsRetriever {
    private val networkInterface: NetworkInterface

    companion object {
        var BaseUrl = "https://www.reed.co.uk"
    }

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        networkInterface = retrofit.create(NetworkInterface::class.java)
    }

   suspend fun getJobs() : List<Jobs> {
       //WHY IS THIS NOT WORKING??!!??
       return networkInterface.getJobs()
   }

}