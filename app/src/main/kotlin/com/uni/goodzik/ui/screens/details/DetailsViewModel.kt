package com.uni.goodzik.ui.screens.details

import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String
) : StateViewModel<DetailsState>(DetailsState()) {

    init {
        loadGuideDetails()
    }

    private fun loadGuideDetails() {
        mutableState.update {
            it.copy(
                title = "Title $id",
                author = "Author with $id",
                description = """
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже повітря, то і одужання буде йти довше та важче. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді.
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже по
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже повітря, то і одужання буде йти довше та важче. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді.
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже по
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже повітря, то і одужання буде йти довше та важче. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді. 
                    Весна - сезон худі. 
                    В "Швейній роті" - кіберхуді.
                    Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в Кіберхуді - одяг який дуже допомагає одужувати нашим хлопцям. Бо, якщо у пораненого нема одягу, в якому можна вийти на свіже по
                """.trimIndent(),
                imageUrl = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2024/02/attackontitan_anime_colossustitan_eren.jpg?q=50&fit=crop&w=943&h=&dpr=1.5"
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): DetailsViewModel
    }
}