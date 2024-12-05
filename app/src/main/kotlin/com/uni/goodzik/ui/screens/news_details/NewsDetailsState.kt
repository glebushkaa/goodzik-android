package com.uni.goodzik.ui.screens.news_details

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class NewsDetailsState(
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val images: ImmutableList<String> = persistentListOf()
)