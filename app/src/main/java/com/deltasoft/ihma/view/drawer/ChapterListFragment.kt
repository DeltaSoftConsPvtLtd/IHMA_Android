package com.deltasoft.ihma.view.drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentChapterListBinding
import com.deltasoft.ihma.viewmodel.ChapterListViewModel


class ChapterListFragment : Fragment() {
    val viewModel: ChapterListViewModel by viewModels()

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
        return chapterListBinding.root
    }




}