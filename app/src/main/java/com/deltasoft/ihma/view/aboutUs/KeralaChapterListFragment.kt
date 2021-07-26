package com.deltasoft.ihma.view.aboutUs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.bindingAdaptor.ChaptersAdapter
import com.deltasoft.ihma.databinding.FragmentChapterListBinding
import com.deltasoft.ihma.viewmodel.ChapterListViewModel


class KeralaChapterListFragment : Fragment() {
    val viewModel: ChapterListViewModel by viewModels()
    var adapter: ChaptersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chapterListBinding = DataBindingUtil.inflate<FragmentChapterListBinding>(
            inflater,
            R.layout.fragment_chapter_list,
            container,
            false
        )
        chapterListBinding.lifecycleOwner = this
        chapterListBinding.viewModel = viewModel
        chapterListBinding.fragment = this

        adapter = ChaptersAdapter(viewModel, this)
        chapterListBinding.chaptersRv.adapter=adapter
        observeData()
        return chapterListBinding.root

    }

    private fun observeData() {
        viewModel.lst.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            Log.i("data",it.toString())
            adapter?.setData(it)
        })
    }

    fun onAllChaptersViewClicked(view: View) {

        findNavController().navigate(R.id.action_aboutus_fragment_to_viewAllChapters)

    }

}