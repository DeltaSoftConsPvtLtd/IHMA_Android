package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.NotificationModel
import com.deltasoft.ihma.view.drawer.MyCoursesFragment
import com.deltasoft.ihma.viewmodel.MyCoursesViewModel
import kotlinx.android.synthetic.main.single_notification_layout.view.*

class NotificationAdapter(val viewModel: MyCoursesViewModel,  val context: MyCoursesFragment): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    var arrayList: ArrayList<NotificationModel>? = null

    fun setData(list: ArrayList<NotificationModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotificationAdapter.NotificationViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_notification_layout,parent,false)
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
        fun bind(notification: NotificationModel){

            binding.notification_date.text = notification.date
            binding.notification_month.text = notification.month
            binding.notification_title.text = notification.title
            binding.notification_description.text = notification.description

        }

    }

}