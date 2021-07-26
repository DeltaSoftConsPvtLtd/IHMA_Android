package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.registerModel.ChaptersModel
import com.deltasoft.ihma.view.aboutUs.KeralaChapterListFragment
import com.deltasoft.ihma.viewmodel.ChapterListViewModel
import kotlinx.android.synthetic.main.single_home_events.view.*


class ChaptersAdapter(val viewModel: ChapterListViewModel, val context: KeralaChapterListFragment): RecyclerView.Adapter<ChaptersAdapter.ChaptersViewHolder>() {
    var arrayList: ArrayList<ChaptersModel>? = null


    fun setData(list: ArrayList<ChaptersModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ChaptersAdapter.ChaptersViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_chapters_events,parent,false)
        return ChaptersViewHolder(root)
    }

    override fun onBindViewHolder(holder: ChaptersAdapter.ChaptersViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class ChaptersViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(events: ChaptersModel){
            binding.events_text_id.text = events.chapters

        }

    }

}

