package com.example.submissioncompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.BookRepository
import com.example.submissioncompose.model.OrderBook
import com.example.submissioncompose.ui.common.UiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: BookRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiResult<List<OrderBook>>> =
        MutableStateFlow(UiResult.Loading)
    val uiState: StateFlow<UiResult<List<OrderBook>>>
        get() = _uiState

    fun getAllListBooks(){
        viewModelScope.launch {
            repository.getAllBooks()
                .catch {
                    _uiState.value = UiResult.Error(it.message.toString())
                }
                .collect{ orderBooks ->
                    _uiState.value = UiResult.Success(orderBooks)
                }
        }
    }
}