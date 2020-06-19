package com.example.mviexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mviexample.R
import com.example.mviexample.databinding.FragmentHomeBinding
import com.example.mviexample.domain.event.HomeEvent
import com.example.mviexample.model.AdapterItem
import com.example.mviexample.model.PostUi
import com.example.mviexample.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
    }

    private fun registerObserver() {
        viewModel.onEvent(HomeEvent.FetchData)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun render(viewState: HomeViewState) {
        dataBinding.viewState = viewState
        viewState.run {
            when {
                isLoading -> return
                posts.isNotEmpty() && photos.isNotEmpty() -> {
                    dataBinding.content.postItems.run {
                        val verticalLayout =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        layoutManager = verticalLayout
                        adapter = PostsAdapter(AdapterItem(posts, photos)) { postUi -> onItemClicked(postUi) }
                    }
                }
                isError -> showErrorMessageDialog { registerObserver() }
            }
        }
    }

    private fun onItemClicked(postUi: PostUi) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(postUi)
        findNavController().navigate(action)
    }
}