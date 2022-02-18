package com.yah.job_api.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yah.job_api.R
import com.yah.job_api.model.JobResponse
import com.yah.job_api.network.JobsRetriever
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private val jobRetriever : JobsRetriever = JobsRetriever()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intRecyclerview()
        fetchJobs()

    }

    private fun fetchJobs() {
        //Implement coroutines to fetch jobs from API
        val jobsFetchJob = Job()

        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        val scope = CoroutineScope(jobsFetchJob + Dispatchers.Main)
        scope.launch(errorHandler) {
            //fetched data
            val jobResponse = jobRetriever.getJobs()

            //render data in recyclerview
            renderData(jobResponse)
        }

        }

    private fun renderData(jobResponse: JobResponse) {
        recyclerview.adapter = JobAdapter(jobResponse)

    }
}

    private fun intRecyclerview() {
        recyclerview.layoutManager = LinearLayoutManager(this)
    }
