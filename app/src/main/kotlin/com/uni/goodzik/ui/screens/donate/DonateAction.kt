package com.uni.goodzik.ui.screens.donate

sealed interface DonateAction {
    data class Toast(val message: String): DonateAction
}