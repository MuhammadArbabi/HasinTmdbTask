package com.della.hassintmdbtask.data.model.movie.detail


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)