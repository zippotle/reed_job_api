package com.yah.job_api.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yah.job_api.R
import com.yah.job_api.model.Jobs
import kotlinx.coroutines.Job
//manual import

class JobAdapter(private val jobResponse: List<Jobs>) :
    RecyclerView.Adapter<JobAdapter.ViewHolder>() {

    private val job = Jobs()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val jobTitle: TextView = view.findViewById(R.id.jobTitle)
        val employerName: TextView = view.findViewById(R.id.employerName)
        val locationName: TextView = view.findViewById(R.id.locationName)
        val jobDescription: TextView = view.findViewById(R.id.jobDescription)
        val jobUrl: TextView = view.findViewById(R.id.jobUrl)
    }

        /**fun bind(job: Jobs) {
            //not importing from job_item.xml
            //import kotlinx.android.synthetic.main.job_item.view*
            job.jobTitle.text = job.jobTitle
            itemView.employerName .text = job.employerName
            itemView.locationName.text = job.locationName
            itemView.jobDescription.text = job.jobDescription
            itemView.jobUrl.text = job.jobUrl

        }**/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Not recognizing what bind is
        //holder.bind(job = jobResponse.get(position))
        holder.jobTitle.text = job.jobTitle
        holder.employerName.text = job.employerName
        holder.jobDescription.text = job.jobDescription
        holder.locationName.text = job.locationName
        holder.jobUrl.text = job.jobUrl
    }

    override fun getItemCount(): Int = jobResponse.size
}