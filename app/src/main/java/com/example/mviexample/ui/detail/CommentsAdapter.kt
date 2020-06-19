package com.example.mviexample.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mviexample.R
import com.example.mviexample.databinding.CommentItemLayoutBinding
import com.example.mviexample.model.Comment

class CommentsAdapter :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    private lateinit var dataBinding: CommentItemLayoutBinding

    private var comments: List<Comment> = emptyList()

    fun setup(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.comment_item_layout,
            parent,
            false
        )
        return CommentsViewHolder(dataBinding.root)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        dataBinding.comment = comments[position]
    }
}