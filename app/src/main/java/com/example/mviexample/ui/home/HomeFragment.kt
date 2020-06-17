package com.example.mviexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mviexample.R
import com.example.mviexample.databinding.FragmentHomeBinding
import com.example.mviexample.model.Post
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
        dataBinding.postItems.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PostsAdapter(listOf(Post(1, "title", "desc"))) { id -> onItemClicked(id) }
        }
    }

    private fun onItemClicked(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }
}