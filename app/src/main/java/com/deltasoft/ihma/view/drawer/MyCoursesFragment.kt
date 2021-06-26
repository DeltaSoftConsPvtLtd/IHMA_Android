package com.deltasoft.ihma.view.drawer



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.NotificationAdapter
import com.deltasoft.ihma.databinding.FragmentMyCoursesBinding
import com.deltasoft.ihma.model.NotificationModel
import com.deltasoft.ihma.viewmodel.MyCoursesViewModel


class MyCoursesFragment : Fragment() {



    val viewModel: MyCoursesViewModel by viewModels()
    var adapter:NotificationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationFragmentBinding = DataBindingUtil.inflate<FragmentMyCoursesBinding>(
            inflater,
            R.layout.fragment_my_courses,
            container,
            false
        )
        notificationFragmentBinding.lifecycleOwner = this
        notificationFragmentBinding.viewModel = viewModel
        adapter = NotificationAdapter(viewModel, this)
        notificationFragmentBinding.notificationRecycler.adapter=adapter
        addData()
        observeData()

        return notificationFragmentBinding.root
    }





    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {
        val date="25"
        val month="April"
        val title="Membership is Approved"
        val description="Pay Now"
        if(title.isNullOrBlank()){
            Toast.makeText(context,"Enter value!", Toast.LENGTH_LONG).show()
        }else{
            val notification= NotificationModel(date,month,title,description)
            viewModel.add(notification)
        }

    }





}