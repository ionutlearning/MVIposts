package com.example.mviexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mviexample.R
import com.example.mviexample.databinding.FragmentDetailBinding
import com.example.mviexample.domain.event.DetailsEvent
import com.example.mviexample.model.PostUi
import com.example.mviexample.ui.base.BaseFragment

class DetailsFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    private lateinit var dataBinding: FragmentDetailBinding
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var post: PostUi
    private val mAdapter = CommentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post = args.post
        setupContent()
        registerObserver()
    }

    private fun setupContent() {
        dataBinding.post = post
        dataBinding.content.recyclerView.run {
            val verticalLayout =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = verticalLayout
            adapter = mAdapter
        }

        dataBinding.content.errorMessage.setOnClickListener {
            registerObserver()
        }
    }

    private fun render(viewState: DetailsViewState) {
        viewState.run {
            dataBinding.viewState = this
            if (comments.isNotEmpty()) {
                mAdapter.setup(comments)
            }
        }
    }

    private fun registerObserver() {
        viewModel.onEvent(DetailsEvent.FetchData(post.id))
        viewModel.data.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }
}