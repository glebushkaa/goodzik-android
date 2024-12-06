package com.uni.goodzik.ui.screens.chat

import com.uni.goodzik.model.Message
import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

@HiltViewModel(assistedFactory = ChatViewModel.Factory::class)
class ChatViewModel @AssistedInject constructor(
    @Assisted private val id: String
) : StateViewModel<ChatState>(ChatState()) {

    init {
        loadChat()
    }

    fun sendAction(action: ChatAction) {
        when (action) {
            is ChatAction.MessageChanged -> mutableState.update {
                it.copy(text = action.message)
            }

            ChatAction.Send -> {}
        }
    }

    private fun loadChat() {
        val persistentList = persistentListOf<Message>().builder()
        repeat(10) {
            val chatMessage = Message(
                id = it.toString(),
                text = "Message $it",
                incoming = it % 2 == 0,
                author = "Author $it",
            )
            persistentList.add(chatMessage)
        }
        mutableState.update {
            it.copy(messages = persistentList.build())
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): ChatViewModel
    }
}