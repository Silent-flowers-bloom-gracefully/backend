package com.lylist.silentflowers.presentation.community.dto

data class CreateArticleRequest(
    val title: String,
    val content: String,
    val categories: List<String>
)
