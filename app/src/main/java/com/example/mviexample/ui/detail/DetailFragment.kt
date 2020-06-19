package com.example.mviexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mviexample.R
import com.example.mviexample.databinding.FragmentDetailBinding
import com.example.mviexample.domain.event.DetailsEvent
import com.example.mviexample.ui.base.BaseFragment

class DetailFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    private lateinit var dataBinding: FragmentDetailBinding
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val post = args.post
        dataBinding.post = post

        viewModel.onEvent(DetailsEvent.FetchData(post.id))
        viewModel.data.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun render(it: DetailsViewState) {
        println(it.comments.toString())
    }
}