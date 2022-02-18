# Reed Job API

Android Application to display Web Developer roles from the Reed Job API.

## Current Errors

JobRetriever.kt

Issue: getJob() won't import from NetworkInterface.kt

```bash
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

```

JobAdapter.kt

Issue: Not importing from job_item.xml

```bash
package com.yah.job_api.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yah.job_api.R
import com.yah.job_api.model.Jobs
import kotlinx.coroutines.Job
//manual import
import kotlinx.android.synthetic.main.job_item.view*

class JobAdapter(private val jobResponse: List<Jobs>) :
    RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
        fun bind(job: Jobs) {
            //not importing from job_item.xml
            //import kotlinx.android.synthetic.main.job_item.view*
            itemView.jobTitle.text = job.jobTitle
            itemView.employerName .text = job.employerName
            itemView.locationName.text = job.locationName
            itemView.jobDescription.text = job.jobDescription
            itemView.jobUrl.text = job.jobUrl

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(view = LayoutInflater.from(parent.context).inflate(R.job_item, parent, false),)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Not recognizing what bind is
        holder.bind(job = jobResponse.get(position))    }

    override fun getItemCount(): Int = jobResponse.size
}
```

MainActivity.kt

Issue: recyclerView not working

```bash
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

```