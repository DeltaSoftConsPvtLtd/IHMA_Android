package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.CoursesModel
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.view.bottomNavigation.ApplyFragment
import com.deltasoft.ihma.view.bottomNavigation.HomeFragment
import com.deltasoft.ihma.viewmodel.ApplyViewModel
import com.deltasoft.ihma.viewmodel.NewHomeViewModel
import kotlinx.android.synthetic.main.opmarketing.view.*
import kotlinx.android.synthetic.main.single_course_layout.view.*
import kotlinx.android.synthetic.main.single_home_collaborations.view.*

class HomeCollaborationsAdapter(val viewModel: NewHomeViewModel, val context: HomeFragment): RecyclerView.Adapter<HomeCollaborationsAdapter.CollaborationsViewHolder>() {
    var arrayList: ArrayList<HomeModel>? = null

    fun setData(list: ArrayList<HomeModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeCollaborationsAdapter.CollaborationsViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_home_collaborations,parent,false)
        return CollaborationsViewHolder(root)
    }

    override fun onBindViewHolder(holder: HomeCollaborationsAdapter.CollaborationsViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class CollaborationsViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(home: HomeModel){
            binding.collaborations_img_id.setImageResource(home.image)

        }

    }

}