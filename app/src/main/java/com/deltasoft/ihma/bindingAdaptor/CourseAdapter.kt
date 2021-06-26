package com.deltasoft.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.CoursesModel
import com.deltasoft.ihma.view.bottomNavigation.ApplyFragment
import com.deltasoft.ihma.viewmodel.ApplyViewModel
import kotlinx.android.synthetic.main.single_course_layout.view.*

class CourseAdapter(val viewModel: ApplyViewModel,  val context: ApplyFragment): RecyclerView.Adapter<CourseAdapter.CoursesViewHolder>() {
    var arrayList: ArrayList<CoursesModel>? = null

    fun setData(list: ArrayList<CoursesModel>)
    {
        arrayList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CourseAdapter.CoursesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_course_layout,parent,false)
        return CoursesViewHolder(root)
    }

    override fun onBindViewHolder(holder: CourseAdapter.CoursesViewHolder, position: Int) {
        arrayList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (null != arrayList) {
            arrayList?.size!!
        } else{
            0
        }
    }


    inner  class CoursesViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(course: CoursesModel){
            binding.txtView_title.text = course.title
            binding.txtView_description.text = course.description

        }

    }

}