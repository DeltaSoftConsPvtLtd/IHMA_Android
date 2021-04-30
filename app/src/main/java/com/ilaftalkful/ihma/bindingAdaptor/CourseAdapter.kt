package com.ilaftalkful.ihma.bindingAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilaftalkful.ihma.R
import com.ilaftalkful.ihma.model.CoursesModel
import com.ilaftalkful.ihma.view.home.ApplyFragment
import com.ilaftalkful.ihma.viewmodel.ApplyViewModel
import kotlinx.android.synthetic.main.single_course_layout.view.*

class CourseAdapter(val viewModel: ApplyViewModel, val arrayList: ArrayList<CoursesModel>, val context: ApplyFragment): RecyclerView.Adapter<CourseAdapter.CoursesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CourseAdapter.CoursesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.single_course_layout,parent,false)
        return CoursesViewHolder(root)
    }

    override fun onBindViewHolder(holder: CourseAdapter.CoursesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
           // Toast.makeText(context,"List is empty",Toast.LENGTH_LONG).show()
        }else{

        }
        return arrayList.size
    }


    inner  class CoursesViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(course: CoursesModel){
            binding.txtView_title.text = course.title
            binding.txtView_description.text = course.description

        }

    }

}