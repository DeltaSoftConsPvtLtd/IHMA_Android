package com.deltasoft.ihma.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.GalleryAdapter
import com.deltasoft.ihma.databinding.FragmentGalleryBinding
import com.deltasoft.ihma.model.HomeModel
import com.deltasoft.ihma.viewmodel.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    val viewModel: GalleryViewModel by viewModels()
    var adapter: GalleryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryFragmentBinding = DataBindingUtil.inflate<FragmentGalleryBinding>(
            inflater,
            R.layout.fragment_gallery,
            container,
            false
        )
        galleryFragmentBinding.lifecycleOwner = this
        galleryFragmentBinding.viewModel = viewModel
        adapter = GalleryAdapter(viewModel, this)
        galleryFragmentBinding.galleryRecyclerView.adapter = adapter
        galleryFragmentBinding.galleryRecyclerView.setLayoutManager(GridLayoutManager(context, 2))
        addData()
        observeData()

        return galleryFragmentBinding.root
    }


    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Log.i("data", it.toString())
            adapter?.setData(it)
        })
    }

    private fun addData() {

        val gallery1 = HomeModel(R.drawable.ag, "")
        viewModel.add(gallery1)
        val gallery2 = HomeModel(R.drawable.bg, "")
        viewModel.add(gallery2)
        val gallery4 = HomeModel(R.drawable.dg, "")
        viewModel.add(gallery4)
        val gallery6 = HomeModel(R.drawable.fg, "")
        viewModel.add(gallery6)
        val gallery8 = HomeModel(R.drawable.hg, "")
        viewModel.add(gallery8)
        val gallery13 = HomeModel(R.drawable.mg, "")
        viewModel.add(gallery13)
        val gallery18 = HomeModel(R.drawable.homec, "")
        viewModel.add(gallery18)
        val gallery23 = HomeModel(R.drawable.wg, "")
        viewModel.add(gallery23)
        val gallery27 = HomeModel(R.drawable.bgg, "")
        viewModel.add(gallery27)
        val gallery29 = HomeModel(R.drawable.dgg, "")
        viewModel.add(gallery29)


        }


    }
