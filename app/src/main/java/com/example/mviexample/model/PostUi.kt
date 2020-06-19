package com.example.mviexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostUi(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String
) : Parcelable