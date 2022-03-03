package com.yah.job_api.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yah.job_api.R
import com.yah.job_api.databinding.ActivityMainBinding
import com.yah.job_api.model.Jobs
import com.yah.job_api.network.JobsRetriever
import com.yah.job_api.network.NetworkInterface
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val jobRetriever : JobsRetriever = JobsRetriever()

//    private var jobs = List<Jobs>
    private var jobs = getJobs()
//    private var jobs = NetworkInterface.getJobs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = JobAdapter()

        //intRecyclerview()
        //fetchJobs()

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

    private fun renderData(jobResponse: Jobs) {
        binding.recyclerview.adapter

    }
    private fun renderData(jobResponse: List<Jobs>) {
        binding.recyclerview.adapter = JobAdapter(jobResponse = jobResponse)

    }

    private fun intRecyclerview() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }
}


