package com.uni.goodzik.ui.screens.news

import com.uni.goodzik.model.News
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class NewsState(
    val news: ImmutableList<News> = persistentListOf()
)