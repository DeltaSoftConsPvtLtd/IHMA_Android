package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.view.home.FlashNewsFragment
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.alertModel.AlertModel
import com.deltasoft.ihma.viewmodel.FlasNewsViewModel
import kotlinx.android.synthetic.main.single_home_flashnews.view.*


class FlashNewsAdapter(val viewModel: FlasNewsViewModel, val context: FlashNewsFragment): RecyclerView.Adapter<FlashNewsAdapter.FlashNewsViewHolder>() {
    var arrayList: ArrayList<AlertModel>? = null

    fun setData(list: ArrayList<AlertModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FlashNewsAdapter.FlashNewsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_home_flashnews,parent,false)
        return FlashNewsViewHolder(root)
    }

    override fun onBindViewHolder(holder: FlashNewsAdapter.FlashNewsViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class FlashNewsViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(flashnews: AlertModel){

            binding.flashnews_text_id.text = flashnews.name

        }

    }

}