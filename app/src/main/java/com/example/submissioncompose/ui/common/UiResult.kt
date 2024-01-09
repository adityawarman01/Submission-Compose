package com.example.submissioncompose.ui.common


sealed class UiResult<out T: Any?> {
    object Loading: UiResult<Nothing>()

    data class Success<out T: Any>(val data: T): UiResult<T>()
    data class Error(val errorMessage: String) : UiResult<Nothing>()
}