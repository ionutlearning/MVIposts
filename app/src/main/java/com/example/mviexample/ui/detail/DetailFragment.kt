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

class DetailFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    private lateinit var dataBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var post: PostUi

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
        dataBinding.post = post
        registerObserver()
    }

    private fun render(viewState: DetailsViewState) {
        dataBinding.viewState = viewState
        viewState.run {
            when {
                isLoading -> return
                comments.isNotEmpty() -> {
                    dataBinding.content.postItems.run {
                        val verticalLayout =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        layoutManager = verticalLayout
                        adapter = CommentsAdapter(comments.take(3))
                    }
                }
                isError -> showErrorMessageDialog { registerObserver() }
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