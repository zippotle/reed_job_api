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