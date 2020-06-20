package com.example.mviexample.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mviexample.R
import com.example.mviexample.databinding.PostItemLayoutBinding
import com.example.mviexample.model.PostsAdapterItem
import com.example.mviexample.model.PostUi
import kotlin.random.Random

class PostsAdapter(private val callback: (postUi: PostUi) -> Unit) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private lateinit var dataBinding: PostItemLayoutBinding
    private var postsAdapterItem: PostsAdapterItem = PostsAdapterItem(emptyList(), emptyList())

    fun setup(postsAdapterItem: PostsAdapterItem) {
        this.postsAdapterItem = postsAdapterItem
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item_layout,
            parent,
            false
        )
        return PostViewHolder(dataBinding.root)
    }

    override fun getItemCount() = postsAdapterItem.posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postsAdapterItem.posts[position]
        dataBinding.post = post
        val imageUrl = postsAdapterItem.photos[Random.nextInt(postsAdapterItem.photos.size)].thumbnailUrl
        dataBinding.imageUrl = imageUrl
        dataBinding.root.setOnClickListener {
            callback(PostUi(post.userId, post.id, post.title, post.body, imageUrl))
        }
    }
}