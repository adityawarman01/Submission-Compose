package com.example.submissioncompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.BookRepository
import com.example.submissioncompose.model.Book
import com.example.submissioncompose.model.OrderBook
import com.example.submissioncompose.ui.common.UiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailBookViewModel (private val repository: BookRepository) : ViewModel(){

    private val _uiState: MutableStateFlow<UiResult<OrderBook>> =
        MutableStateFlow(UiResult.Loading)
    val uiState: StateFlow<UiResult<OrderBook>>
        get() = _uiState

    fun getBookById(bookId: Long){
        viewModelScope.launch {
            _uiState.value = UiResult.Loading
            _uiState.value = UiResult.Success(repository.getOrderBookById(bookId))
        }
    }
    fun addToChart(book: Book, count: Int) {
        viewModelScope.launch {
            repository.updateOrderBook(book.bookID, count)
        }
    }
}