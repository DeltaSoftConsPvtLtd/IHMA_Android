package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.view.home.GalleryFragment
import com.deltasoft.ihma.viewmodel.GalleryViewModel
import kotlinx.android.synthetic.main.single_home_gallery.view.*

class GalleryAdapter(val viewModel: GalleryViewModel, val context: GalleryFragment): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    var arrayList: ArrayList<HomeModel>? = null

    fun setData(list: ArrayList<HomeModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): GalleryAdapter.GalleryViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_home_gallery,parent,false)
        return GalleryViewHolder(root)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.GalleryViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class GalleryViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(home: HomeModel){

            binding.gallery_img_id.setImageResource(home.image)


        }

    }

}