package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.view.home.EventsFragment
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.alertModel.AlertModel
import com.deltasoft.ihma.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.single_home_events.view.*


class EventsAdapter(val viewModel: EventsViewModel, val context: EventsFragment): RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
    var arrayList: ArrayList<AlertModel>? = null


    fun setData(list: ArrayList<AlertModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventsAdapter.EventsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_home_events,parent,false)
        return EventsViewHolder(root)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventsViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class EventsViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(events: AlertModel){
            binding.events_text_id.text = events.name

        }

    }

}

