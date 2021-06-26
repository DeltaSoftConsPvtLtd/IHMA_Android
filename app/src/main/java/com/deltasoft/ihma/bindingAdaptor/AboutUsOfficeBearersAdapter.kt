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
import com.deltasoft.ihma.view.drawer.NewAboutUsFragment
import com.deltasoft.ihma.viewmodel.AboutUsViewModel
import com.deltasoft.ihma.viewmodel.ApplyViewModel
import com.deltasoft.ihma.viewmodel.NewHomeViewModel
import kotlinx.android.synthetic.main.single_course_layout.view.*
import kotlinx.android.synthetic.main.single_home_research.view.*

class AboutUsOfficeBearersAdapter(val viewModel: AboutUsViewModel, val context: NewAboutUsFragment): RecyclerView.Adapter<AboutUsOfficeBearersAdapter.OfficeBearersViewHolder>() {
    var arrayList: ArrayList<HomeModel>? = null

    fun setData(list: ArrayList<HomeModel
            >)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AboutUsOfficeBearersAdapter.OfficeBearersViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_office_bearers_research,parent,false)
        return OfficeBearersViewHolder(root)
    }

    override fun onBindViewHolder(holder: AboutUsOfficeBearersAdapter.OfficeBearersViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class OfficeBearersViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(research: HomeModel){
            binding.research_img_id.setImageResource(research.image)
            binding.research_text_id.text = research.description

        }

    }

}