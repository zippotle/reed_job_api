package com.yah.job_api.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yah.job_api.model.Jobs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.yah.job_api.network.NetworkInterface


//Issue:
//getJob() wont import from NetworkInterface.kt

class JobsRetriever {

    companion object {
        var BaseUrl = "https://www.reed.co.uk"
    }

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val networkInterface: NetworkInterface by lazy {
        retrofit.create(NetworkInterface::class.java)
    }

   suspend fun getJobs() : List<Jobs> {
       //WHY IS THIS NOT WORKING??!!??
       return networkInterface.getJobs()
   }

}