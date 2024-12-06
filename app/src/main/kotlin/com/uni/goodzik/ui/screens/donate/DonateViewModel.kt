package com.uni.goodzik.ui.screens.donate

import com.uni.goodzik.ui.core.StateViewModel
import com.uni.goodzik.ui.core.component.ToastMessageData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DonateViewModel @Inject constructor() : StateViewModel<DonateState>(DonateState()) {

    fun sendAction(action: DonateAction) {
        when (action) {
            is DonateAction.Toast -> {
                val toast = ToastMessageData(
                    text = action.message,
                    type = ToastMessageData.Type.Success
                )
                showToast(toast)
            }
        }
    }
}