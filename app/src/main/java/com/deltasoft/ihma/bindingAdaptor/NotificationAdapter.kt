package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.alertModel.AlertModel
import com.deltasoft.ihma.view.home.NotificationFragment
import com.deltasoft.ihma.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.single_notification_layout.view.*

class NotificationAdapter(val viewModel: NotificationViewModel, val context: NotificationFragment): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    var arrayList: ArrayList<AlertModel>? = null

    fun setData(list: ArrayList<AlertModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotificationAdapter.NotificationViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_home_events,parent,false)
        return NotificationViewHolder(root)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.NotificationViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class NotificationViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(notification: AlertModel){

            binding.notification_date.text = notification.name


        }

    }

}